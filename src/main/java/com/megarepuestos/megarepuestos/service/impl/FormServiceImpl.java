package com.megarepuestos.megarepuestos.service.impl;

import com.megarepuestos.megarepuestos.exception.custom.BadRequestException;
import com.megarepuestos.megarepuestos.exception.error.Error;
import com.megarepuestos.megarepuestos.model.Form;
import com.megarepuestos.megarepuestos.repository.FormRepository;
import com.megarepuestos.megarepuestos.repository.specification.FormSpec;
import com.megarepuestos.megarepuestos.service.FormService;
import com.megarepuestos.megarepuestos.service.dto.request.FormDTOin;
import com.megarepuestos.megarepuestos.service.dto.request.FormFilterDTO;
import com.megarepuestos.megarepuestos.service.dto.response.FormDTO;
import com.megarepuestos.megarepuestos.service.mapper.FormMapper;
import com.megarepuestos.megarepuestos.util.AuthSupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class FormServiceImpl implements FormService {


    private final FormRepository formRepository;


    public FormServiceImpl(FormRepository formRepository) {
        this.formRepository = formRepository;
    }

    @Override
    public FormDTO create(FormDTOin dto) {
        if (formRepository.existsByPhoneNumber(dto.getPhoneNumber())) {
            throw new BadRequestException(Error.PHONE_NUMBER_ALREADY_REGISTERED);
        }

        Form form = FormMapper.MAPPER.toEntity(dto);
        form = formRepository.save(form);
        return FormMapper.MAPPER.toDto(form);
    }

    @Override
    public FormDTO getById(Long id) {
        Form form = getForm(id);
        return FormMapper.MAPPER.toDto(form);
    }

    @Override
    public Page<FormDTO> getAll(FormFilterDTO filter, Pageable pageable) {
        Specification<Form> spec = FormSpec.getSpec(filter);
        Page<Form> page = formRepository.findAll(spec, pageable);
        return page.map(FormMapper.MAPPER::toDto);
    }

    @Override
    public boolean existsByPhoneNumber(String phoneNumber) {
        return formRepository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public void delete(Long id)  {
        Long userId = AuthSupport.getUserId();
        Form form = getForm(id);
        if(userId == null){
            throw new BadRequestException(Error.USER_NOT_LOGIN);
        }
        formRepository.delete(form);
    }

    private Form getForm(Long id) {
        Optional<Form> formOptional = formRepository.findById(id);
        if (formOptional.isEmpty()) {
            throw new BadRequestException(Error.FORM_NOT_FOUND);
        }
        return formOptional.get();
    }
}
