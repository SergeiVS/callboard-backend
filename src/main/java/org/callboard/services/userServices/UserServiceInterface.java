package org.callboard.services.userServices;

import org.springframework.stereotype.Service;

@Service
public interface UserServiceInterface<T, S> {

    T execute(S s);
}
