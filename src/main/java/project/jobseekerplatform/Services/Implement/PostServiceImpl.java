package project.jobseekerplatform.Services.Implement;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import project.jobseekerplatform.Model.dto.PostDto;
import project.jobseekerplatform.Model.entities.Post;
import project.jobseekerplatform.Model.entities.User;
import project.jobseekerplatform.Persistences.PostRepo;
import project.jobseekerplatform.Persistences.UserRepo;
import project.jobseekerplatform.Services.FileStorageService;
import project.jobseekerplatform.Services.PostService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final ModelMapper modelMapper;
    private final PostRepo postRepo;
    private final UserRepo userRepo;
    private final FileStorageService fileStorageService;


    @Autowired
    public PostServiceImpl(ModelMapper modelMapper, PostRepo postRepo, UserRepo userRepo, FileStorageService fileStorageService) {
        this.modelMapper = modelMapper;
        this.postRepo = postRepo;
        this.userRepo = userRepo;
        this.fileStorageService = fileStorageService;
    }

    @Override
    public ResponseEntity<?> getNewsFeed(int userId) {
        User user = userRepo.findById(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        List<User> followingPeople = user.getFollowing();
        List<PostDto> newsfeed = new ArrayList<>();
        for (User follower : followingPeople) {
            newsfeed.addAll(postRepo.findAllByUserId(follower.getId()).stream().map(p -> modelMapper.map(p, PostDto.class)).toList());
        }
        return ResponseEntity.status(HttpStatus.OK).body(newsfeed);
    }

    @Override
    public ResponseEntity<?> createPost(PostDto postDto){
        User user = userRepo.findById(postDto.getUser().getId()).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        Post post = new Post();
        post.setUser(user);
        post.setContent(postDto.getContent());
        post.setComment(postDto.getComment());
        post.setLikes(postDto.getLikes());
        post.setComment(postDto.getComment());
        post.setPostedDate(LocalDate.now());
        post.setImages(postDto.getImages());


        postRepo.save(post);
        return ResponseEntity.status(HttpStatus.OK).body("Post created");
    }

    @Override
    public ResponseEntity<?> deletePost(int postId) {
        Post post = postRepo.findById(postId).orElse(null);
        if (post == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found");
        }
        postRepo.deleteById(postId);
        return ResponseEntity.status(HttpStatus.OK).body("Post deleted");
    }

    @Override
    public ResponseEntity<?> updatePost(PostDto postDto) {
        Post post = postRepo.findById(postDto.getId()).orElse(null);
        if (post == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found");
        }
        postRepo.updatePostById(postDto.getId(), postDto.getContent());
        return ResponseEntity.status(HttpStatus.OK).body("Post updated");
    }

}
