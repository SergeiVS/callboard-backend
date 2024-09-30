package org.callboard.controllers.postController;

import lombok.RequiredArgsConstructor;
import org.callboard.dto.postDto.NewPostRequestDTO;
import org.callboard.dto.postDto.PostResponseDTO;
import org.callboard.dto.postDto.UpdatePostRequestDTO;
import org.callboard.services.postsService.SaveNewPostService;
import org.callboard.services.postsService.UpdatePostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final SaveNewPostService savePostService;
    private final UpdatePostService updatePostService;

    @PostMapping
    public ResponseEntity<PostResponseDTO> createPost(@RequestBody NewPostRequestDTO request) {
        return savePostService.execute(request);
    }

//    @GetMapping
//    public ResponseEntity<List<PostResponseDTO>> getAllPosts() {
//        return ResponseEntity.ok(postService.getAllPosts());
//    }
//
    @PutMapping
    public ResponseEntity<PostResponseDTO> updatePost(@RequestBody UpdatePostRequestDTO request) {
        return updatePostService.execute(request);
    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
//        postService.deletePost(id);
//        return ResponseEntity.noContent().build();
//    }
}
