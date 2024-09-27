package org.callboard.services.UserService;

import org.callboard.dto.UserDTO.UserDataDTO;
import org.callboard.dto.UserDTO.UserRegistrationRequestDTO;
import org.callboard.dto.UserDTO.UserLoginRequestDTO;
import org.callboard.dto.UserDTO.UpdateUserRequestDTO;
import org.springframework.http.ResponseEntity;
//import org.callboard.dto.PostDto.entities.Post;
//import java.util.List;

public interface UserService {
    ResponseEntity<UserDataDTO> doService(Object o);


    //void registerUser(UserRegistrationRequestDTO registrationRequestDTO);
    // Метод для регистрации нового пользователя
    // Принимает DTO с данными для регистрации
    //boolean loginUser(UserLoginRequestDTO userLoginRequestDTO);
    // Метод для логина логина пользователя
    // Принимает DTO с данными для логина
    // - возвращает true- если логин успешен, и false- в противном случае
    //void updateUser(Long id, UpdateUserRequestDTO updateUserRequestDTO);
    // Метод для обновления данных по его идентификатору.
    // Принимает ID пользователя и DTO с обновлёнными данными
    //UserDataDTO getUserById(Long id);
    // Метод для получения информации о пользователе по его идентификатору
    // Возвращает DTO с данными пользователя
    //void deleteUser(Long id);
    // Метод для удаления пользователя по его идентификатору

}