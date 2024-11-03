package org.callboard.services.userServices;

import lombok.RequiredArgsConstructor;
import org.callboard.dto.StandardStringRequest;
import org.callboard.dto.userDto.UserResponseList;
import org.callboard.entities.User;
import org.callboard.services.StandardServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllUsersService implements StandardServiceInterface<UserResponseList, StandardStringRequest> {

    private final UserRepositoryService userRepositoryService;
    private final UserServiceUtils userServiceUtils;

    @Override
    public UserResponseList execute(StandardStringRequest request) throws Exception {
        if (userRepositoryService.existsUserByEmail(request.getParameter())) {
            List<User> users = userRepositoryService.findAllUsers();
            return userServiceUtils.getUserResponseList(users);
        } else {
            throw new IllegalAccessException("You need to have Admin permissions");
        }
    }
}
