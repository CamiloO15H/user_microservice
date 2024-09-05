package com.emazon.user_microservice.domain.spi;

import com.emazon.user_microservice.domain.model.User;

public interface UserPersistencePort {
    void createUser(User user);
    boolean userExistsByIdentityDocument(String identityDocument);
    boolean userExistsByEmail(String email);
}
