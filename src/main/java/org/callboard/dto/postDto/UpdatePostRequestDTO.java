package org.callboard.dto.postDto;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
public class UpdatePostRequestDTO extends NewPostRequestDTO {
    @NonNull
    private Long postId;
   }
