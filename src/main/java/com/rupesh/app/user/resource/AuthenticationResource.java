package com.rupesh.app.user.resource;

import com.rupesh.app.user.model.AuthenticationRequest;
import com.rupesh.app.user.model.RegistrationRequest;
import com.rupesh.app.user.model.UserProfileUpdateRequest;
import com.rupesh.app.user.service.IAuthenticationService;
import com.rupesh.app.util.GlobalResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/authentication")
@RequiredArgsConstructor
@Tag(name="Authentication")
public class AuthenticationResource {

    private final IAuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<GlobalResponse<?>> authenticate(
            @RequestBody @Valid AuthenticationRequest request
    ) {

        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<GlobalResponse<?>> register(
            @RequestBody @Valid RegistrationRequest request
    ) {
        authenticationService.register(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/confirm-email")
    public ResponseEntity<GlobalResponse<?>> confirmEmail(
            @RequestParam(name = "username") String email,
            @RequestParam(name = "token") String token
    ) {

        return ResponseEntity.ok(authenticationService.confirmEmail(email, token));
    }

    @PostMapping("/resend-confirmation-email")
    public ResponseEntity<GlobalResponse<?>> resendConfirmationEmail(
            @RequestParam(name = "email") String email
    ) {
        return ResponseEntity.ok(GlobalResponse.success("Password reset email sent successfully."));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<GlobalResponse<?>> forgotPassword(
            @RequestParam(name = "email") String email
    ) {
        return ResponseEntity.ok(GlobalResponse.success("Password reset email sent successfully."));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<GlobalResponse<?>> resetPassword(
            @RequestParam(name = "email") String email,
            @RequestParam(name = "token") String token,
            @RequestParam(name = "newPassword") String newPassword
    ) {
        return ResponseEntity.ok(GlobalResponse.success("Password reset successful."));
    }

    @PostMapping(path = "/change-password")
    public ResponseEntity<GlobalResponse<?>> changePassword(
            @RequestParam(name = "email") String email,
            @RequestParam(name = "oldPassword") String oldPassword,
            @RequestParam(name = "newPassword") String newPassword
    ) {
        return ResponseEntity.ok(GlobalResponse.success("Password updated successfully."));
    }

    @PutMapping("/profile")
    public ResponseEntity<GlobalResponse<?>> updateProfile(
            @RequestBody @Valid UserProfileUpdateRequest request
    ) {
        return ResponseEntity.ok(GlobalResponse.success("Profile updated successfully."));
    }

    @PostMapping("/logout")
    public ResponseEntity<GlobalResponse<?>> logout() {
        return ResponseEntity.ok(GlobalResponse.success("Logged out successfully."));
    }

    @PutMapping("/assign-role")
    public ResponseEntity<GlobalResponse<?>> assignRole(
            @RequestParam("roles") String[] roles,
            @RequestParam("email") String email,
            @RequestParam("userId") String userId
    ) {
        return ResponseEntity.ok(GlobalResponse.success("Logged out successfully."));
    }

    @PutMapping("/unassign-role")
    public ResponseEntity<GlobalResponse<?>> unassignRole(
            @RequestParam("role") String role,
            @RequestParam("email") String email,
            @RequestParam("userId") String userId
    ) {
        return ResponseEntity.ok(GlobalResponse.success("Logged out successfully."));
    }

}
