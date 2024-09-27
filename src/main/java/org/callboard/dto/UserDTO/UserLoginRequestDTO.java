package org.callboard.dto.UserDTO;

public class UserLoginRequestDTO {
    private String email;
    private String password;
// Вход в систему будет совешаться только через email
// или ещё опцией может быть username: firstName + lastName?/
// private String usernameOrEmail

}

// Конструкторы, геттеры, сеттеры