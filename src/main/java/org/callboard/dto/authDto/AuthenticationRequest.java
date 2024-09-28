package org.callboard.dto.authDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.callboard.annotations.PasswordValidation;
import org.callboard.annotations.StringFormatValidation;

@Data
public class AuthenticationRequest {
    @NotBlank
    @Email
    private String email;
    @NotBlank
//    @StringFormatValidation(groups = PasswordValidation.class)
    private String password;
}
