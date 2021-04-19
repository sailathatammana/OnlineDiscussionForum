package se.kth.sda.skeleton.posts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.kth.sda.skeleton.Exception.ResourceNotFoundException;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

/*
    @TODO create the methods needed to implement the API.
    Don't forget to add necessary annotations.
 */
@RestController
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> listAllPosts(
            @RequestParam(
                    required = false,
                    value="someAttr") Long topicId) {
        return ResponseEntity.ok(postRepository.findAll());
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(@Valid @RequestBody Post post) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postRepository.save(post));
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable Long postId, @Valid @RequestBody Post updatedPost) {
        postRepository.findById(postId)
                .orElseThrow(ResourceNotFoundException::new);
        updatedPost.setId(postId);
        return ResponseEntity.ok(postRepository.save(updatedPost));
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long postId){
        return postRepository.findById(postId)
                .map(post -> {
                    postRepository.delete(post);
                    return ResponseEntity.ok().build();
                }).orElseThrow(()-> new ResourceNotFoundException("Question not found with id" + postId));
    }

}
