package com.emazon.user_microservice.domain.api.usecase;

import com.emazon.user_microservice.adapters.driving.rest.service.impl.RoleServiceImpl;
import com.emazon.user_microservice.domain.api.UserServicePort;
import com.emazon.user_microservice.domain.exceptions.UserDocumentAlreadyExistsException;
import com.emazon.user_microservice.domain.exceptions.UserEmailAlreadyExistsException;
import com.emazon.user_microservice.domain.model.Role;
import com.emazon.user_microservice.domain.model.User;
import com.emazon.user_microservice.domain.spi.UserPersistencePort;
import com.emazon.user_microservice.domain.utils.RoleName;

import static com.emazon.user_microservice.domain.utils.ValidationUtils.validateBirthDate;

public class UserUseCase implements UserServicePort {

    private final UserPersistencePort userPersistencePort;
    private final RoleServiceImpl roleServiceImpl;

    public UserUseCase(UserPersistencePort userPersistencePort, RoleServiceImpl roleServiceImpl) {
        this.userPersistencePort = userPersistencePort;
        this.roleServiceImpl = roleServiceImpl;
    }

    private void createUser(User user) {
        validateBirthDate(user.getBirthdate());
        if(userPersistencePort.userExistsByEmail(user.getEmail())) throw new UserEmailAlreadyExistsException(user.getEmail());
        if(userPersistencePort.userExistsByIdentityDocument(user.getIdentityDocument())) throw new UserDocumentAlreadyExistsException(user.getIdentityDocument());
        userPersistencePort.createUser(user);
    }

    @Override
    public void createWarehouseAssistant(User user) {
        Role role = roleServiceImpl.getOrCreateRole(RoleName.WAREHOUSE_ASSISTANT); // Usa el RoleService para buscar/crear el rol
        user.setRole(role); // Asigna el rol existente al usuario
        createUser(user);
    }
}
