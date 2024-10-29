package org.callboard.mappers;

import org.callboard.dto.userDto.NewUserRequest;
import org.callboard.dto.userDto.UserResponse;
import org.callboard.dto.userDto.UserResponseForPost;
import org.callboard.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMappers {

    @Mapping(target = "roles", ignore = true)
    UserResponse userToUserResponse(User user);

    UserResponseForPost usetToUserResponseForPost(User user);

    //
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "phoneNumber", ignore = true)
    @Mapping(target = "password", ignore = true)
    User newUserResponseToUser(NewUserRequest request);
}
