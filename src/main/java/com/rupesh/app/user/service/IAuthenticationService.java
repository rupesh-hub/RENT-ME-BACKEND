package com.rupesh.app.user.service;

import com.rupesh.app.user.model.AuthenticationRequest;
import com.rupesh.app.user.model.AuthenticationResponse;
import com.rupesh.app.user.model.RegistrationRequest;
import com.rupesh.app.util.GlobalResponse;

public interface IAuthenticationService {
    void register(RegistrationRequest request);

    GlobalResponse<AuthenticationResponse> authenticate(AuthenticationRequest request);

    GlobalResponse<Void> confirmEmail(String email, String token);
}
