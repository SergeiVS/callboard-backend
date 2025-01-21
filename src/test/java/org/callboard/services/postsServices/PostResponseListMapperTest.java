package org.callboard.services.postsServices;

import org.callboard.dto.postDto.PostResponse;
import org.callboard.dto.subjectDto.SubjectResponse;
import org.callboard.dto.userDto.UserResponseForPost;
import org.callboard.entities.Post;
import org.callboard.entities.Subject;
import org.callboard.entities.User;
import org.callboard.mappers.PostMappers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostResponseListMapperTest {
    @Mock
    PostMappers postMappers;
    @InjectMocks
    PostResponseListMapper postResponseListMapper;

    Post post1;
    Post post2;

    Subject subject1;
    Subject subject2;

    User user1;
    User user2;

    PostResponse postResponse1;
    PostResponse postResponse2;

    @BeforeEach
    void setUp() {
        user1 = new User();
        user1.setId(1);
        user1.setFirstName("first1");
        user1.setLastName("last1");
        user1.setEmail("email1@email.com");

        user2 = new User();
        user2.setId(2);
        user2.setFirstName("first2");
        user2.setLastName("last2");
        user2.setEmail("email2@email.com");

        subject1 = new Subject();
        subject1.setSubjectId(1L);
        subject1.setName("subject1");

        subject2 = new Subject();
        subject2.setSubjectId(2L);
        subject2.setName("subject2");

        post1 = new Post();
        post1.setPostId(1L);
        post1.setSubject(subject1);
        post1.setUser(user1);
        post1.setHeader("header1");
        post1.setDescription("description1");
        post1.setPhotoLink("photolink1");

        post2 = new Post();
        post2.setPostId(2L);
        post2.setSubject(subject2);
        post2.setHeader("header2");
        post2.setDescription("description2");
        post2.setPhotoLink("N/A");

        postResponse1 = new PostResponse();
        postResponse1.setPostId(1L);
        postResponse1.setHeader("header1");
        postResponse1.setDescription("description1");
        postResponse1.setPhotoLink("photolink1");
        postResponse1.setSubject(new SubjectResponse(subject1.getName()));
        postResponse1.setUser(
                new UserResponseForPost(1, "first1", "last1", "email1@email.com", null));

        postResponse2 = new PostResponse();
        postResponse2.setPostId(2L);
        postResponse2.setHeader("header2");
        postResponse2.setDescription("description2");
        postResponse2.setPhotoLink("N/A");
        postResponse2.setSubject(new SubjectResponse(subject2.getName()));
        postResponse2.setUser(
                new UserResponseForPost(2, "first2", "last2", "email2@email.com", null));
    }

    @Test
    void mapPostsListToPostResponseList() {
        List<Post> posts = Arrays.asList(post1, post2);
        List<PostResponse> responseList = Arrays.asList(postResponse1, postResponse2);
        when(postMappers.toPostResponse(post1)).thenReturn(postResponse1);
        when(postMappers.toPostResponse(post2)).thenReturn(postResponse2);
        List<PostResponse> result = postResponseListMapper.mapPostsListToPostResponseList(posts);

        assertEquals(responseList, result);
        assertThat(result).containsExactly(postResponse1, postResponse2);
        assertThat(result).hasSize(2);
        assertThat(result.getFirst()).isEqualTo(postResponse1);
        assertThat(result.getLast()).isEqualTo(postResponse2);
    }
}