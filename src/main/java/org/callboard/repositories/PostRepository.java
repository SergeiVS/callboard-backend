package org.callboard.repositories;

import org.callboard.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.subject.subjectId=:id")
    List<Post> findBySubjectId(Long id);

    void deleteByUserId(Integer userId);

    @Query("SELECT p FROM Post p WHERE p.user.id=:id")
    List<Post> findByUserId(Integer id);

    @Query("SELECT p FROM Post p WHERE p.user.email=:email")
    List<Post> findByUserEmail(String email);
}