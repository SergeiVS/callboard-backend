package org.callboard.dto.postDto;
import lombok.Data;

@Data
public class UserForPostDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
