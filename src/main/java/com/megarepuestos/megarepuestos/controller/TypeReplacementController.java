package com.megarepuestos.megarepuestos.controller;


import com.megarepuestos.megarepuestos.service.TypeReplacementService;
import com.megarepuestos.megarepuestos.service.dto.request.TypeReplacementDTOin;
import com.megarepuestos.megarepuestos.service.dto.request.TypeReplacementFilterDTO;
import com.megarepuestos.megarepuestos.service.dto.response.TypeReplacementDTO;
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
@RequestMapping("/typeReplacement")
@Tag(name = "TypeReplacement", description = "Replacement Type Endpoints")
public class TypeReplacementController {

    private final TypeReplacementService typeReplacementService;

    public TypeReplacementController(TypeReplacementService typeReplacementService) {
        this.typeReplacementService = typeReplacementService;
    }

    @PostMapping
    @Operation(summary = "Crea un tipo de repuesto", security = { @SecurityRequirement(name = "bearer-jwt") })
    public ResponseEntity<TypeReplacementDTO> create(@RequestParam String name){
        TypeReplacementDTOin dto = new TypeReplacementDTOin(name);
        TypeReplacementDTO response =  typeReplacementService.create(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Obtiene los tipos de repuesto, con filtro")
    public ResponseEntity<List<TypeReplacementDTO>> getAll(@ParameterObject TypeReplacementFilterDTO filterDTO, @ParameterObject Pageable pageable){
        Page<TypeReplacementDTO> response = typeReplacementService.getAll(filterDTO, pageable);
        HttpHeaders headers = PaginationUtil.setTotalCountPageHttpHeaders(response);
        return new ResponseEntity<>(response.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un tipo de repuesto por id")
    public ResponseEntity<TypeReplacementDTO> getById(@PathVariable Long id) {
        TypeReplacementDTO response = typeReplacementService.getById(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un tipo de repuesto", security = { @SecurityRequirement(name = "bearer-jwt") })
    public ResponseEntity<String> delete(@PathVariable Long id)  {
        typeReplacementService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
