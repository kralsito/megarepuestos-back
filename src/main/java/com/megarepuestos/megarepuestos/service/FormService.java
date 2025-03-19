package com.megarepuestos.megarepuestos.service;

import com.megarepuestos.megarepuestos.service.dto.request.FormDTOin;
import com.megarepuestos.megarepuestos.service.dto.request.FormFilterDTO;
import com.megarepuestos.megarepuestos.service.dto.response.FormDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FormService {

    FormDTO create(FormDTOin dto);

    Page<FormDTO> getAll(FormFilterDTO filter, Pageable pageable);

    boolean existsByPhoneNumber(String phoneNumber);

}
