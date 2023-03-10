package project.jobseekerplatform.Services.Implement;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jobseekerplatform.Model.dto.PostDto;
import project.jobseekerplatform.Model.dto.UserDtoBasic;
import project.jobseekerplatform.Model.dto.UserDtoSignup;
import project.jobseekerplatform.Model.entities.Application;
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
    public void signUp(UserDtoSignup userDtoSignup) {
        User user = new User();
        user.setName(userDtoSignup.getName());
        user.setEmail(userDtoSignup.getEmail());
        user.setUsername(userDtoSignup.getUsername());
        user.setPassword(userDtoSignup.getPassword());
        userRepo.save(user);
    }

    @Override
    public User getDetails(int userId) {
        Optional<User> user = userRepo.findById(userId);
        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        return user.get();
    }


    @Override
    public void addFollow(int userId, int followId) {
        Optional<User> user = userRepo.findById(userId);
        Optional<User> follow = userRepo.findById(followId);
        if (user.isEmpty() || follow.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        user.get().getFollowing().add(follow.get());
        follow.get().getFollowers().add(user.get());
        userRepo.save(user.get());
    }

    @Override
    public List<UserDtoBasic> listFollowers(int userId) {
        Optional<User> user = userRepo.findById(userId);
        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        return user.get().getFollowers().stream().map(follower -> modelMapper.map(follower, UserDtoBasic.class)).toList();
    }

    @Override
    public List<UserDtoBasic> listFollowing(int userId) {
        Optional<User> user = userRepo.findById(userId);
        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        return user.get().getFollowing().stream().map(following -> modelMapper.map(following, UserDtoBasic.class)).toList();
    }

    @Override
    public List<Application> listApplying(int userId) {
        Optional<User> user = userRepo.findById(userId);
        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        return user.get().getApplications();
    }

    private List<PostDto> convertToPostDto(List<Post> posts) {
        return posts.stream().map(post -> modelMapper.map(post, PostDto.class)).toList();
    }
}
