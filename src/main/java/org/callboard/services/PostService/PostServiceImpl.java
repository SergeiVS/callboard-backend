package org.callboard.services.PostService;

import org.callboard.dto.PostDto.NewPostRequestDTO;
import org.callboard.dto.PostDto.UpdatePostRequestDTO;
import org.callboard.dto.PostDto.entities.Post;
import org.callboard.dto.PostDto.entities.Subject;
import org.callboard.dto.PostDto.entities.User;
import org.callboard.repositories.PostRepository;
import org.callboard.repositories.SubjectRepository;
import org.callboard.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, SubjectRepository subjectRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.subjectRepository = subjectRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Post createPost(NewPostRequestDTO request) {
        Optional<Subject> subject = subjectRepository.findById(request.getSubjectId());
        Optional<User> user = userRepository.findById(request.getUserId());

        if (subject.isEmpty() || user.isEmpty()) {
            throw new IllegalArgumentException("Subject or User not found");
        }

        Post post = new Post();
        post.setHeader(request.getHeader());
        post.setDescription(request.getDescription());
        post.setPhotoLink(request.getPhotoLink());
        post.setSubject(subject.get());
        post.setUser(user.get());

        return postRepository.save(post);
    }

    @Override
    public Post updatePost(Long postId, UpdatePostRequestDTO request) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Post not found"));
        post.setHeader(request.getHeader());
        post.setDescription(request.getDescription());
        post.setPhotoLink(request.getPhotoLink());
        return postRepository.save(post);
    }

    @Override
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post getPostById(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Post not found"));
    }
}
