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

    @GetMapping("/posts/{postsId}/answers")
    public ResponseEntity<List<Comment>> listAllComments(@PathVariable Long postId){
        return ResponseEntity.ok(commentRepository.findByPostId(postId));
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<Comment> createCommentOnArticle(@PathVariable Long postId, @Valid @RequestBody Comment comment) {
        Post post = (Post) postRepository.findById(postId).orElseThrow(ResourceNotFoundException::new);
        comment.setPost(post);
        return ResponseEntity.ok(commentRepository.save(comment));
    }

    @PutMapping("/posts/{postId}/comments{commentsId}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long questionId, @Validated({Comment.PutValidationGroup.class})
    @RequestBody Comment updatedComment) {
        Comment comment = commentRepository.findById(questionId).orElseThrow(ResourceNotFoundException::new);
        updatedComment.setId(comment.getId());
        updatedComment.setPost(comment.getPost());
        return ResponseEntity.ok(commentRepository.save(updatedComment));
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<Comment> deleteComment(@PathVariable Long questionId) {
        Comment comment = commentRepository.findById(questionId).orElseThrow(ResourceNotFoundException::new);
        commentRepository.delete(comment);
        return ResponseEntity.ok(comment);
    }
}



