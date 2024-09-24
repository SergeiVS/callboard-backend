package org.callboard.dto.PostDto;

import lombok.Data;
import org.jetbrains.annotations.Nullable;

@Data
public class UpdatePostRequestDTO {
    @Nullable
    private String subject;

    @Nullable
    private String header;

    @Nullable
    private String description;

    @Nullable
    private String photoLink;
}
