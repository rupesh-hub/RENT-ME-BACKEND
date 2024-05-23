package com.rupesh.app.user.service;

import com.rupesh.app.email.EmailRequest;
import com.rupesh.app.notification.INotificationService;
import com.rupesh.app.role.repository.RoleRepository;
import com.rupesh.app.security.util.TokenService;
import com.rupesh.app.token.entity.Token;
import com.rupesh.app.token.repository.TokenRepository;
import com.rupesh.app.user.entity.User;
import com.rupesh.app.user.enumeration.TemplateName;
import com.rupesh.app.user.model.AuthenticationRequest;
import com.rupesh.app.user.model.AuthenticationResponse;
import com.rupesh.app.user.model.RegistrationRequest;
import com.rupesh.app.user.repository.UserRepository;
import com.rupesh.app.util.GlobalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class
AuthenticationService implements IAuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;
    private final INotificationService<EmailRequest> emailService;

    @Override
    public void register(RegistrationRequest request) {
        var role = roleRepository.findByName("USER")
                .orElseThrow(() -> new IllegalStateException("Could not find role 'USER'"));

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(List.of(role))
                .build();

        userRepository.save(user);
        sendConfirmationEmail(user);
    }

    @Override
    public GlobalResponse<AuthenticationResponse> authenticate(AuthenticationRequest request) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        Map<String, Object> claims = new HashMap<>();
        var user = (User) auth.getPrincipal();
        claims.put("name", user.fullName());
        claims.put("username", user.getEmail());
        claims.put("authorities", user.getAuthorities());

        var token = TokenService.generateToken(claims, user);

        return GlobalResponse.success(
                AuthenticationResponse
                        .builder()
                        .username(user.getEmail())
                        .token(token)
                        .build()
        );
    }

    @Override
    public GlobalResponse<Void> confirmEmail(String email, String token) {

        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User " + email + " not found."));

        var tokenEntity = tokenRepository.findByTokenAndUserId(token, user.getId())
                .orElseThrow(() -> new IllegalStateException("Invalid token."));

        if (LocalDateTime.now().isBefore(tokenEntity.getExpiresAt())) {
            user.setEnabled(true);
            userRepository.save(user);
            tokenEntity.setValidatedAt(LocalDateTime.now());
            tokenRepository.save(tokenEntity);

            return GlobalResponse.success("Your email has been confirmed.");
        } else {
            tokenRepository.delete(tokenEntity);
            sendConfirmationEmail(user);
            throw new IllegalStateException("""
                    Token has been expired.
                    A new token has been sent to your email.
                    """);
        }

    }

    private String confirmationUrl() {
        return "http://localhost:8088/api/v1.0.0/authentication/confirm-email";
    }

    private void sendConfirmationEmail(User user) {
        String code = generateCode(6);
        Token token = Token
                .builder()
                .user(user)
                .token(code)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(10))
                .build();
        tokenRepository.save(token);

        EmailRequest emailRequest = EmailRequest
                .builder()
                .to(user.getEmail())
                .name(user.fullName())
                .username(user.getEmail())
                .confirmationUrl(confirmationUrl())
                .template(TemplateName.ACTIVATE_ACCOUNT)
                .token(code)
                .subject("Account confirmation")
                .build();

        emailService.send(emailRequest);
    }

    private String generateCode(int length) {
        String characters = "0123456789";
        SecureRandom secureRandom = new SecureRandom();

        return secureRandom.ints(length, 0, characters.length())
                .mapToObj(characters::charAt)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

}
