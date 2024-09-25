package org.callboard.dto.PostDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;

@Data
public class NewPostRequestDTO {
    @NotBlank
    @Getter
    private String subject;

    @Getter
    @NotBlank
    private String header;

    @Getter
    @NotBlank
    private String description;

    private String photoLink;
    @Getter
    private Long subjectId;
    @Getter
    private Integer userId;

}
