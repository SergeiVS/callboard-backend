package org.callboard.dto.userDto;

import lombok.Data;

@Data
public class UserDataResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
