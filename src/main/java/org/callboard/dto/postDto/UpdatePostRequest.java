package org.callboard.dto.postDto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdatePostRequest {
    @NotNull
    private Long postId;

    @Nullable
    private String header;

    @Nullable
    private String description;

    @Nullable
    private String photoLink;
}
