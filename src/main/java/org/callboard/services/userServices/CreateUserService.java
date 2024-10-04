package org.callboard.services.userServices;

import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.callboard.dto.userDto.NewUserRequest;
import org.callboard.dto.userDto.UserResponse;
import org.callboard.entities.User;
import org.callboard.exceptions.AlreadyExistException;
import org.callboard.mappers.UserMappers;
import org.callboard.services.rolesServices.RolesRepositoryService;
import org.callboard.security.securityService.CreateJwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateUserService implements UserServiceInterface<UserResponse, NewUserRequest> {

    private final UserRepositoryService userRepoService;
    private final UserMappers userMappers;
    private final RolesRepositoryService rolesRepoService;
    private final CreateJwtService createJwtService;

    @Override
    public ResponseEntity<UserResponse> execute(NewUserRequest request) throws AuthException {

        User userForSave = getUserForSaveFromRequest(request);

        User savedUser = userRepoService.saveUser(userForSave);

        UserResponse userResponse = userMappers.userToUserResponse(savedUser);
        userResponse.setMessage(createJwtService.createJwt(request.getEmail(), request.getPassword()));

        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    private User getUserForSaveFromRequest(NewUserRequest request) {
        User userForSave = userMappers.newUserResponseToUser(request);

        if (userRepoService.existsUserByEmail(request.getEmail())) {
            throw new AlreadyExistException(STR."User with email \{request.getEmail()} already exists");
        }

        if (request.getPhoneNumber() != null) {
            userForSave.setPhoneNumber(request.getPhoneNumber());
        } else {
            userForSave.setPhoneNumber("N/A");
        }

        userForSave.setRoles(new HashSet<>());
        userForSave.getRoles().add(rolesRepoService.getRoleByName("USER"));
        log.info(userForSave.getRoles().toString());
        return userForSave;
    }
}
