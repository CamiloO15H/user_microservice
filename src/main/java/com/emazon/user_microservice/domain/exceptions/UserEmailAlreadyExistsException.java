package com.emazon.user_microservice.domain.exceptions;

import com.emazon.user_microservice.domain.utils.DomainConstants;

public class UserEmailAlreadyExistsException extends RuntimeException {
    public UserEmailAlreadyExistsException(String email) {
        super(String.format(DomainConstants.USER_EMAIL_ALREADY_EXISTS_MESSAGE, email));
    }
}
