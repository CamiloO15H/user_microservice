package com.emazon.user_microservice.domain.exceptions;

import com.emazon.user_microservice.domain.utils.DomainConstants;

public class EmptyFieldException extends RuntimeException {
    public EmptyFieldException(String field) {
        super(String.format(DomainConstants.EMPTY_FIELD_MESSAGE, field));
    }
}
