package project.jobseekerplatform.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.jobseekerplatform.Model.dto.PostDto;
import project.jobseekerplatform.Services.PostService;

import java.util.List;

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
       List<PostDto> newsfeed = postService.getNewsFeed(userId);
       return ResponseEntity.ok(newsfeed);
   }

    @PostMapping("/create")
    public ResponseEntity<?> createPost(@RequestBody PostDto postDto) {
        postService.createPost(postDto);
        return ResponseEntity.ok("Post created successful");
    }

    @PostMapping("/delete/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable int postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok("Post deleted successful");
    }

    @PutMapping ("/update")
    public ResponseEntity<?> updatePost(@RequestBody PostDto postDto) {
        postService.updatePost(postDto);
        return ResponseEntity.ok("Post updated successful");
    }
}
