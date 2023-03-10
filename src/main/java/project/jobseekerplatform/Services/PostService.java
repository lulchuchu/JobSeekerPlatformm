package project.jobseekerplatform.Services;

import project.jobseekerplatform.Model.dto.PostDto;

import java.util.List;

public interface PostService {
    void createPost(PostDto postDto);

    void deletePost(int postId);

    void updatePost(PostDto postDto);

    List<PostDto> getNewsFeed(int userId);

}
