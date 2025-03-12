package com.megarepuestos.megarepuestos.controller;

import com.megarepuestos.megarepuestos.service.ProductService;
import com.megarepuestos.megarepuestos.service.dto.request.ProductDTOin;
import com.megarepuestos.megarepuestos.service.dto.request.ProductFilterDTO;
import com.megarepuestos.megarepuestos.service.dto.response.ProductDTO;
import com.megarepuestos.megarepuestos.util.PaginationUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/product")
@Tag(name = "Product", description = "Product Endpoints")

public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Crea un producto", security = { @SecurityRequirement(name = "bearer-jwt") })
    public ResponseEntity<ProductDTO> create(@RequestParam String name,
                                            @RequestParam String description,
                                            @RequestParam MultipartFile image){
        ProductDTOin dto = new ProductDTOin(name, description, image);
        ProductDTO response =  productService.create(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Obtiene productos, con filtro")
    public ResponseEntity<List<ProductDTO>> getAll(@ParameterObject ProductFilterDTO filterDTO, @ParameterObject Pageable pageable){
        Page<ProductDTO> response = productService.getAll(filterDTO, pageable);
        HttpHeaders headers = PaginationUtil.setTotalCountPageHttpHeaders(response);
        return new ResponseEntity<>(response.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene los productos por id")
    public ResponseEntity<ProductDTO> getById(@PathVariable Long id) {
        ProductDTO response = productService.getById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Modifica un producto", security = { @SecurityRequirement(name = "bearer-jwt") })
    public ResponseEntity<ProductDTO> update(@PathVariable Long id,
                                            @RequestParam String name,
                                            @RequestParam String description,
                                            @RequestParam(required = false) MultipartFile image) {
        ProductDTOin dto = new ProductDTOin(name, description, image);
        ProductDTO response = productService.update(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un producto", security = { @SecurityRequirement(name = "bearer-jwt") })
    public ResponseEntity<String> delete(@PathVariable Long id)  {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

