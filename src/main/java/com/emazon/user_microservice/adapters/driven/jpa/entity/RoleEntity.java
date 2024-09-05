package com.emazon.user_microservice.adapters.driven.jpa.entity;

import com.emazon.user_microservice.domain.utils.RoleName;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Entity
@Table(name = "role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class RoleEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role_name", unique = true, nullable = false)
    private RoleName roleName;
}