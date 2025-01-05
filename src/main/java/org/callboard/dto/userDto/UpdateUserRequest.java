package org.callboard.dto.userDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.callboard.annotations.NameValidation;
import org.callboard.annotations.PasswordValidation;
import org.callboard.annotations.StringFormatValidation;
import org.callboard.dto.Request;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Component;

@Component
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserRequest extends Request {

    @NotNull(message = "User id could not be empty")
    @NumberFormat
    private Integer userId;
    @NotBlank(message = "User firstname could not be empty")
    @StringFormatValidation(groups = {NameValidation.class})
    private String firstName;
    @NotBlank(message = "User lastname could not be empty")
    @StringFormatValidation(groups = {NameValidation.class})
    private String lastName;

    private String phoneNumber;
}
