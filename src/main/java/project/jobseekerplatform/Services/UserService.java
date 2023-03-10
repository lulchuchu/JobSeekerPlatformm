package project.jobseekerplatform.Services;

import project.jobseekerplatform.Model.dto.UserDtoBasic;
import project.jobseekerplatform.Model.dto.UserDtoSignup;
import project.jobseekerplatform.Model.entities.Application;
import project.jobseekerplatform.Model.entities.User;

import java.util.List;

public interface UserService {
    void signUp(UserDtoSignup userDtoSignup);

    User getDetails(int userId);

    void addFollow(int userId, int followId);

    List<UserDtoBasic> listFollowers(int userId);

    List<UserDtoBasic> listFollowing(int userId);

    List<Application> listApplying(int userId);
}
