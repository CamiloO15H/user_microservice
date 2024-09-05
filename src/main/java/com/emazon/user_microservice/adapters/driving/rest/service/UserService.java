package com.emazon.user_microservice.adapters.driving.rest.service;

import com.emazon.user_microservice.adapters.driving.rest.dto.request.UserRequest;
import com.emazon.user_microservice.adapters.driving.rest.dto.response.RegisterResponse;

public interface UserService {
    RegisterResponse createWarehouseAssistant(UserRequest userRequest);
}