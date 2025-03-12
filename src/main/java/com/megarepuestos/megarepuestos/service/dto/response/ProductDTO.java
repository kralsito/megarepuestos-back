package com.megarepuestos.megarepuestos.service.dto.response;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private String s3Url;
}
