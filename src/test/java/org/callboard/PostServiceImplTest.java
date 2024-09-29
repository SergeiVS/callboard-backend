package org.callboard;

import org.callboard.dto.postDto.PostResponseDTO;
import org.callboard.entities.Post;
import org.callboard.mappers.PostMapper;
import org.callboard.repositories.PostRepository;
import org.callboard.services.postService.PostServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PostServiceImplTest {

    private final PostRepository postRepository = mock(PostRepository.class);
   // private final UserService userService = mock(UserService.class);
   //private final SubjectService subjectService = mock(SubjectService.class);
    private final PostMapper postMapper = new PostMapper();

    private final PostServiceImpl postService = new PostServiceImpl(postRepository,/*userService, subjectService*/ postMapper);

    @Test
    void createPost_success() {
        //NewPostRequestDTO request = new NewPostRequestDTO(1L, 2L, "Header", "Description", "PhotoLink");
       // User user = new User(); user.setId(1L);
        //Subject subject = new Subject(); subject.setId(2L);

       // when(userService.getUserById(1L)).thenReturn(user);
       // when(subjectService.getSubjectById(2L)).thenReturn(subject);
        when(postRepository.save(Mockito.any(Post.class))).thenAnswer(i -> i.getArguments()[0]);


       // PostResponseDTO response = postService.createPost(request);


        //assertNotNull(response);
       // assertEquals("Header", response.getHeader());
    }

    @Test
    void getAllPosts_success() {

        Post post = new Post();
       // post.setId(1L);
        post.setHeader("Test Post");

        when(postRepository.findAll()).thenReturn(List.of(post));


        List<PostResponseDTO> posts = postService.getAllPosts();


        assertEquals(1, posts.size());
        assertEquals("Test Post", posts.get(0).getHeader());
    }
}
