package com.megarepuestos.megarepuestos.service.mapper;


import com.megarepuestos.megarepuestos.model.Form;
import com.megarepuestos.megarepuestos.service.dto.request.FormDTOin;
import com.megarepuestos.megarepuestos.service.dto.response.FormDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface FormMapper extends EntityMapper<FormDTO, Form>{
    FormMapper MAPPER = Mappers.getMapper(FormMapper.class);
    Form toEntity(FormDTOin dto);
}
