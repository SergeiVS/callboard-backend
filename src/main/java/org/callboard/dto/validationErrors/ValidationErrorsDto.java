package org.callboard.dto.validationErrors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidationErrorsDto {
    public List<ValidationErrorDto> errors;
}
