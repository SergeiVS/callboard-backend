package org.callboard.services.postsServices;

import org.callboard.dto.postDto.NewPostRequest;
import org.callboard.dto.postDto.PostCreateSuccessResponse;
import org.callboard.entities.Post;
import org.callboard.entities.Subject;
import org.callboard.entities.User;
import org.callboard.exceptions.NotFoundException;
import org.callboard.services.fileUploadServices.FileUploadService;
import org.callboard.services.subjectService.SubjectRepositoryService;
import org.callboard.services.userServices.UserRepositoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.argThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreatePostServiceTest {
    @Mock
    PostRepositoryService postRepoService;

    @Mock
    SubjectRepositoryService subjectRepoService;

    @Mock
    FileUploadService fileUploadService;

    @Mock
    UserRepositoryService userRepositoryService;

    @InjectMocks
    CreatePostService createPostService;

    private User user;
    private NewPostRequest request;
    private Post postForSave;
    private Post postSaved;
    private Subject subject;


    @BeforeEach
    void setUp() {
        request = new NewPostRequest();
        request.setHeader("header");
        request.setSubject("subject");
        request.setUserId(1);
        request.setDescription("description");

        user = new User();
        user.setId(1);

        subject = new Subject(1L, "subject");

        postForSave = new Post();
        postForSave.setHeader(request.getHeader());
        postForSave.setDescription(request.getDescription());
        postForSave.setPhotoLink("N/A");
        postForSave.setSubject(subject);
        postForSave.setUser(user);

        postSaved = new Post();
        postSaved.setPostId(1L);
        postSaved.setHeader(request.getHeader());
        postSaved.setDescription(request.getDescription());
        postSaved.setPhotoLink("N/A");
        postSaved.setSubject(subject);
        postSaved.setUser(user);
    }

    @Test
    void shouldCreatePost() throws IOException {
        when(subjectRepoService.findByName("subject")).thenReturn(Optional.ofNullable(subject));
        when(userRepositoryService.findUserById(1)).thenReturn(Optional.of(user));
        when(postRepoService.save(argThat(post -> (post.getHeader().equals(postForSave.getHeader()) &&
                (post.getDescription().equals(postForSave.getDescription()))))))
                .thenReturn(postSaved);

        PostCreateSuccessResponse result = createPostService.execute(request);
        PostCreateSuccessResponse response = new PostCreateSuccessResponse(1L, "Your post is successfully added under id: 1");
        assertEquals(response, result);
    }

    @Test
    void shouldNotCreatePostSubjectNotExist() throws IOException {
        assertThrows(NotFoundException.class, () -> createPostService.execute(request), "Subject: subject not found");
    }
}