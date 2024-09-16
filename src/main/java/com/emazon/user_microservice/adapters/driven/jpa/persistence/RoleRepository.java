package com.emazon.user_microservice.adapters.driven.jpa.persistence;

import com.emazon.user_microservice.adapters.driven.jpa.entity.RoleEntity;
import com.emazon.user_microservice.domain.utils.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByRoleName(RoleName roleName);
    boolean existsByRoleName(RoleName roleName);
}
