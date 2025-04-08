package com.megarepuestos.megarepuestos.service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ReplacementDTOin {
    private String name;
    private Long brand_id;
    private Long typeReplacement_id;
}
