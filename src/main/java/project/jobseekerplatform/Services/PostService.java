package project.jobseekerplatform.Services;

import org.springframework.http.ResponseEntity;
import project.jobseekerplatform.Model.dto.PostDto;

public interface PostService {
    ResponseEntity<?> createPost(PostDto postDto);
    ResponseEntity<?> deletePost(int postId);
    ResponseEntity<?> updatePost(PostDto postDto);
    ResponseEntity<?> getNewsFeed(int userId);

}
