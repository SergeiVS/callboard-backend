package org.callboard.services.userServices;

import lombok.RequiredArgsConstructor;
import org.callboard.entities.User;
import org.callboard.exceptions.NotFoundException;
import org.callboard.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User: " + id + " not found"));
    }

    public boolean existsUserByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public Integer findUserIdByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User: " + email + " not found"));
        return user.getId();
    }

    public void deleteUserById(Integer id) {
      try {
          userRepository.deleteById(id);
      }catch (Exception e) {
          throw new RuntimeException((e.getMessage()));
      }
    }
}
