package com.megarepuestos.megarepuestos.controller;

import com.megarepuestos.megarepuestos.service.ReplacementService;
import com.megarepuestos.megarepuestos.service.dto.request.ReplacementDTOin;
import com.megarepuestos.megarepuestos.service.dto.request.ReplacementFilterDTO;
import com.megarepuestos.megarepuestos.service.dto.response.ReplacementDTO;
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
import com.megarepuestos.megarepuestos.util.PaginationUtil;

import java.util.List;

@RestController
@RequestMapping("/replacement")
@Tag(name = "Replacement", description = "Replacement Endpoints")
public class ReplacementController {

    private final ReplacementService replacementService;

    public ReplacementController(ReplacementService replacementService) {this.replacementService = replacementService;}

    @PostMapping
    @Operation(summary = "crea un repuesto", security = { @SecurityRequirement(name = "bearer-jwt") })
    public ResponseEntity<ReplacementDTO> create(@RequestParam String name,
                                                 @RequestParam Long brand_id,
                                                 @RequestParam Long typeReplacement_id){
        ReplacementDTOin dto = new ReplacementDTOin(name, brand_id, typeReplacement_id);
        ReplacementDTO response =  replacementService.create(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Obtiene repuestos, con filtro")
    public ResponseEntity<List<ReplacementDTO>> getAll(@ParameterObject ReplacementFilterDTO filterDTO, @ParameterObject Pageable pageable){
        Page<ReplacementDTO> response = replacementService.getAll(filterDTO, pageable);
        HttpHeaders headers = PaginationUtil.setTotalCountPageHttpHeaders(response);
        return new ResponseEntity<>(response.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un repuesto por id", security = { @SecurityRequirement(name = "bearer-jwt") })
    public ResponseEntity<ReplacementDTO> getById(@PathVariable Long id) {
        ReplacementDTO response = replacementService.getById(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un repuesto", security = { @SecurityRequirement(name = "bearer-jwt") })
    public ResponseEntity<String> delete(@PathVariable Long id)  {
        replacementService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
