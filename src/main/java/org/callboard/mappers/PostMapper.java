package org.callboard.mappers;
import org.callboard.dto.postDto.*;
import org.callboard.entities.*;
import org.springframework.stereotype.Component;
@Component
public class PostMapper {

    public Post toEntity(NewPostRequestDTO dto, User user, Subject subject) {
        Post post = new Post();
        post.setHeader(dto.getHeader());
        post.setDescription(dto.getDescription());
        post.setPhotoLink(dto.getPhotoLink());
        post.setUser(user);
        post.setSubject(subject);
        return post;
    }


    public PostResponseDTO toDto(Post post) {
        final PostResponseDTO o = null;
        return null;
    }

//    public PostResponseDTO toDto(Post post) {
//        return new PostResponseDTO(
//                post.getId(),
//                post.getHeader(),
//                post.getDescription(),
//                post.getPhotoLink(),
//                new UserForPostDTO(
//                        post.getUser().getId(),
//                        post.getUser().getFirstName(),
//                        post.getUser().getLastName(),
//                        post.getUser().getPhoneNumber(),
//                        post.getUser().getEmail()
//                ),
//                post.getSubject().getName()
//        );
//    }
}
