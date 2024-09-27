package org.callboard.controllers.userController;

import org.callboard.dto.UserDTO.UserDataDTO;
import org.callboard.dto.UserDTO.UserRegistrationRequestDTO;
import org.callboard.services.UserService.UserRegistrationService;
import org.callboard.services.UserService.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class UserControllerImpl implements UserController {
    @Override
    public ResponseEntity<UserDataDTO> registerUser(UserRegistrationRequestDTO dto) {
        return null;
    }
    //private final UserRegistrationService userService;
}
//TODO