package project.jobseekerplatform.Persistences;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.jobseekerplatform.Model.entities.Post;
import project.jobseekerplatform.Model.entities.User;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findAllByUserId(int userId);
    @Modifying
    @Transactional
    @Query(value = "UPDATE Post p SET p.content = ?2 WHERE p.id = ?1")
    void updatePostById(int id, String content);
}
