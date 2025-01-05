package org.callboard.services.userServices;

import lombok.RequiredArgsConstructor;
import org.callboard.dto.userDto.UserResponse;
import org.callboard.dto.userDto.UserResponseList;
import org.callboard.entities.Role;
import org.callboard.entities.User;
import org.callboard.mappers.UserMappers;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
@RequiredArgsConstructor
public class UserServiceUtils {
    private final UserMappers userMappers;

    public static List<String> getUserRoles(User user) {
        return user.getRoles().stream().map(Role::getRoleName).toList();
    }

    public  UserResponseList getUserResponseList(List<User> users) {
       List<UserResponse> responseList = new ArrayList<>();
       for (User user : users) {
           UserResponse userResponse = userMappers.userToUserResponse(user);
           userResponse.setRoles(getUserRoles(user));
           responseList.add(userResponse);
       }
        return new UserResponseList(responseList);
    }
}
