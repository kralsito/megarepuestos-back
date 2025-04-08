package com.megarepuestos.megarepuestos.service.dto.request;

import lombok.Data;

@Data
public class ReplacementFilterDTO {
    private String name;
    private Long brand_id;
    private Long typeReplacement_id;
}
