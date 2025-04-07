package com.megarepuestos.megarepuestos.service.impl;

import com.megarepuestos.megarepuestos.exception.custom.BadRequestException;
import com.megarepuestos.megarepuestos.exception.error.Error;
import com.megarepuestos.megarepuestos.model.TypeReplacement;
import com.megarepuestos.megarepuestos.repository.TypeReplacementRepository;
import com.megarepuestos.megarepuestos.repository.specification.TypeReplacementSpec;
import com.megarepuestos.megarepuestos.service.TypeReplacementService;
import com.megarepuestos.megarepuestos.service.dto.request.TypeReplacementDTOin;
import com.megarepuestos.megarepuestos.service.dto.request.TypeReplacementFilterDTO;
import com.megarepuestos.megarepuestos.service.dto.response.TypeReplacementDTO;
import com.megarepuestos.megarepuestos.service.mapper.TypeReplacementMapper;
import com.megarepuestos.megarepuestos.util.AuthSupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TypeReplacementServiceImpl implements TypeReplacementService {

    private final TypeReplacementRepository typeReplacementRepository;

    public TypeReplacementServiceImpl(TypeReplacementRepository typeReplacementRepository) {
        this.typeReplacementRepository = typeReplacementRepository;
    }

    @Override
    public TypeReplacementDTO create(TypeReplacementDTOin dto) {
        TypeReplacement typeReplacement = TypeReplacementMapper.MAPPER.toEntity(dto);
        typeReplacement = typeReplacementRepository.save(typeReplacement);
        return TypeReplacementMapper.MAPPER.toDto(typeReplacement);
    }

    @Override
    public TypeReplacementDTO getById(Long id) {
        TypeReplacement typeReplacement = getTypeReplacement(id);
        return TypeReplacementMapper.MAPPER.toDto(typeReplacement);
    }

    @Override
    public Page<TypeReplacementDTO> getAll(TypeReplacementFilterDTO filter, Pageable pageable) {
        Specification<TypeReplacement> spec = TypeReplacementSpec.getSpec(filter);
        Page<TypeReplacement> page = typeReplacementRepository.findAll(spec, pageable);
        return page.map(TypeReplacementMapper.MAPPER::toDto);
    }

    @Override
    public void delete(Long id)  {
        Long userId = AuthSupport.getUserId();
        TypeReplacement typeReplacement = getTypeReplacement(id);
        if(userId == null){
            throw new BadRequestException(Error.USER_NOT_LOGIN);
        }
        typeReplacementRepository.delete(typeReplacement);
    }

    private TypeReplacement getTypeReplacement(Long id) {
        Optional<TypeReplacement> typeReplacementOptional = typeReplacementRepository.findById(id);
        if (typeReplacementOptional.isEmpty()) {
            throw new BadRequestException(Error.TYPE_REPLACEMENT_NOT_FOUND);
        }
        return typeReplacementOptional.get();
    }
}
