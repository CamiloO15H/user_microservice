package com.emazon.user_microservice.configuration.utils;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import com.emazon.user_microservice.adapters.driven.jpa.entity.RoleEntity;
import com.emazon.user_microservice.adapters.driven.jpa.persistence.RoleRepository;
import com.emazon.user_microservice.domain.utils.RoleName;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final RoleRepository roleRepository;

    public DataLoader(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        createRoleIfNotExists(RoleName.WAREHOUSE_ASSISTANT);
        createRoleIfNotExists(RoleName.ADMIN);
        // Puedes añadir otros roles de la misma manera
    }

    private void createRoleIfNotExists(RoleName roleName) {
        if (!roleRepository.existsByRoleName(roleName)) {
            roleRepository.save(new RoleEntity(null, roleName));
        }
    }
}