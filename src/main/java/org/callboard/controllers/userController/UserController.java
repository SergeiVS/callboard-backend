package org.callboard.controllers.userController;

import jakarta.security.auth.message.AuthException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.callboard.dto.StandardResponse;
import org.callboard.dto.userDto.UpdateUserRequest;
import org.callboard.entities.User;
import org.callboard.repositories.UserRepository;
import org.callboard.services.userServices.UpdateUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final UpdateUserService updateUserService;

    @GetMapping
    List<User> getAllUser() {
        return userRepository.findAll();
    }

    @PutMapping("/update")
    public ResponseEntity<StandardResponse> updateUser(@Valid @RequestBody UpdateUserRequest request) throws AuthException {
        return new ResponseEntity<>(updateUserService.execute(request), HttpStatus.OK);
    }
}
