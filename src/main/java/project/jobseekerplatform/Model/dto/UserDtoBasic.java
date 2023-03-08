package project.jobseekerplatform.Model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A DTO for the {@link project.jobseekerplatform.Model.entities.User} entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoBasic implements Serializable {
    private Integer id;
    private String name;
    private String email;
    private String profilePicture;
    private String bio;
}