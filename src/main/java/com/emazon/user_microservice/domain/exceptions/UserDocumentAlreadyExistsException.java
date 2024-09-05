package com.emazon.user_microservice.domain.exceptions;

import com.emazon.user_microservice.domain.utils.DomainConstants;

public class UserDocumentAlreadyExistsException extends RuntimeException {
    public UserDocumentAlreadyExistsException(String email) {
        super(String.format(DomainConstants.USER_DOCUMENT_ALREADY_EXISTS_MESSAGE, email));
    }
}
