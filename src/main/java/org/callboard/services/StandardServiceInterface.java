package org.callboard.services;

import org.callboard.dto.Request;
import org.callboard.dto.Response;

public interface StandardServiceInterface<T extends Response, S extends Request> {

    T execute(S s) throws Exception;
}
