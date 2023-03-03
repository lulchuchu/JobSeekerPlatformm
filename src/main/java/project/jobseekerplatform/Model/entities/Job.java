package project.jobseekerplatform.Model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String title;
    private LocalDate startDate;
    private LocalDate leaveDate;
    @ManyToOne
    private User user;
    @ManyToOne
    private Company company;

}
