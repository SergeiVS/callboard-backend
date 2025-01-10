package org.callboard.services.postsServices;

import org.callboard.dto.postDto.PostResponse;
import org.callboard.dto.postDto.UpdatePostRequest;
import org.callboard.dto.subjectDto.SubjectResponse;
import org.callboard.dto.userDto.UserResponseForPost;
import org.callboard.entities.Post;
import org.callboard.entities.Subject;
import org.callboard.entities.User;
import org.callboard.mappers.PostMapper;
import org.callboard.mappers.PostMappers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdatePostServiceTest {
    @Mock
    PostRepositoryService postRepositoryService;

    @Mock
    User user;
    @Mock
    Subject subject;
    @Mock
    PostMappers postMappers;

    @InjectMocks
    UpdatePostService updatePostService;
    UpdatePostRequest request;
    PostResponse response;
    Post postForUpdate;
    Post postUpdated;
    UserResponseForPost userResponseForPost = new UserResponseForPost();
    SubjectResponse subjectResponse = new SubjectResponse();

    @BeforeEach
    void setUp() {
        request = new UpdatePostRequest();
        request.setPostId(1L);
        request.setDescription("test description");
        request.setHeader("test header");
        request.setPhotoLink("test photo link");

        postForUpdate = new Post();
        postForUpdate.setPostId(1L);
        postForUpdate.setDescription("description not updated");
        postForUpdate.setHeader("header not updated");
        postForUpdate.setPhotoLink("photo link not updated");
        postForUpdate.setSubject(subject);
        postForUpdate.setUser(user);

        postUpdated = new Post();
        postUpdated.setPostId(1L);
        postUpdated.setDescription("test description");
        postUpdated.setHeader("test header");
        postUpdated.setPhotoLink("test photo link");
        postUpdated.setSubject(subject);
        postUpdated.setUser(user);

        response = new PostResponse();
        response.setPostId(1L);
        response.setDescription("test description");
        response.setHeader("test header");
        response.setPhotoLink("test photo link");
    }

    @Test
    void shouldUpdateAllFields() {
        when(postMappers.toPostResponse(postUpdated)).thenReturn(response);
        when(postRepositoryService.findById(request.getPostId())).thenReturn(postForUpdate);
        when(postRepositoryService.save(postForUpdate)).thenReturn(postUpdated);
        PostResponse result = updatePostService.execute(request);

        assertEquals(response, result);
        assertEquals(request.getDescription(), result.getDescription());
        assertEquals(request.getHeader(), result.getHeader());
        assertEquals(request.getPhotoLink(), result.getPhotoLink());
    }


}