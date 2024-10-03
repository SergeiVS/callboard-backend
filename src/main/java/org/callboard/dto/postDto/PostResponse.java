package org.callboard.dto.postDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.callboard.dto.subjectDto.SubjectResponse;
import org.callboard.dto.userDto.UserDataResponse;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostResponse {
    private Long postId;
    private SubjectResponse subject;
    private String header;
    private UserDataResponse user;
    private String description;
    private String photoLink;
}