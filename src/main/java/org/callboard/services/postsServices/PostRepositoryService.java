package org.callboard.services.postsServices;

import lombok.RequiredArgsConstructor;
import org.callboard.entities.Post;
import org.callboard.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostRepositoryService {
    private final PostRepository postRepository;

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }
    public boolean existsById(Long id) {
        return postRepository.existsById(id);
    }
}
