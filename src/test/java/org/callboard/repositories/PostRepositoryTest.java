package org.callboard.repositories;

import org.callboard.entities.Post;
import org.callboard.entities.Subject;
import org.callboard.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Profile;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Profile("test")
class PostRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private PostRepository postRepository;

    Subject subject;
    User user;
    Post post;



    @BeforeEach
    void setUp() {
        subject = new Subject("Subject");
        subject = entityManager.persist(subject);

        user = new User("User","User","user@email.com","password");
        entityManager.persist(user);

        post = new Post( "header", "description", "N/A", subject, user);
        entityManager.persist(post);
        entityManager.flush();
    }


    @Test
    void findBySubjectId() {
        List<Post> result = postRepository.findBySubjectId(subject.getSubjectId());
        assertThat(result).hasSize(1);
        assertThat(result.getFirst()).isEqualTo(post);
        assertThat(result.getFirst().getSubject().getSubjectId()).isEqualTo(subject.getSubjectId());
    }

    @Test
    void deleteByUserId() {
        List<Post> posts = postRepository.findByUserId(user.getId());
        assertThat(posts.size()).isEqualTo(1);
        assertThat(posts.getFirst().getUser().getId()).isEqualTo(user.getId());

        postRepository.deleteByUserId(user.getId());

        List<Post> result = postRepository.findByUserId(user.getId());
        assertThat(result.size()).isEqualTo(0);
    }

    @Test
    void findByUserId() {
        List<Post> result = postRepository.findByUserId(user.getId());
        assertThat(result).hasSize(1);
        assertThat(result.getFirst()).isEqualTo(post);
        assertThat(result.getFirst().getUser().getId()).isEqualTo(user.getId());
    }

    @Test
    void findByUserEmail() {
        List<Post> result = postRepository.findByUserEmail("user@email.com");
        assertThat(result).hasSize(1);
        assertThat(result.getFirst()).isEqualTo(post);
        assertThat(result.getFirst().getUser().getEmail()).isEqualTo("user@email.com");

    }

}