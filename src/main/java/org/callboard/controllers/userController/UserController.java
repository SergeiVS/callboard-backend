package org.callboard.controllers.userController;

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
@RequiredArgsConstructor
public class UserController {

    private final UpdateUserService updateUserService;
    private final FindAllUsersService findAllUsersService;
    private final DeleteUserService deleteUserService;

    @GetMapping

    public ResponseEntity<UserResponseList> getAllUser(Principal principal) throws Exception {
        StandardStringRequest request = new StandardStringRequest(principal.getName());

        return new ResponseEntity<>(findAllUsersService.execute(request), HttpStatus.FOUND);
    }

    @PutMapping("/update")
    public ResponseEntity<StandardResponse> updateUser(@Valid @RequestBody UpdateUserRequest request) throws AuthException {
        return new ResponseEntity<>(updateUserService.execute(request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse> deleteUser(@PathVariable Integer id) {
        StandardIntRequest request = new StandardIntRequest(id);
        return new ResponseEntity<>(deleteUserService.execute(request), HttpStatus.OK);
    }
}
