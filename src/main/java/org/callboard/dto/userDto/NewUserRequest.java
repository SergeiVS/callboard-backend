package org.callboard.dto.userDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.callboard.annotations.NameValidation;
import org.callboard.annotations.PasswordValidation;
import org.callboard.annotations.StringFormatValidation;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewUserRequest {

    @NotBlank
    @StringFormatValidation(groups = {NameValidation.class})
    private String firstName;
    @NotBlank
    @StringFormatValidation(groups = {NameValidation.class})
    private String lastName;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @StringFormatValidation(groups = {PasswordValidation.class})
    private String password;
    private String phoneNumber;
}
