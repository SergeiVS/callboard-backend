package org.callboard.dto.postDto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.callboard.dto.Request;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.nio.channels.MulticastChannel;

@Component
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewPostRequest extends Request {

    @NonNull
    private Integer userId;

    @NotBlank
    private String subject;

    @NotBlank
    private String header;

    @NotBlank
    private String description;

    private MultipartFile image;
}
