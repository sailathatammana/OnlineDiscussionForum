package se.kth.sda.skeleton.comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CommentController {

    CommentRepository commentRepository;
    PostRepository postRepository;

    @Autowired
    public CommentController(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<Comment> createComment(@PathVariable Long postId, @RequestBody Comment comment) {
        Post post = postRepository
                .findById(postId)
                .orElseThrow(ResourceNotFoundException::new);
        comment.setCommentForPost(post);
        commentRepository.save(comment);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(comment);
    }

    @PutMapping("/comment/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @Valid @RequestBody Comment updatedComment) {
        Comment comment = commentRepository
                .findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        updatedComment.setId(id);
        commentRepository.save(updatedComment);
        return ResponseEntity.ok(updatedComment);
    }


    
}