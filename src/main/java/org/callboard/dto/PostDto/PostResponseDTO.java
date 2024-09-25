package org.callboard.dto.PostDto;

import lombok.Data;

@Data
public class PostResponseDTO {
    private Long postId;
    private String subject;
    private String header;
    private UserForPostDTO user;
    private String description;
    private String photoLink;
}
