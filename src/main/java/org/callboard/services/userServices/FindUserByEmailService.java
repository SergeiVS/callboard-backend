package org.callboard.services.userServices;

import jakarta.security.auth.message.AuthException;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.core.StandardService;
import org.callboard.dto.Request;
import org.callboard.dto.Response;
import org.callboard.dto.StandardStringRequest;
import org.callboard.dto.userDto.UserResponse;
import org.callboard.entities.User;
import org.callboard.exceptions.NotFoundException;
import org.callboard.mappers.UserMappers;
import org.callboard.services.StandardServiceInterface;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindUserByEmailService implements StandardServiceInterface<UserResponse, StandardStringRequest> {

    private final UserRepositoryService userRepositoryService;
    private final UserMappers userMappers;


    @Override
    public UserResponse execute(StandardStringRequest request) throws AuthException {
        User user = userRepositoryService.findUserByEmail(request.getParameter())
                .orElseThrow(() -> new NotFoundException(STR."User: \{request.getParameter()} not found"));
        UserResponse userResponse = userMappers.userToUserResponse(user);
        userResponse.setRoles(UserServiceUtils.getUserRoles(user));
        return userResponse;
    }
}
