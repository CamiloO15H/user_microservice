package com.emazon.user_microservice.configuration;

import lombok.RequiredArgsConstructor;
import com.emazon.user_microservice.adapters.driven.jpa.adapter.UserJpaAdapter;
import com.emazon.user_microservice.adapters.driven.jpa.mapper.UserEntityMapper;
import com.emazon.user_microservice.adapters.driven.jpa.persistence.RoleRepository;
import com.emazon.user_microservice.adapters.driven.jpa.persistence.UserRepository;
import com.emazon.user_microservice.adapters.driving.rest.service.impl.RoleServiceImpl;
import com.emazon.user_microservice.domain.api.UserServicePort;
import com.emazon.user_microservice.domain.api.usecase.UserUseCase;
import com.emazon.user_microservice.domain.exceptions.UserWithEmailNotFoundException;
import com.emazon.user_microservice.domain.spi.UserPersistencePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;
    private final RoleRepository roleRepository;
    private final RoleServiceImpl roleServiceImpl;

    @Bean
    UserPersistencePort userPersistencePort() {
        return new UserJpaAdapter(userRepository, userEntityMapper, roleRepository);
    }

    @Bean
    UserServicePort userServicePort() {
        return new UserUseCase(userPersistencePort(),roleServiceImpl);
    }

    // Service

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    UserDetailsService userDetailsService() {
        return email -> userRepository.findByEmail(email)
                .orElseThrow(() -> new UserWithEmailNotFoundException(email));
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

}
