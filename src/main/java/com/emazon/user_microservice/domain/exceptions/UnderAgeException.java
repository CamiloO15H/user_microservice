package com.emazon.user_microservice.domain.exceptions;

import com.emazon.user_microservice.domain.utils.DomainConstants;

public class UnderAgeException extends RuntimeException {
    public UnderAgeException() {
        super(DomainConstants.UNDER_AGE_MESSAGE);
    }
}
