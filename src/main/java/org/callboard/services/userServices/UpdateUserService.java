package org.callboard.services.userServices;

import jakarta.security.auth.message.AuthException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.callboard.dto.StandardResponse;
import org.callboard.dto.userDto.UpdateUserRequest;
import org.callboard.entities.User;
import org.callboard.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserService implements UserServiceInterface<StandardResponse, UpdateUserRequest> {

    private final UserRepositoryService userRepoService;


    @Override
    @Transactional
    public StandardResponse execute(UpdateUserRequest request) throws AuthException {
        User userForSave = getUserForSave(request);
        validateUserEmail(userForSave, request.getEmail());
        addNewFieldsToUser(userForSave, request);
        userRepoService.saveUser(userForSave);
        return new StandardResponse(STR."User: \{userForSave.getEmail()} updated successfully");
    }

    private void addNewFieldsToUser(User userForSave, UpdateUserRequest request) {
        userForSave.setFirstName(request.getFirstName());
        userForSave.setLastName(request.getLastName());
        userForSave.setEmail(request.getEmail());
        setNewPhoneNumberToUser(userForSave, request);
    }

    private static void setNewPhoneNumberToUser(User userForSave, UpdateUserRequest request) {
        if (request.getPhoneNumber() == null || request.getPhoneNumber().isEmpty()) {
            userForSave.setPhoneNumber("N/A");
        } else {
            userForSave.setPhoneNumber(request.getPhoneNumber());
        }
    }

    private User getUserForSave(UpdateUserRequest request) {
        return userRepoService.findUserById(request.getUserId()).orElseThrow(() -> {
            return new NotFoundException(STR."User: \{request.getUserId()} not found");
        });
    }

    private void validateUserEmail(User user, String email) {
        if (!user.getEmail().equals(email.toLowerCase()) && userRepoService.existsUserByEmail(email)) {
            throw new IllegalArgumentException(STR."email: \{email} already exists");
        }
    }
}
