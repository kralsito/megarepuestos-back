package com.megarepuestos.megarepuestos.service;

import com.megarepuestos.megarepuestos.service.dto.request.TypeReplacementDTOin;
import com.megarepuestos.megarepuestos.service.dto.request.TypeReplacementFilterDTO;
import com.megarepuestos.megarepuestos.service.dto.response.TypeReplacementDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TypeReplacementService {
    TypeReplacementDTO create(TypeReplacementDTOin dto);

    Page<TypeReplacementDTO> getAll(TypeReplacementFilterDTO filter, Pageable pageable);

    TypeReplacementDTO getById(Long id);

    void delete(Long id);
}
