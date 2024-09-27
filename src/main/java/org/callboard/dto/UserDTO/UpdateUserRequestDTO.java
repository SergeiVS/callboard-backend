package org.callboard.dto.UserDTO;

import lombok.Data;

@Data
public class UpdateUserRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    // Пароль ведь тоже может быть обновлён?
}

// Конструкторы, геттеры, сеттеры
