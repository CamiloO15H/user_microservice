package com.emazon.user_microservice.domain.utils;

import com.emazon.user_microservice.domain.exceptions.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Slf4j
public class ValidationUtils {
    private ValidationUtils() {
        throw new IllegalStateException("Utility class");
    }
    public static void validateBirthDate(LocalDateTime birthDate) {
        if(birthDate.until(LocalDateTime.now(), ChronoUnit.YEARS) < 18) throw new UnderAgeException();
    }
}
