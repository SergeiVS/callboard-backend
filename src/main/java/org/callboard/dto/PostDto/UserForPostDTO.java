package org.callboard.dto.PostDto;
import lombok.Data;

@Data
public class UserForPostDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
