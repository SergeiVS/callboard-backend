package org.callboard.dto.postDto;

import lombok.*;
import org.callboard.dto.Response;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostListResponse extends Response {
    private List<PostResponse> responses;

}
