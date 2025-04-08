package com.megarepuestos.megarepuestos.service.impl;


import com.megarepuestos.megarepuestos.exception.custom.BadRequestException;
import com.megarepuestos.megarepuestos.model.Brand;
import com.megarepuestos.megarepuestos.model.Replacement;
import com.megarepuestos.megarepuestos.model.TypeReplacement;
import com.megarepuestos.megarepuestos.model.User;
import com.megarepuestos.megarepuestos.repository.BrandRepository;
import com.megarepuestos.megarepuestos.repository.ReplacementRepository;
import com.megarepuestos.megarepuestos.repository.TypeReplacementRepository;
import com.megarepuestos.megarepuestos.repository.UserRepository;
import com.megarepuestos.megarepuestos.repository.specification.ReplacementSpec;
import com.megarepuestos.megarepuestos.service.ReplacementService;
import com.megarepuestos.megarepuestos.service.dto.request.ReplacementDTOin;
import com.megarepuestos.megarepuestos.service.dto.request.ReplacementFilterDTO;
import com.megarepuestos.megarepuestos.service.dto.response.ReplacementDTO;
import com.megarepuestos.megarepuestos.service.mapper.ReplacementMapper;
import com.megarepuestos.megarepuestos.util.AuthSupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.megarepuestos.megarepuestos.exception.error.Error;

import java.util.Optional;

@Service
public class ReplacementServiceImpl implements ReplacementService {

    private final ReplacementRepository replacementRepository;

    private final UserRepository userRepository;

    private final BrandRepository brandRepository;

    private final TypeReplacementRepository typeReplacementRepository;

    public ReplacementServiceImpl(ReplacementRepository replacementRepository, UserRepository userRepository, BrandRepository brandRepository, TypeReplacementRepository typeReplacementRepository)
    {
        this.replacementRepository = replacementRepository;
        this.userRepository = userRepository;
        this.brandRepository = brandRepository;
        this.typeReplacementRepository = typeReplacementRepository;
    }

    @Override
    public ReplacementDTO create(ReplacementDTOin dto) {
        Long userId = AuthSupport.getUserId();
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new BadRequestException(Error.USER_NOT_LOGIN);
        }
        Replacement replacement =  ReplacementMapper.MAPPER.toEntity(dto);
        Brand brand = getBrand(dto);
        replacement.setBrand(brand);
        TypeReplacement typeReplacement = getTypeReplacement(dto);
        replacement.setTypeReplacement(typeReplacement);
        replacement = replacementRepository.save(replacement);
        return ReplacementMapper.MAPPER.toDto(replacement);
    }

    @Override
    public Page<ReplacementDTO> getAll(ReplacementFilterDTO filter, Pageable pageable) {
        Specification<Replacement> spec = ReplacementSpec.getSpec(filter);
        Page<Replacement> page = replacementRepository.findAll(spec, pageable);
        return page.map(ReplacementMapper.MAPPER::toDto);
    }

    @Override
    public ReplacementDTO getById(Long id) {
        Replacement replacement = getReplacement(id);
        return ReplacementMapper.MAPPER.toDto(replacement);
    }

    @Override
    public void delete(Long id)  {
        Long userId = AuthSupport.getUserId();
        Replacement replacement = getReplacement(id);
        if(userId == null){
            throw new BadRequestException(Error.USER_NOT_LOGIN);
        }
        replacementRepository.delete(replacement);
    }

    private Brand getBrand(ReplacementDTOin dto) {
        Optional<Brand> brandOptional = brandRepository.findById(dto.getBrand_id());
        if (brandOptional.isEmpty()) {
            throw new BadRequestException(Error.BRAND_NOT_FOUND);
        }
        Brand brand = brandOptional.get();
        brand.setId(dto.getBrand_id());
        return brand;
    }

    private TypeReplacement getTypeReplacement(ReplacementDTOin dto) {
        Optional<TypeReplacement> typeReplacementOptional = typeReplacementRepository.findById(dto.getTypeReplacement_id());
        if (typeReplacementOptional.isEmpty()) {
            throw new BadRequestException(Error.TYPE_REPLACEMENT_NOT_FOUND);
        }
        TypeReplacement typeReplacement = typeReplacementOptional.get();
        typeReplacement.setId(dto.getTypeReplacement_id());
        return typeReplacement;
    }

    private Replacement getReplacement(Long id) {
        Optional<Replacement> replacementOptional = replacementRepository.findById(id);
        if (replacementOptional.isEmpty()) {
            throw new BadRequestException(Error.REPLACEMENT_NOT_FOUND);
        }
        return replacementOptional.get();
    }
}
