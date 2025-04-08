package com.megarepuestos.megarepuestos.service.dto.response;

import lombok.Data;

@Data
public class ReplacementDTO {
    private Long id;
    private String name;
    private BrandDTO brand;
    private TypeReplacementDTO typeReplacement;
}
