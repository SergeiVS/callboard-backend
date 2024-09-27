package org.callboard.dto.UserDTO;

import lombok.Data;
// TODO
@Data
public class UserDataDTO {
    private Long id;  // Нужен ли тут вообще id?
    private String firstName;
    private String lastName;
    private String email;
    // Нужен ли тут + ещё номер телефона пользователя?
}

 // Конструкторы, геттеры, сеттеры