package org.callboard.services.UserService;

import lombok.RequiredArgsConstructor;
import org.callboard.entities.User;
import org.callboard.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service

@RequiredArgsConstructor
public class UserRepositoryService {
    private final UserRepository userRepository;

    User saveUser(User user) {
       return userRepository.save(user);
    }
}
