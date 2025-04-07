package com.megarepuestos.megarepuestos.service;

import com.megarepuestos.megarepuestos.service.dto.request.BrandDTOin;
import com.megarepuestos.megarepuestos.service.dto.request.BrandFilterDTO;
import com.megarepuestos.megarepuestos.service.dto.response.BrandDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BrandService {
    BrandDTO create(BrandDTOin dto);

    Page<BrandDTO> getAll(BrandFilterDTO filter, Pageable pageable);

    BrandDTO getById(Long id);

    void delete(Long id);
}
