package org.callboard.dto.postDto;

import lombok.Data;
import org.callboard.dto.userDto.UserDataResponse;

@Data
public class PostResponseDTO {
    private Long postId;
    private String subject;
    private String header;
    private UserDataResponse user;
    private String description;
    private String photoLink;
}
