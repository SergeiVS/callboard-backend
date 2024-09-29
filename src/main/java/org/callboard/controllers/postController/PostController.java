package org.callboard.controllers.postController;

import org.callboard.dto.postDto.NewPostRequestDTO;
import org.callboard.dto.postDto.PostResponseDTO;
import org.callboard.dto.postDto.UpdatePostRequestDTO;
import org.callboard.services.postService.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostResponseDTO> createPost(@RequestBody NewPostRequestDTO request) {
        return ResponseEntity.status(201).body(postService.createPost(request));
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDTO>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @PutMapping
    public ResponseEntity<PostResponseDTO> updatePost(@RequestBody UpdatePostRequestDTO request) {
        return ResponseEntity.ok(postService.updatePost(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
