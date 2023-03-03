package project.jobseekerplatform.Services;

import org.springframework.http.ResponseEntity;
import project.jobseekerplatform.Model.dto.UserDtoSignup;

public interface UserService {
    public ResponseEntity<?> signUp(UserDtoSignup userDtoSignup);
    public ResponseEntity<?> getDetails(int userId);
    public ResponseEntity<?> addFollow(int userId, int followId);
    public ResponseEntity<?> listFollowers(int userId);
    public ResponseEntity<?> listFollowing(int userId);
}
