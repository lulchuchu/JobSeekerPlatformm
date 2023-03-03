package project.jobseekerplatform.Model.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Post post;

    @ManyToOne
    private User user;
}
