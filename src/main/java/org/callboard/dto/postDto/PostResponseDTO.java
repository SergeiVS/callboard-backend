package org.callboard.dto.postDto;

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
