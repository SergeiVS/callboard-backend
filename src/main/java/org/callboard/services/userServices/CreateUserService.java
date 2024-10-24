package org.callboard.services.userServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.callboard.dto.StandardResponse;
import org.callboard.dto.userDto.NewUserRequest;
import org.callboard.entities.User;
import org.callboard.exceptions.AlreadyExistException;
import org.callboard.mappers.UserMappers;
import org.callboard.services.StandardServiceInterface;
import org.callboard.services.rolesServices.RolesRepositoryService;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateUserService implements StandardServiceInterface<StandardResponse, NewUserRequest> {

    private final UserRepositoryService userRepoService;
    private final UserMappers userMappers;
    private final RolesRepositoryService rolesRepoService;

    @Override
    public StandardResponse execute(NewUserRequest request) throws AlreadyExistException {

        User userForSave = getUserForSaveFromRequest(request);

        User savedUser = userRepoService.saveUser(userForSave);
        validateSavedUser(savedUser);

        return new StandardResponse(STR."User: \{savedUser.getFirstName()} \{savedUser.getLastName()} created");
    }

    private static void validateSavedUser(User savedUser) {
        if (savedUser == null) {
            throw new RuntimeException("Failed to save user");
        }
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
