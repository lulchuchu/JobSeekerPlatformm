package project.jobseekerplatform.Services;

import org.springframework.http.ResponseEntity;
import project.jobseekerplatform.Model.dto.PostDto;

public interface PostService {
    public ResponseEntity<?> createPost(PostDto postDto);
    public ResponseEntity<?> deletePost(int postId);
    public ResponseEntity<?> updatePost(PostDto postDto);
    public ResponseEntity<?> getNewsFeed(int userId);

}
