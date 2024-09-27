package org.callboard.controllers.userController;

import org.callboard.dto.UserDTO.UserDataDTO;
import org.callboard.dto.UserDTO.UserRegistrationRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface UserController {
    ResponseEntity<UserDataDTO> registerUser (UserRegistrationRequestDTO dto);
}
