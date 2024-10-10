package org.callboard.services.userServices;

import jakarta.security.auth.message.AuthException;
import org.callboard.dto.Request;
import org.callboard.dto.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserServiceInterface<T extends Response, S extends Request> {

    T execute(S s) throws AuthException;
}
