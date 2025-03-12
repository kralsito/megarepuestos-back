package com.megarepuestos.megarepuestos.service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Data
public class ProductDTOin {
    private String name;
    private String description;
    private MultipartFile image;
}
