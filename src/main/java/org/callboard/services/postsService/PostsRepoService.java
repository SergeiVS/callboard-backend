package org.callboard.services.postsService;

import lombok.RequiredArgsConstructor;
import org.callboard.entities.Post;
import org.callboard.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostsRepoService {
    private final PostRepository repository;

    public Post savePost(Post post) { return this.repository.save(post); }
}
