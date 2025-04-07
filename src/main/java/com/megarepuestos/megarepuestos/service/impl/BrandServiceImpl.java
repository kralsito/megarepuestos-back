package com.megarepuestos.megarepuestos.service.impl;

import com.megarepuestos.megarepuestos.exception.custom.BadRequestException;
import com.megarepuestos.megarepuestos.exception.error.Error;
import com.megarepuestos.megarepuestos.model.Brand;
import com.megarepuestos.megarepuestos.repository.BrandRepository;
import com.megarepuestos.megarepuestos.repository.specification.BrandSpec;
import com.megarepuestos.megarepuestos.service.BrandService;
import com.megarepuestos.megarepuestos.service.dto.request.BrandDTOin;
import com.megarepuestos.megarepuestos.service.dto.request.BrandFilterDTO;
import com.megarepuestos.megarepuestos.service.dto.response.BrandDTO;
import com.megarepuestos.megarepuestos.service.mapper.BrandMapper;
import com.megarepuestos.megarepuestos.util.AuthSupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public BrandDTO create(BrandDTOin dto) {
        Brand brand = BrandMapper.MAPPER.toEntity(dto);
        brand = brandRepository.save(brand);
        return BrandMapper.MAPPER.toDto(brand);
    }

    @Override
    public BrandDTO getById(Long id) {
        Brand brand = getBrand(id);
        return BrandMapper.MAPPER.toDto(brand);
    }

    @Override
    public Page<BrandDTO> getAll(BrandFilterDTO filter, Pageable pageable) {
        Specification<Brand> spec = BrandSpec.getSpec(filter);
        Page<Brand> page = brandRepository.findAll(spec, pageable);
        return page.map(BrandMapper.MAPPER::toDto);
    }

    @Override
    public void delete(Long id)  {
        Long userId = AuthSupport.getUserId();
        Brand brand = getBrand(id);
        if(userId == null){
            throw new BadRequestException(Error.USER_NOT_LOGIN);
        }
        brandRepository.delete(brand);
    }

    private Brand getBrand(Long id) {
        Optional<Brand> brandOptional = brandRepository.findById(id);
        if (brandOptional.isEmpty()) {
            throw new BadRequestException(Error.BRAND_NOT_FOUND);
        }
        return brandOptional.get();
    }
}
