package com.megarepuestos.megarepuestos.controller;


import com.megarepuestos.megarepuestos.service.BrandService;
import com.megarepuestos.megarepuestos.service.dto.request.BrandDTOin;
import com.megarepuestos.megarepuestos.service.dto.request.BrandFilterDTO;
import com.megarepuestos.megarepuestos.service.dto.response.BrandDTO;
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
@RequestMapping("/brand")
@Tag(name = "Brand", description = "Brand Endpoints")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping
    @Operation(summary = "Crea una marca", security = { @SecurityRequirement(name = "bearer-jwt") })
    public ResponseEntity<BrandDTO> create(@RequestParam String name){
        BrandDTOin dto = new BrandDTOin(name);
        BrandDTO response =  brandService.create(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Obtiene las marcas, con filtro")
    public ResponseEntity<List<BrandDTO>> getAll(@ParameterObject BrandFilterDTO filterDTO, @ParameterObject Pageable pageable){
        Page<BrandDTO> response = brandService.getAll(filterDTO, pageable);
        HttpHeaders headers = PaginationUtil.setTotalCountPageHttpHeaders(response);
        return new ResponseEntity<>(response.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene las marcas por id")
    public ResponseEntity<BrandDTO> getById(@PathVariable Long id) {
        BrandDTO response = brandService.getById(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina una marca", security = { @SecurityRequirement(name = "bearer-jwt") })
    public ResponseEntity<String> delete(@PathVariable Long id)  {
        brandService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
