package org.callboard.dto.postDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
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

    @Positive(message = "User id should be a positive figure")
    private Integer userId;

    @NotBlank(message = "Subject should not be empty or null")
    private String subject;

    @NotBlank(message = "Header should not be empty or null")
    private String header;

    @NotBlank(message = "Description should not be empty or null")
    private String description;

    private MultipartFile image;
}
