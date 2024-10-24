package org.callboard.dto.postDto;

import lombok.*;
import org.callboard.dto.Response;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCreateSuccessResponse extends Response {
    private Long id;
    private String message;
}
