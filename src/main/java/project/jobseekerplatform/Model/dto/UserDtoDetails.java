package project.jobseekerplatform.Model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link project.jobseekerplatform.Model.entities.User} entity
 */
@Data
public class UserDtoDetails implements Serializable {
    private final Integer id;
    private final String name;
    private final String email;
    private final String profilePicture;
    private final String bio;
    private final List<UserDtoBasic> following;
    private final List<UserDtoBasic> followers;
    private final List<JobDto> jobs;
    private final List<PostDto> posts;
}