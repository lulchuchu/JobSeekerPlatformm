package project.jobseekerplatform.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.jobseekerplatform.Model.dto.PostDto;
import project.jobseekerplatform.Services.PostService;

@RestController
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

   @GetMapping("/newsfeed/{userId}")
    public ResponseEntity<?> getNewsFeed(@PathVariable int userId) {
        return postService.getNewsFeed(userId);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPost(@RequestBody PostDto postDto){
        return postService.createPost(postDto);
    }

    @PostMapping("/delete/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable int postId){
        return postService.deletePost(postId);
    }

    @PutMapping ("/update")
    public ResponseEntity<?> updatePost(@RequestBody PostDto postDto){
        return postService.updatePost(postDto);
    }
}
