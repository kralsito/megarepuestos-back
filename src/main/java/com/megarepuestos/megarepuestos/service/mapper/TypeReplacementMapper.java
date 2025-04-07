package com.megarepuestos.megarepuestos.service.mapper;

import com.megarepuestos.megarepuestos.model.TypeReplacement;
import com.megarepuestos.megarepuestos.service.dto.request.TypeReplacementDTOin;
import com.megarepuestos.megarepuestos.service.dto.response.TypeReplacementDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TypeReplacementMapper extends EntityMapper<TypeReplacementDTO, TypeReplacement> {
    TypeReplacementMapper MAPPER = Mappers.getMapper(TypeReplacementMapper.class);
    TypeReplacement toEntity(TypeReplacementDTOin dto);
}
