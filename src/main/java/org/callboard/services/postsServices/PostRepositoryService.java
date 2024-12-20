package org.callboard.services.postsServices;

import lombok.RequiredArgsConstructor;
import org.callboard.entities.Post;
import org.callboard.entities.Subject;
import org.callboard.exceptions.NotFoundException;
import org.callboard.repositories.PostRepository;
import org.callboard.services.subjectService.SubjectRepositoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostRepositoryService {
    private final PostRepository postRepository;
    private final SubjectRepositoryService subjectRepoService;

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public void deleteBiId(Long id) {
        try {
            postRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteByUserId(Integer id) {
        try {
            postRepository.deleteByUserId(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Post> findBySubject(String subject) {
        Subject subjectForSearch = subjectRepoService.findByName(subject)
                .orElseThrow(() -> new NotFoundException("Subject: " + subject + " not found"));
        return postRepository.findBySubjectId(subjectForSearch.getSubjectId());
    }

    public List<Post> findByUserId(Integer userId) {
        return postRepository.findByUserId(userId);
    }
}
