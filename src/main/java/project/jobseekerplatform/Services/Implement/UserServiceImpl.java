package project.jobseekerplatform.Services.Implement;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import project.jobseekerplatform.Model.dto.PostDto;
import project.jobseekerplatform.Model.dto.UserDtoBasic;
import project.jobseekerplatform.Model.dto.UserDtoSignup;
import project.jobseekerplatform.Model.entities.Post;
import project.jobseekerplatform.Model.entities.User;
import project.jobseekerplatform.Persistences.UserRepo;
import project.jobseekerplatform.Services.UserService;

import java.util.List;
import java.util.Optional;

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
        return ResponseEntity.status(HttpStatus.OK).body("User" + userDtoSignup +"created");
    }

    @Override
    public ResponseEntity<?> getDetails(int userId) {
        Optional<User> user = userRepo.findById(userId);
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(user.get());
    }


    @Override
    public ResponseEntity<?> addFollow(int userId, int followId) {
        Optional<User> user = userRepo.findById(userId);
        Optional<User> follow = userRepo.findById(followId);
        if (user.isEmpty() || follow.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        user.get().getFollowing().add(follow.get());
        follow.get().getFollowers().add(user.get());
        userRepo.save(user.get());
        return ResponseEntity.status(HttpStatus.OK).body("Followed");
    }

    @Override
    public ResponseEntity<?> listFollowers(int userId) {
        Optional<User> user = userRepo.findById(userId);
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                user.get().getFollowing().stream().map(following -> modelMapper.map(following, UserDtoBasic.class)).toList());
    }

    @Override
    public ResponseEntity<?> listFollowing(int userId) {
        Optional<User> user = userRepo.findById(userId);
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                user.get().getFollowing().stream().map(following -> modelMapper.map(following, UserDtoBasic.class)).toList());
    }

    @Override
    public ResponseEntity<?> listApplying(int userId) {
        Optional<User> user = userRepo.findById(userId);
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                user.get().getApplications());
    }

    private List<PostDto> convertToPostDto(List<Post> posts) {
        return posts.stream().map(post -> modelMapper.map(post, PostDto.class)).toList();
    }
}
