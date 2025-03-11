package com.megarepuestos.megarepuestos.service.impl;


import com.megarepuestos.megarepuestos.exception.custom.UnauthorizeException;
import com.megarepuestos.megarepuestos.exception.error.Error;
import com.megarepuestos.megarepuestos.model.User;
import com.megarepuestos.megarepuestos.repository.UserRepository;
import com.megarepuestos.megarepuestos.service.AuthService;
import com.megarepuestos.megarepuestos.service.dto.request.UserDTOin;
import com.megarepuestos.megarepuestos.service.dto.response.UserDTO;
import com.megarepuestos.megarepuestos.service.mapper.UserMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public void register(UserDTOin dto) {
        User user = UserMapper.MAPPER.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(user);
    }

    public UserDTO authenticate(UserDTOin dto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            dto.getEmail(),
                            dto.getPassword()
                    )
            );
            User user = userRepository.findByEmail(dto.getEmail()).orElseThrow();
            return new UserDTO(user.getId(), user.getEmail());
        }catch (Exception ex){
            throw new UnauthorizeException(Error.AUTH_ERROR);
        }
    }
}