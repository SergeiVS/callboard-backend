package org.callboard.controllers.postsControllers;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.callboard.dto.postDto.NewPostRequest;
import org.callboard.dto.postDto.PostResponse;
import org.callboard.dto.postDto.UpdatePostRequest;
import org.callboard.services.postsServices.CreatePostService;
import org.callboard.services.postsServices.UpdatePostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostsControllers {

    private final CreatePostService createPostService;
    private final UpdatePostService updatePostService;


    @PostMapping
    public ResponseEntity<List<PostResponse>> createNewPost(@Valid @RequestBody NewPostRequest request) {
        return createPostService.execute(request);
    }

    @PostMapping("/update")
    public ResponseEntity<List<PostResponse>> updatePost(@Valid @RequestBody UpdatePostRequest request) {
        return updatePostService.execute(request);
    }
}
