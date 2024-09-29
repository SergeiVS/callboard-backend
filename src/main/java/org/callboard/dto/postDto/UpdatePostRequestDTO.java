package org.callboard.dto.postDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
public class UpdatePostRequestDTO {
    @NotNull
    private Long postId;

    @Nullable
    private String subject;

    @Nullable
    private String header;

    @Nullable
    private String description;

    @Nullable
    private String photoLink;

    @NotBlank
    private String phoneNumber;
}
