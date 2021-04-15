package se.kth.sda.skeleton.posts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
    @TODO create the methods needed to implement the API.
 */
@RestController
public class PostController {

    PostRepository postRepository;
    PostService postService;

    @Autowired
    public PostController(PostRepository postRepository, PostService postService) {
        this.postRepository = postRepository;
        this.postService = postService;
    }

    @GetMapping("/posts")
    public List<Post> listAllPosts() {
        List<Post> posts = postRepository.findAll( );
        return posts;
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        postRepository.save(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPost(@PathVariable Long id){
        Post post = postRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        return ResponseEntity.ok(post);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id , @RequestBody Post updatedPost){
        Post post = postService.updatePost(id, updatedPost);
        return ResponseEntity.ok(post);
    }

    @DeleteMapping("posts/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable Long id){
        Post post = postRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        postRepository.delete(post);
        return ResponseEntity.ok(post);
    }
}