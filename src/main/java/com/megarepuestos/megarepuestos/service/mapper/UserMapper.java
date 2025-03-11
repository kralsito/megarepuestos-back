package com.megarepuestos.megarepuestos.service.mapper;

import com.megarepuestos.megarepuestos.model.User;
import com.megarepuestos.megarepuestos.service.dto.request.UserDTOin;
import com.megarepuestos.megarepuestos.service.dto.response.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends EntityMapper<UserDTO, User>{
    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "password", source = "dto", qualifiedByName = "encodePassword")
    User toEntity(UserDTOin dto);

    @Named("encodePassword")
    default String getBusinessConsumer(UserDTOin dto){
        return PasswordEncodedUtil.encode(dto.getPassword());
    }
}
