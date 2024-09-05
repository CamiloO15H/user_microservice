package com.emazon.user_microservice.adapters.driving.rest.controller;

import com.emazon.user_microservice.adapters.driving.rest.dto.request.UserRequest;
import com.emazon.user_microservice.adapters.driving.rest.dto.response.RegisterResponse;
import com.emazon.user_microservice.adapters.driving.rest.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @Operation(summary = "Register a new warehouse assistant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Assistant has been registered"),
            @ApiResponse(responseCode = "409", description = "User with that email already exists"),
            @ApiResponse(responseCode = "409", description = "User with that identity document already exists"),
            @ApiResponse(responseCode = "409", description = "User is under aged"),
            @ApiResponse(responseCode = "400", description = "Some of the field doesn't pass validations"),
    })
    @PostMapping("/register/warehouse-assistant")
    public ResponseEntity<RegisterResponse> registerWarehouseAssistant(@RequestBody @Valid UserRequest userRequest){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.createWarehouseAssistant(userRequest));
    }
}
