package org.callboard.dto.postDto;

import lombok.*;
import org.callboard.dto.Response;
import org.callboard.dto.subjectDto.SubjectResponse;
import org.callboard.dto.userDto.UserResponseForPost;
import org.springframework.stereotype.Component;

@Component
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostResponse extends Response {
    private Long postId;
    private SubjectResponse subject;
    private String header;
    private UserResponseForPost user;
    private String description;
    private String photoLink;
}
