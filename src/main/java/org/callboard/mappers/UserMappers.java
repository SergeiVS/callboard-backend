package org.callboard.mappers;

import org.callboard.dto.userDto.UserDataResponse;
import org.callboard.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMappers {

    UserDataResponse userToUserDataResponse(User user);
}
