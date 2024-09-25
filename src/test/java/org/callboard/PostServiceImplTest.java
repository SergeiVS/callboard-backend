package org.callboard;

import org.callboard.dto.PostDto.NewPostRequestDTO;
import org.callboard.dto.PostDto.entities.Post;
import org.callboard.repositories.PostRepository;
import org.callboard.services.PostService.PostServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PostServiceImplTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostServiceImpl postService;

    @Test
    public void testGetAllPosts() {
        Post post = new Post();
        when(postRepository.findAll()).thenReturn(Collections.singletonList(post));

        List<Post> posts = postService.getAllPosts();
        assertEquals(1, posts.size());
    }

    @Test
    public void testGetPostById() {
        Post post = new Post();
        when(postRepository.findById(1L)).thenReturn(java.util.Optional.of(post));

        Post foundPost = postService.getPostById(1L);
        assertNotNull(foundPost);
    }

    @Test
    public void testCreatePost() {
        Post post = new Post();
        when(postRepository.save(any(Post.class))).thenReturn(post);

        Post createdPost = postService.createPost(new NewPostRequestDTO());
        assertNotNull(createdPost);
    }
}
