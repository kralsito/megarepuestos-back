package com.megarepuestos.megarepuestos.controller;


import com.megarepuestos.megarepuestos.service.FormService;
import com.megarepuestos.megarepuestos.service.dto.request.FormDTOin;
import com.megarepuestos.megarepuestos.service.dto.request.FormFilterDTO;
import com.megarepuestos.megarepuestos.service.dto.response.FormDTO;
import com.megarepuestos.megarepuestos.util.PaginationUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/form")
@Tag(name = "Form", description = "Form Endpoints")

public class FormController {

    private final FormService formService;

    public FormController(FormService formService) {
        this.formService = formService;
    }

    @PostMapping
    @Operation(summary = "Crea un formulario")
    public ResponseEntity<FormDTO> create(@RequestParam String firstName,
                                          @RequestParam String lastName,
                                          @RequestParam String phoneNumber){
        FormDTOin dto = new FormDTOin(firstName, lastName, phoneNumber);
        FormDTO response =  formService.create(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Obtiene formularios, con filtro", security = { @SecurityRequirement(name = "bearer-jwt") })
    public ResponseEntity<List<FormDTO>> getAll(@ParameterObject FormFilterDTO filterDTO, @ParameterObject Pageable pageable){
        Page<FormDTO> response = formService.getAll(filterDTO, pageable);
        HttpHeaders headers = PaginationUtil.setTotalCountPageHttpHeaders(response);
        return new ResponseEntity<>(response.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/exist")
    @Operation(summary = "Verifica si un numero de telefono ya está registrado")
    public ResponseEntity<Boolean> existsByPhoneNumber(@RequestParam String phoneNumber) {
        boolean exists = formService.existsByPhoneNumber(phoneNumber);
        return ResponseEntity.ok(exists);
    }
}
