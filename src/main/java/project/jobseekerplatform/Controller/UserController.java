package project.jobseekerplatform.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.jobseekerplatform.Model.dto.UserDtoSignup;
import project.jobseekerplatform.Services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserDtoSignup userDtoSignup){
        return userService.signUp(userDtoSignup);
    }

    @PutMapping("/addfollow/{userId}/{followId}")
    public ResponseEntity<?> addFollow(@PathVariable int userId, @PathVariable int followId){
        return userService.addFollow(userId, followId);
    }

    @GetMapping("/following/{userId}")
    public ResponseEntity<?> listFollowing(@PathVariable int userId){
        return userService.listFollowing(userId);
    }

    @GetMapping("/followers/{userId}")
    public ResponseEntity<?> listFollowers(@PathVariable int userId){
        return userService.listFollowers(userId);
    }
}
