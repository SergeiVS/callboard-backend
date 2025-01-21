package org.callboard.repositories;

import jakarta.validation.constraints.NotNull;
import org.callboard.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.subject.subjectId=:id")
    List<Post> findBySubjectId(Long id);

    @Modifying
    @Query("DELETE FROM Post p WHERE p.user.id=:userId")
    void deleteByUserId(@NotNull Integer userId);

    @Query("SELECT p FROM Post p WHERE p.user.id=:id")
    List<Post> findByUserId(Integer id);

    @Query("SELECT p FROM Post p WHERE p.user.email=:email")
    List<Post> findByUserEmail(String email);
}