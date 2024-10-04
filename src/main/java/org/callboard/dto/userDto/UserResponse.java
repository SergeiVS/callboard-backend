package org.callboard.dto.userDto;

import lombok.*;
import org.callboard.dto.Response;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse extends Response {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String message;
}
