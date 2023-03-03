package project.jobseekerplatform.Persistences;

import org.springframework.data.jpa.repository.JpaRepository;
import project.jobseekerplatform.Model.entities.Company;

public interface CompanyRepo extends JpaRepository<Company, Integer> {
}
