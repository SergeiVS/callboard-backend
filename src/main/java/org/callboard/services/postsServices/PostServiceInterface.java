package org.callboard.services.postsServices;

import org.callboard.dto.Request;
import org.callboard.dto.Response;
import org.callboard.dto.postDto.PostResponse;
import org.jetbrains.annotations.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PostServiceInterface<T extends Response, S extends Request> {

    T  execute(S s);
}
