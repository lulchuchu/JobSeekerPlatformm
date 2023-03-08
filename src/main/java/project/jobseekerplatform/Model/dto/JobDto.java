package project.jobseekerplatform.Model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.jobseekerplatform.Model.entities.Company;
import project.jobseekerplatform.Model.entities.User;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link project.jobseekerplatform.Model.entities.Job} entity
 */
@Data
@NoArgsConstructor
public class JobDto implements Serializable {
    private int id;
    private String title;
    private LocalDate startDate;
    private LocalDate leaveDate;
    @JsonIgnore
    private Company company;
}