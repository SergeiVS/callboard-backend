package org.callboard.dto.postDto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.callboard.dto.Request;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdatePostRequest extends Request {
    @NotNull
    private Long postId;

    @Nullable
    private String header;

    @Nullable
    private String description;

    @Nullable
    private String photoLink;
}
