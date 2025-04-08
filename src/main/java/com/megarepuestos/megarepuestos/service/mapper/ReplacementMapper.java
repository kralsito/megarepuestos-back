package com.megarepuestos.megarepuestos.service.mapper;


import com.megarepuestos.megarepuestos.model.Replacement;
import com.megarepuestos.megarepuestos.service.dto.request.ReplacementDTOin;
import com.megarepuestos.megarepuestos.service.dto.response.ReplacementDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ReplacementMapper extends EntityMapper<ReplacementDTO, Replacement>{
    ReplacementMapper MAPPER = Mappers.getMapper(ReplacementMapper.class);
    Replacement toEntity(ReplacementDTOin dto);
}
