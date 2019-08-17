package com.github.bartoszpogoda.auth.api;

import com.github.bartoszpogoda.auth.dto.*;
import com.github.bartoszpogoda.auth.error.impl.RegistrationFailedError;
import com.github.bartoszpogoda.auth.service.AuthenticationService;
import com.github.bartoszpogoda.auth.service.TokenProviderService;
import com.github.bartoszpogoda.auth.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthApi {

    private final UserService userService;
    private final TokenProviderService tokenProviderService;
    private final AuthenticationService authenticationService;

    public AuthApi(UserService userService,
                   TokenProviderService tokenProviderService,
                   AuthenticationService authenticationService) {
        this.userService = userService;
        this.tokenProviderService = tokenProviderService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public RegistrationResponse register(@RequestBody @Valid RegistrationRequest registrationReq) {
        return this.userService.register(registrationReq)
                .map(this.tokenProviderService::provide)
                .map(RegistrationResponse::new)
                .orElseThrow(RegistrationFailedError::new);
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest loginReq) {
        return this.userService.findByEmail(loginReq.getEmail())
                .map(user -> {
                    boolean authenticated = this.authenticationService.authenticate(user, loginReq.getPlainPassword());
                    return authenticated ? user : null;
                })
                .map(user -> new LoginResponse(true, this.tokenProviderService.provide(user)))
                .orElse(new LoginResponse(false));
    }

    @PostMapping("/authorize")
    public AuthorizationResponse authorize(@Valid @RequestBody AuthorizationRequest authorizationReq) {
        return new AuthorizationResponse(true);
    }

}
