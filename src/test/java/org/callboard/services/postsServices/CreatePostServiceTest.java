package org.callboard.services.postsServices;

import org.callboard.dto.postDto.NewPostRequest;
import org.callboard.dto.postDto.PostCreateSuccessResponse;
import org.callboard.entities.Post;
import org.callboard.entities.Subject;
import org.callboard.entities.User;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreatePostServiceTest {

    @Mock
    PostRepositoryService postRepositoryService;

    @Mock
    SubjectRepositoryService subjectRepoService;

    @Mock
    FileUploadService fileUploadService;

    @Mock
    UserRepositoryService userRepositoryService;

    private User user;

    private NewPostRequest request;
    private Post postForSave;
    private Post postSaved;
    Subject subject;

    @InjectMocks
    CreatePostService createPostService;


    @BeforeEach
    void setUp() {
        request = new NewPostRequest();
        request.setHeader("header");
        request.setSubject("subject");
        request.setUserId(1);
        request.setDescription("description");

        user = new User();
        user.setId(1);

        postForSave = new Post();
        postForSave.setHeader("header");
        postForSave.setDescription("description");
        postForSave.setPhotoLink("N/A");
        postForSave.setUser(user);
        postForSave.setSubject(subject);

        postSaved = new Post();
        postSaved.setPostId(1L);
        postSaved.setUser(user);
        postSaved.setSubject(subject);
        postSaved.setDescription("description");
        postSaved.setPhotoLink("N/A");
        postSaved.setHeader("header");

        subject = new Subject(1L, "subject");
        when(subjectRepoService.findByName("subject")).thenReturn(Optional.ofNullable(subject));
        when(userRepositoryService.findUserById(1)).thenReturn(Optional.of(user));


        when(postRepositoryService.save(postForSave)).thenReturn(postSaved);

    }

    @Test
    void shouldCreatePost() throws IOException {
        PostCreateSuccessResponse result = createPostService.execute(request);
        PostCreateSuccessResponse response = new PostCreateSuccessResponse(1L, "Subject: 1 not found");

        assertEquals(response, result);
    }
}