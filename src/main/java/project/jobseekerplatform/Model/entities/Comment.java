package project.jobseekerplatform.Model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String contents;
    private LocalDate commentDate;

    @ManyToOne
    private Post post;

    @ManyToOne
    private User user;
}
