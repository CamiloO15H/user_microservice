package com.emazon.user_microservice.adapters.driving.rest.service.impl;

import com.emazon.user_microservice.adapters.driven.jpa.entity.RoleEntity;
import com.emazon.user_microservice.adapters.driven.jpa.mapper.RoleEntityMapper;
import com.emazon.user_microservice.adapters.driven.jpa.persistence.RoleRepository;
import com.emazon.user_microservice.domain.model.Role;
import com.emazon.user_microservice.domain.utils.RoleName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl {

    private final RoleRepository roleRepository;
    private final RoleEntityMapper roleEntityMapper;

    public Role getOrCreateRole(RoleName roleName) {
        RoleEntity roleEntity = roleRepository.findByRoleName(roleName)
                .orElseGet(() -> roleRepository.save(new RoleEntity(null, roleName))); // Crea el rol si no existe
        return roleEntityMapper.toDomain(roleEntity);
    }
}