package com.emazon.user_microservice.domain.api;

import com.emazon.user_microservice.domain.model.User;

public interface UserServicePort {
    void createWarehouseAssistant(User user);
}
