package se.kth.sda.skeleton.comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.kth.sda.skeleton.posts.Post;
import se.kth.sda.skeleton.posts.PostRepository;
import se.kth.sda.skeleton.Exception.ResourceNotFoundException;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CommentController {

    CommentRepository commentRepository;
    PostRepository postRepository;

    @Autowired
    public CommentController(CommentRepository commentRepository, PostRepository postRepository){
        this.commentRepository=commentRepository;
        this.postRepository=postRepository;
    }

    @PutMapping("/comment/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @Validated({Comment.PutValidationGroup.class})
    @RequestBody Comment updatedComment) {
        Comment comment = commentRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        updatedComment.setId(comment.getId());
        updatedComment.setPost(comment.getPost());
        return ResponseEntity.ok(commentRepository.save(updatedComment));
    }

    @GetMapping(value = "/comments", params = {"authorName"})
    public ResponseEntity<List<Comment>> listCommentsByAuthorName(@RequestParam String authorName){
        return ResponseEntity.ok(commentRepository.findByAuthorName(authorName));
    }

    @PostMapping("/posts/{id}/comments")
    public ResponseEntity<Comment> createCommentOnArticle(@PathVariable Long id, @Valid @RequestBody Comment comment) {
        Post post = (Post) postRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        comment.setPost(post);
        return ResponseEntity.ok(commentRepository.save(comment));
    }

    @GetMapping("/posts/{id}/comments")
    public ResponseEntity<List<Comment>> listAllComments(@PathVariable String id){
        return ResponseEntity.ok(commentRepository.findAll());
    }


    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Comment> deleteComment(@PathVariable Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        commentRepository.delete(comment);
        return ResponseEntity.ok(comment);
    }
}



