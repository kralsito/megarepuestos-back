package com.megarepuestos.megarepuestos.service;


import com.megarepuestos.megarepuestos.service.dto.request.UserDTOin;
import com.megarepuestos.megarepuestos.service.dto.response.UserDTO;

public interface AuthService {

    /**
     * Registra un usuario
     * @param dto
     */
    void register(UserDTOin dto);

    /**
     * Autentica al usuario
     * @param dto
     * @return
     */
    UserDTO authenticate(UserDTOin dto);
}
