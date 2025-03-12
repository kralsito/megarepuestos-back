package com.megarepuestos.megarepuestos.service.mapper;

import com.megarepuestos.megarepuestos.model.Product;
import com.megarepuestos.megarepuestos.service.dto.request.ProductDTOin;
import com.megarepuestos.megarepuestos.service.dto.response.ProductDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper extends EntityMapper<ProductDTO, Product>{
    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);
    Product toEntity(ProductDTOin dto);

    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "imageId")
    void update(@MappingTarget Product entity, Product updateEntity);
}
