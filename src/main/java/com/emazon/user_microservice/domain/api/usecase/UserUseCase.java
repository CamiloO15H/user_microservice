package com.emazon.user_microservice.domain.api.usecase;

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

    public UserUseCase(UserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    private void createUser(User user) {
        validateBirthDate(user.getBirthdate());
        if(userPersistencePort.userExistsByEmail(user.getEmail())) throw new UserEmailAlreadyExistsException(user.getEmail());
        if(userPersistencePort.userExistsByIdentityDocument(user.getIdentityDocument())) throw new UserDocumentAlreadyExistsException(user.getIdentityDocument());
        userPersistencePort.createUser(user);
    }

    @Override
    public void createWarehouseAssistant(User user) {
        user.setRole(new Role(null, RoleName.WAREHOUSE_ASSISTANT));
        createUser(user);
    }
}
