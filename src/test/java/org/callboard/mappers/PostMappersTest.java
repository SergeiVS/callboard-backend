package org.callboard.mappers;

import org.callboard.dto.postDto.PostResponse;
import org.callboard.dto.subjectDto.SubjectResponse;
import org.callboard.dto.userDto.UserResponseForPost;
import org.callboard.entities.Post;
import org.callboard.entities.Subject;
import org.callboard.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PostMappersImpl.class, UserMappersImpl.class, SubjectMappersImpl.class})
class PostMappersTest {

    @Autowired
    PostMappers postMappers;


    Post post;
    Subject subject;
    User user;
    PostResponse postResponse;


    @BeforeEach
    void setUp() {

        subject = new Subject(1L, "subject");
        user = new User();
        user.setId(1);
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setEmail("email");
        user.setPhoneNumber("phoneNumber");

        post = new Post();
        post.setPostId(1L);
        post.setSubject(subject);
        post.setUser(user);
        post.setHeader("Header");
        post.setDescription("Description");
        post.setPhotoLink("photoLink");

        postResponse = new PostResponse();
        postResponse.setPostId(1L);
        postResponse.setHeader("Header");
        postResponse.setDescription("Description");
        postResponse.setPhotoLink("photoLink");
        postResponse.setUser(
                new UserResponseForPost(1, "firstName", "lastName", "email", "phoneNumber"));
        postResponse.setSubject(new SubjectResponse("subject"));
    }

    @Test
    void toPostResponse() {
        PostResponse result = postMappers.toPostResponse(post);
        assertEquals(postResponse, result);
        assertThat(postResponse.getPostId()).isEqualTo(post.getPostId());
        assertThat(postResponse.getSubject().getName()).isEqualTo(subject.getName());
        assertThat(postResponse.getHeader()).isEqualTo("Header");
        assertThat(postResponse.getDescription()).isEqualTo("Description");
        assertThat(postResponse.getPhotoLink()).isEqualTo("photoLink");
        assertThat(postResponse.getUser().getId()).isEqualTo(user.getId());
        assertThat(postResponse.getUser().getFirstName()).isEqualTo(user.getFirstName());
        assertThat(postResponse.getUser().getLastName()).isEqualTo(user.getLastName());
        assertThat(postResponse.getUser().getEmail()).isEqualTo(user.getEmail());
        assertThat(postResponse.getUser().getPhoneNumber()).isEqualTo(user.getPhoneNumber());

    }
}