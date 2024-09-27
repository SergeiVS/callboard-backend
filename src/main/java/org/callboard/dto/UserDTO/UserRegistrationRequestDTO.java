package org.callboard.dto.UserDTO;

import lombok.Data;

@Data
public class UserRegistrationRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    // Нужен ли тут + ещё номер телефона пользователя?
}

// Конструкторы, геттеры, сеттеры