package org.callboard.controllers.api;

import jakarta.security.auth.message.AuthException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.callboard.dto.StandardIntRequest;
import org.callboard.dto.StandardResponse;
import org.callboard.dto.StandardStringRequest;
import org.callboard.dto.userDto.UpdateUserRequest;
import org.callboard.dto.userDto.UserResponseList;
import org.callboard.services.userServices.DeleteUserService;
import org.callboard.services.userServices.FindAllUsersService;
import org.callboard.services.userServices.UpdateUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/users")
public interface UserControllerInterface {

    @GetMapping
    @PreAuthorize(value = "ADMIN")
    ResponseEntity<UserResponseList> getAllUser(Principal principal) throws Exception;

    @PutMapping("/update")
    ResponseEntity<StandardResponse> updateUser(@Valid @RequestBody UpdateUserRequest request) throws AuthException;

    @DeleteMapping("/{id}")
    ResponseEntity<StandardResponse> deleteUser(@PathVariable Integer id);
}
