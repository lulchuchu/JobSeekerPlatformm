package project.jobseekerplatform.Model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A DTO for the {@link project.jobseekerplatform.Model.entities.User} entity
 */
@Data
@NoArgsConstructor
public class UserDtoSignup implements Serializable {
    private Integer id;
    private String name;
    private String email;
    private String username;
    private String password;
}