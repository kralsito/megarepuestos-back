package com.megarepuestos.megarepuestos.controller;


import com.megarepuestos.megarepuestos.service.AuthService;
import com.megarepuestos.megarepuestos.service.dto.request.UserDTOin;
import com.megarepuestos.megarepuestos.service.dto.response.TokenResponse;
import com.megarepuestos.megarepuestos.service.dto.response.UserDTO;
import com.megarepuestos.megarepuestos.util.JwtUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth", description = "Auth Endpoints")
public class AuthController {

    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTOin userRequestDto) {
        authService.register(userRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody UserDTOin userRequestDto) {
        UserDTO authenticatedUser = authService.authenticate(userRequestDto);
        final String accessToken = JwtUtil.buildToken(authenticatedUser.getEmail(), authenticatedUser.getId());
        TokenResponse tokenResponse = new TokenResponse(accessToken, authenticatedUser);
        return ResponseEntity.ok(tokenResponse);
    }

}