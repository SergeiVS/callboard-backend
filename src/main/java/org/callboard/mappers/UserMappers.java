package org.callboard.mappers;

import org.callboard.dto.userDto.UserDataResponse;
import org.callboard.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMappers {
UserDataResponse userToDto(User user);
}
