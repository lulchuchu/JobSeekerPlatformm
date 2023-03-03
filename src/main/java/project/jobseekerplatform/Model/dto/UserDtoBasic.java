package project.jobseekerplatform.Model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link project.jobseekerplatform.Model.entities.User} entity
 */
@Data
public class UserDtoBasic implements Serializable {
    private final Integer id;
    private final String name;
    private final String email;
    private final String profilePicture;
    private final String bio;
}