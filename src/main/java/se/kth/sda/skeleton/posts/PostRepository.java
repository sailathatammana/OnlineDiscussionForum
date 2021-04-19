package se.kth.sda.skeleton.posts;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/*
    @TODO extend the appropriate JpaRepository to get common database operations for Post
 */

public interface PostRepository extends JpaRepository<Post,Long> {
   List<Post> findByPostID(Post post);
}
