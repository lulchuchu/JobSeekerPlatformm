package project.jobseekerplatform.Model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.jobseekerplatform.Model.entities.Comment;
import project.jobseekerplatform.Model.entities.Like;
import project.jobseekerplatform.Model.entities.User;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * A DTO for the {@link project.jobseekerplatform.Model.entities.Post} entity
 */
@Data
public class PostDto implements Serializable {
    private final int id;
    private final String content;
    private final LocalDate postedDate;
//    private final int userId;
//    private final String username;
//    private final String profilePicture;
    private final UserDtoBasic user;
    @JsonIgnore
    private final List<Like> likes;
    @JsonIgnore
    private final List<Comment> comment;

}