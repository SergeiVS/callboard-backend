package org.callboard.controllers.api;

import jakarta.security.auth.message.AuthException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.callboard.dto.StandardResponse;
import org.callboard.dto.StandardStringRequest;
import org.callboard.dto.authDto.AuthResponse;
import org.callboard.dto.authDto.AuthenticationRequest;
import org.callboard.dto.userDto.NewUserRequest;
import org.callboard.dto.userDto.UserResponse;
import org.callboard.services.authService.AuthService;
import org.callboard.services.userServices.CreateUserService;
import org.callboard.services.userServices.FindUserByEmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
public interface AuthControllerInterface {


    @PostMapping
    ResponseEntity<AuthResponse> authenticateUser(@Valid @RequestBody AuthenticationRequest request) throws AuthException ;

    @PostMapping("/signing")
   ResponseEntity<StandardResponse> registerNewUser(@Valid @RequestBody NewUserRequest request) throws AuthException ;

    @GetMapping("/me")
   ResponseEntity<UserResponse> getUserData(@NotNull(message = "User is not authorized") Principal principal) throws AuthException;
}
