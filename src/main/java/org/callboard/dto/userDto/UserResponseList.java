package org.callboard.dto.userDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.callboard.dto.Response;
import org.springframework.stereotype.Component;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseList extends Response {
    private List<UserResponse> userResponseList;
}
