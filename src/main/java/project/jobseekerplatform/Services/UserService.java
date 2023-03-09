package project.jobseekerplatform.Services;

import org.springframework.http.ResponseEntity;
import project.jobseekerplatform.Model.dto.UserDtoSignup;

public interface UserService {
    ResponseEntity<?> signUp(UserDtoSignup userDtoSignup);
    ResponseEntity<?> getDetails(int userId);
    ResponseEntity<?> addFollow(int userId, int followId);
    ResponseEntity<?> listFollowers(int userId);
    ResponseEntity<?> listFollowing(int userId);

    ResponseEntity<?> listApplying(int userId);
}
