package org.callboard.services.postsServices;

import org.callboard.entities.Post;
import org.callboard.entities.Subject;
import org.callboard.entities.User;
import org.callboard.exceptions.NotFoundException;
import org.callboard.repositories.PostRepository;
import org.callboard.services.subjectService.SubjectRepositoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostRepositoryServiceTest {

    @Mock
    PostRepository postRepository;

    @Mock
    SubjectRepositoryService subjectRepository;

    @InjectMocks
    PostRepositoryService postRepositoryService;

    Post postForSave;
    Post postSaved;
    User user;
    Subject subject;

    @BeforeEach
    void setUp() {
        user = new User(1, "firstName", "lastName", "user@email.com", "Password@1", null, new HashSet<>());
        subject= new Subject(1L,"subject");
        postForSave = new Post();
        postForSave.setSubject(subject);
        postForSave.setHeader("header");
        postForSave.setDescription("description");
        postForSave.setPhotoLink("photoLink");
        postForSave.setUser(user);

        postSaved = new Post();
        postSaved.setPostId(1L);
        postSaved.setSubject(subject);
        postSaved.setHeader("header");
        postSaved.setDescription("description");
        postSaved.setPhotoLink("photoLink");
        postSaved.setUser(user);
        postSaved.setSubject(subject);

    }

    @Test
    void save() {
        when(postRepository.save(any(Post.class))).thenReturn(postSaved);
        Post saved = postRepositoryService.save(postForSave);
        assertEquals(postSaved, saved);
        verify(postRepository, times(1)).save(any(Post.class));
    }

    @Test
    void shouldFindByIdSuccess() {
        when(postRepository.findById(1L)).thenReturn(Optional.of(postSaved));
        Post result = postRepositoryService.findById(1L);
        assertEquals(postSaved, result);
        verify(postRepository, times(1)).findById(any());
    }

    @Test
    void shouldThrowFindById() {
        when(postRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, ()-> postRepositoryService.findById(1L), "Post with id: 1 not found");
        verify(postRepository, times(1)).findById(1L);
    }

    @Test
    void findAll() {
    }

    @Test
    void deleteBiId() {
    }

    @Test
    void deleteByUserId() {
    }

    @Test
    void findBySubject() {
    }

    @Test
    void findByUserEmail() {
    }

    @Test
    void findByUserId() {
    }
}