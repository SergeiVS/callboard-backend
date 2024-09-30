package org.callboard.dto.postDto;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NewPostRequestDTO {
    @NonNull
    private Integer userId;
    @NotBlank
    private String subject;

    @NotBlank
    private String header;

    @NotBlank
    private String description;

    private String photoLink;
}
