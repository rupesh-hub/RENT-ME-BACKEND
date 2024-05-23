package com.rupesh.app.user.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequest {

    @NotEmpty(message="Email is required.")
    @NotBlank(message="Email is required.")
    private String username;

    @NotEmpty(message="Password is required.")
    @NotBlank(message="Password is required.")
    private String password;

}
