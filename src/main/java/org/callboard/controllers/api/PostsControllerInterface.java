package org.callboard.controllers.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.callboard.dto.StandardIntRequest;
import org.callboard.dto.StandardResponse;
import org.callboard.dto.StandardStringRequest;
import org.callboard.dto.postDto.*;
import org.callboard.services.postsServices.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping("/api/posts")
public interface PostsControllerInterface {


    @PostMapping(consumes = {"multipart/form-data"})
    ResponseEntity<PostCreateSuccessResponse> createNewPost(
            @ModelAttribute @RequestBody NewPostRequest request) throws IOException;

    @PutMapping("/update")
    ResponseEntity<PostResponse> updatePost(@Valid @RequestBody UpdatePostRequest request);

    @GetMapping
    ResponseEntity<PostListResponse> getAllPosts();

    @GetMapping("/{subject}")
    ResponseEntity<PostListResponse> getPostsBySubject(@PathVariable String subject) throws Exception;

    @GetMapping("/user")
    public ResponseEntity<PostListResponse> getPostsByUser(Principal principal) throws Exception;

    @DeleteMapping("/{id}")
    ResponseEntity<StandardResponse> deletePostById(@PathVariable Integer id) throws Exception;

    @DeleteMapping("/user/{id}")
    public ResponseEntity<StandardResponse> deletePostByUserId(@PathVariable Integer id) throws Exception;
}
