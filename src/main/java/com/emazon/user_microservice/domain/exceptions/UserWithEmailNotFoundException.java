package com.emazon.user_microservice.domain.exceptions;

import com.emazon.user_microservice.domain.utils.DomainConstants;

public class UserWithEmailNotFoundException extends RuntimeException {
    public UserWithEmailNotFoundException(String email) {
        super(String.format(DomainConstants.USER_WITH_EMAIL_NOT_FOUND_MESSAGE, email));
    }
}
