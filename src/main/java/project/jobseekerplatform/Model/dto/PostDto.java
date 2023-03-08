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
@NoArgsConstructor
@AllArgsConstructor
public class PostDto implements Serializable {
    private int id;
    private String content;
    private LocalDate postedDate;
    private String images;
    private UserDtoBasic user;
    @JsonIgnore
    private List<Like> likes;
    @JsonIgnore
    private List<Comment> comment;

}