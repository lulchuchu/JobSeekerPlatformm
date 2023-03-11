package project.jobseekerplatform.Services.Implement;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jobseekerplatform.Model.dto.UserDtoBasic;
import project.jobseekerplatform.Model.entities.Application;
import project.jobseekerplatform.Model.entities.User;
import project.jobseekerplatform.Persistences.ApplicationRepo;
import project.jobseekerplatform.Persistences.UserRepo;
import project.jobseekerplatform.Services.ApplicationService;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ModelMapper modelMapper;
    private final ApplicationRepo applicationRepo;
    private final UserRepo userRepo;

    @Autowired
    public ApplicationServiceImpl(ModelMapper modelMapper, ApplicationRepo applicationRepo, UserRepo userRepo) {
        this.modelMapper = modelMapper;
        this.applicationRepo = applicationRepo;
        this.userRepo = userRepo;
    }

    @Override
    public Application apply(int userId, int applicationId) {
        Optional<User> user = userRepo.findById(userId);
        Optional<Application> application = applicationRepo.findById(applicationId);
        if (user.isEmpty() || application.isEmpty()) {
            return null;
        }
        user.get().getApplications().add(application.get());
        userRepo.save(user.get());

        application.get().getUsers().add(user.get());
        applicationRepo.save(application.get());
        return application.get();
    }

    @Override
    public List<UserDtoBasic> listUserApplied(int applicationId) {
        return applicationRepo.findById(applicationId).get().getUsers().stream().map(
                user -> modelMapper.map(user, UserDtoBasic.class)).toList();
    }

    @Override
    public void addApplication(Application application) {
        applicationRepo.save(application);
    }
}
