package org.callboard.dto.PostDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NewPostRequestDTO {
    @NotBlank
    private String subject;

    @NotBlank
    private String header;

    @NotBlank
    private String description;

    private String photoLink;
}
