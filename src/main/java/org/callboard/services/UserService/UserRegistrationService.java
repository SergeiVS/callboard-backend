package org.callboard.services.UserService;

import lombok.RequiredArgsConstructor;
import org.callboard.dto.UserDTO.UserDataDTO;
import org.callboard.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRegistrationService implements UserService{
   private final UserRepositoryService userRepository;
    @Override
    public ResponseEntity<UserDataDTO> doService(Object newUserRequest) {
        return null;
    }
}
