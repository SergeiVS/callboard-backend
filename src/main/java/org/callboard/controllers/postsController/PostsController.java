package org.callboard.controllers.postsController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.callboard.dto.StandardStringRequest;
import org.callboard.dto.postDto.NewPostRequest;
import org.callboard.dto.postDto.PostListResponse;
import org.callboard.dto.postDto.PostResponse;
import org.callboard.dto.postDto.UpdatePostRequest;
import org.callboard.services.postsServices.CreatePostService;
import org.callboard.services.postsServices.FindAllPostsService;
import org.callboard.services.postsServices.FindPostsBySubjectService;
import org.callboard.services.postsServices.UpdatePostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostsController {

    private final CreatePostService createPostService;
    private final UpdatePostService updatePostService;
    private final FindAllPostsService findAllPostsService;
    private final FindPostsBySubjectService findPostsBySubjectService;


    @PostMapping
    public ResponseEntity<PostResponse> createNewPost(@Valid @RequestBody NewPostRequest request) {
        log.info(request.toString());
        return new  ResponseEntity<>(createPostService.execute(request), HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<PostResponse> updatePost(@Valid @RequestBody UpdatePostRequest request) {
        return new ResponseEntity<>( updatePostService.execute(request), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PostListResponse> getAllPosts() {
        return new  ResponseEntity<>(findAllPostsService.findAllPosts(), HttpStatus.FOUND);
    }

    @GetMapping("/{subject}")
    public ResponseEntity<PostListResponse> getPostsBySubject(@PathVariable String subject) {
         return new  ResponseEntity<>(findPostsBySubjectService.execute(new StandardStringRequest(subject)), HttpStatus.FOUND);
    }

}
