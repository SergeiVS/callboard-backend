package org.callboard.services.userServices;

import lombok.RequiredArgsConstructor;
import org.callboard.entities.User;
import org.callboard.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRepositoryService {
    private final UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public Optional<User> findUserById(Integer id) { return userRepository.findById(id); }

    ;
}
