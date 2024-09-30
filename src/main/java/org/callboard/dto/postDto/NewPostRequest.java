package org.callboard.dto.postDto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewPostRequest {

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
