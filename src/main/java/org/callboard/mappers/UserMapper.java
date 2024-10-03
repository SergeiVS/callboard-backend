package org.callboard.mappers;

import org.callboard.dto.userDto.UserResponse;
import org.callboard.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toUserResponse(User user);
}
