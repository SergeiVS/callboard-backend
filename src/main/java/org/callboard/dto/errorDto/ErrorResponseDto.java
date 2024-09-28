package org.callboard.dto.errorDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponseDto {
    @Schema(description = "ErrorMessage", defaultValue = "Internal Server Error")
    private String errorMessage;

    @Schema(description = "UserNotFound")
    private String errorType;
}
