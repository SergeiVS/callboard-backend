package controller.api;

import org.springframework.boot.autoconfigure.security.SecurityProperties;

public class UserRepository {
    public void save(SecurityProperties.User user) {
    }

    public boolean existsByEmail(Object email) {
        return false;
    }
}
