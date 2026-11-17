package io.github.asmolenkov.cloudfilestorage.controllers;

import io.github.asmolenkov.cloudfilestorage.dto.request.RegisterRequest;
import io.github.asmolenkov.cloudfilestorage.dto.response.AuthResponse;
import io.github.asmolenkov.cloudfilestorage.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AuthResponse> singUp(@Valid @RequestBody RegisterRequest registerRequest) {
        AuthResponse authResponse = authService.singUp(registerRequest);
        log.info("User {} sign up successful", registerRequest.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(authResponse);
    }
}
