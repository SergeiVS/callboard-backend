package org.callboard.repositories;

import org.callboard.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    default List<Post> findBySubjectId(Long id) {
        return findAll().stream()
                .filter(post -> post.getSubject().getSubjectId().equals(id))
                .toList();
    }

    ;

    default List<Post> findByUserId(Integer id) {

        return findAll().stream()
                .filter(post -> post.getUser().getId().equals(id))
                .toList();
    }
}