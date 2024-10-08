package org.callboard.controllers.userController;

import lombok.RequiredArgsConstructor;
import org.callboard.entities.User;
import org.callboard.repositories.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    @GetMapping
    List<User> getAlluser(){
      return userRepository.findAll();
    }
}
