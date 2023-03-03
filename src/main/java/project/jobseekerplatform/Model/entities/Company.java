package project.jobseekerplatform.Model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String profilePicture;
    private String bio;

    @OneToMany(mappedBy = "company")
    private List<Job> jobs;

}
