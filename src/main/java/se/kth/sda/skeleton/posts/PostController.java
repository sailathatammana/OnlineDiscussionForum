package se.kth.sda.skeleton.posts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.kth.sda.skeleton.Exception.ResourceNotFoundException;
import se.kth.sda.skeleton.Topic.Topic;
import se.kth.sda.skeleton.Topic.TopicRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

/*
    @TODO create the methods needed to implement the API.
    Don't forget to add necessary annotations.
 */
@RestController
public class PostController {
    PostRepository postRepository;
    TopicRepository topicRepository;

    @Autowired
    public PostController(PostRepository postRepository, TopicRepository topicRepository) {
        this.postRepository = postRepository;
        this.topicRepository = topicRepository;
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> listAllPosts(
            @RequestParam(
                    required = false,
                    value="someAttr") Long topicId) {
        return ResponseEntity.ok(postRepository.findAll());
    }

    @GetMapping("/topics/{topicId}/posts")
    public ResponseEntity<Set<Post>> listPostByTopic(@PathVariable Long topicId) {
        Topic topic = topicRepository.findById(topicId).orElseThrow(ResourceNotFoundException::new);
        return ResponseEntity.ok(topic.getPosts());
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPost(@PathVariable Long id) {
        return ResponseEntity.ok(
                postRepository.findById(id)
                        .orElseThrow(ResourceNotFoundException::new));
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(@Valid @RequestBody Post post) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postRepository.save(post));
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @Valid @RequestBody Post updatedPost) {
        postRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        updatedPost.setId(id);
        return ResponseEntity.ok(postRepository.save(updatedPost));
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        for (Topic topic: post.getTopics()) {
            topic.getPosts().remove(post);
        }
        postRepository.delete(post);
        return ResponseEntity.ok(post);
    }

}
