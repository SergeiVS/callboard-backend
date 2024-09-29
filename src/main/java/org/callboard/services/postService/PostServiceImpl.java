package org.callboard.services.postService;

import org.callboard.dto.postDto.NewPostRequestDTO;
import org.callboard.dto.postDto.PostResponseDTO;
import org.callboard.dto.postDto.UpdatePostRequestDTO;
import org.callboard.entities.Post;
import org.callboard.mappers.PostMapper;
import org.callboard.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
   // private final UserService userService;
   // private final SubjectService subjectService;
    private final PostMapper postMapper;

   public PostServiceImpl(PostRepository postRepository/*UserService userService*//*SubjectService subjectService*/, PostMapper postMapper) {
        this.postRepository = postRepository;
     //  this.userService = userService;
      //  this.subjectService = subjectService;
        this.postMapper = postMapper;
    }

//    @Override
//    public PostResponseDTO createPost(NewPostRequestDTO request) {
//        User user = userService.getUserById(request.getUserId());
//       Subject subject = subjectService.getSubjectById(request.getSubjectId());
//        Post post = postMapper.toEntity(request, user, subject);
//        Post savedPost = postRepository.save(post);
//        return postMapper.toDto(savedPost);
//    }

    @Override
    public PostResponseDTO createPost(NewPostRequestDTO request) {
        return null;
    }

    @Override
    public List<PostResponseDTO> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PostResponseDTO updatePost(UpdatePostRequestDTO request) {
        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        post.setHeader(request.getHeader());
        post.setDescription(request.getDescription());
        post.setPhotoLink(request.getPhotoLink());

        Post updatedPost = postRepository.save(post);
        return postMapper.toDto(updatedPost);
    }

    @Override
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }
}
