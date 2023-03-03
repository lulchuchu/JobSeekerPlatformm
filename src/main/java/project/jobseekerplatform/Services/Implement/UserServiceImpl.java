package project.jobseekerplatform.Services.Implement;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import project.jobseekerplatform.Model.dto.*;
import project.jobseekerplatform.Model.entities.Job;
import project.jobseekerplatform.Model.entities.Post;
import project.jobseekerplatform.Model.entities.User;
import project.jobseekerplatform.Persistences.UserRepo;
import project.jobseekerplatform.Services.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepo userRepo;


    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, UserRepo userRepo) {
        this.modelMapper = modelMapper;
        this.userRepo = userRepo;
    }

    @Override
    public ResponseEntity<?> signUp(UserDtoSignup userDtoSignup) {
        User user = new User();
        user.setName(userDtoSignup.getName());
        user.setEmail(userDtoSignup.getEmail());
        user.setUsername(userDtoSignup.getUsername());
        user.setPassword(userDtoSignup.getPassword());
        userRepo.save(user);
        return ResponseEntity.status(HttpStatus.OK).body("User created");
    }

    @Override
    public ResponseEntity<?> getDetails(int userId) {
        User user = userRepo.findById(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new UserDtoDetails(user.getId(), user.getName(), user.getEmail(), user.getProfilePicture(),
                        user.getBio(), convertToUserDtoBasic(user.getFollowing()), convertToUserDtoBasic(user.getFollowers()),
                        convertToJobDto(user.getJobs()), convertToPostDto(user.getPosts())));
    }


    @Override
    public ResponseEntity<?> addFollow(int userId, int followId) {
        User user = userRepo.findById(userId).orElse(null);
        User follow = userRepo.findById(followId).orElse(null);
        if (user == null || follow == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        user.getFollowing().add(follow);
        follow.getFollowers().add(user);
        userRepo.save(user);
        return ResponseEntity.status(HttpStatus.OK).body("Followed");
    }

    @Override
    public ResponseEntity<?> listFollowers(int userId) {
        User user = userRepo.findById(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(convertToUserDtoBasic(user.getFollowers()));
    }

    @Override
    public ResponseEntity<?> listFollowing(int userId) {
        User user = userRepo.findById(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(convertToUserDtoBasic(user.getFollowing()));
    }

    private List <JobDto> convertToJobDto(List<Job> jobs) {
        return jobs.stream().map(job -> {
            return modelMapper.map(job, JobDto.class);
        }).toList();
    }

    private List<PostDto> convertToPostDto(List<Post> posts) {
        return posts.stream().map(post -> modelMapper.map(post, PostDto.class)).toList();
    }

    private List<UserDtoBasic> convertToUserDtoBasic(List<User> users) {
//        return users.stream().map(user -> {
//            return new UserDtoBasic(user.getId(), user.getName(), user.getEmail(), user.getProfilePicture(), user.getBio());
//        }).toList();
        return users.stream().map(user -> modelMapper.map(user, UserDtoBasic.class)).toList();
    }
}
