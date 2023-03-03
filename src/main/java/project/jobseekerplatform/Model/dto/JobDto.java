package project.jobseekerplatform.Model.dto;

import lombok.Data;
import project.jobseekerplatform.Model.entities.Company;
import project.jobseekerplatform.Model.entities.User;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link project.jobseekerplatform.Model.entities.Job} entity
 */
@Data
public class JobDto implements Serializable {
    private final int id;
    private final String title;
    private final LocalDate startDate;
    private final LocalDate leaveDate;
    private final Company company;
}