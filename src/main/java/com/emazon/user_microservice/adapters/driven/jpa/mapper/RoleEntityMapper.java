package com.emazon.user_microservice.adapters.driven.jpa.mapper;

import com.emazon.user_microservice.adapters.driven.jpa.entity.RoleEntity;
import com.emazon.user_microservice.domain.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleEntityMapper {
    Role toDomain(RoleEntity roleEntity);
    RoleEntity toEntity(Role role);
}