package com.megarepuestos.megarepuestos.service.mapper;

import com.megarepuestos.megarepuestos.model.Brand;
import com.megarepuestos.megarepuestos.service.dto.request.BrandDTOin;
import com.megarepuestos.megarepuestos.service.dto.response.BrandDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BrandMapper extends EntityMapper<BrandDTO, Brand>{
    BrandMapper MAPPER = Mappers.getMapper(BrandMapper.class);
    Brand toEntity(BrandDTOin dto);
}
