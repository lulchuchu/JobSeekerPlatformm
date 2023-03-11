package project.jobseekerplatform.Services;

import project.jobseekerplatform.Model.dto.UserDtoBasic;
import project.jobseekerplatform.Model.entities.Application;

import java.util.List;

public interface ApplicationService {
    Application apply(int userId, int applicationId);

    List<UserDtoBasic> listUserApplied(int applicationId);

    void addApplication(Application application);
}
