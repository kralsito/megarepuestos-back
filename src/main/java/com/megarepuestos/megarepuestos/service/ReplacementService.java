package com.megarepuestos.megarepuestos.service;

import com.megarepuestos.megarepuestos.service.dto.request.ReplacementDTOin;
import com.megarepuestos.megarepuestos.service.dto.request.ReplacementFilterDTO;
import com.megarepuestos.megarepuestos.service.dto.response.ReplacementDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReplacementService {

    ReplacementDTO create(ReplacementDTOin dto);

    Page<ReplacementDTO> getAll(ReplacementFilterDTO filter, Pageable pageable);

    ReplacementDTO getById(Long id);

    void delete(Long id);

}
