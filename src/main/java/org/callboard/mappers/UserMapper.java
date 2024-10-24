package org.callboard.mappers;

import org.callboard.dto.userDto.UserResponse;
import org.callboard.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
@Mapping(target = "roles", ignore = true)
    UserResponse toUserResponse(User user);
}
