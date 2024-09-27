package org.callboard.controllers.authControllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.callboard.dto.UserDTO.UserDataDTO;
import org.callboard.dto.UserDTO.UserRegistrationRequestDTO;
import org.callboard.services.UserService.UserRegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/auth/")
@RequiredArgsConstructor
public class AuthController {
    private final UserRegistrationService registrationService;

    @PostMapping("/registration")
    ResponseEntity<UserDataDTO> registerNewUser(@Valid @RequestBody UserRegistrationRequestDTO dto) {
        return registrationService.doService(dto);
    }

}
