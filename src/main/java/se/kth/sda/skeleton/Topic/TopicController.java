package se.kth.sda.skeleton.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.kth.sda.skeleton.Exception.ResourceNotFoundException;
import se.kth.sda.skeleton.posts.Post;
import se.kth.sda.skeleton.posts.PostRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
public class TopicController {

    TopicRepository topicRepository;
    PostRepository postRepository;

    @Autowired
    public TopicController(TopicRepository topicRepository, PostRepository postRepository) {
        this.topicRepository = topicRepository;
        this.postRepository = postRepository;
    }

    @GetMapping("/topics")
    public ResponseEntity<List<Topic>> listTopics() {
        return ResponseEntity.ok(topicRepository.findAll());
    }

    @GetMapping("/posts/{postId}/topics")
    public ResponseEntity<Set<Topic>> listTopicsOnArticle(@PathVariable Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(ResourceNotFoundException::new);
        return ResponseEntity.ok(post.getTopics());
    }

    @PostMapping("/topics")
    public ResponseEntity<Topic> createTopic(@Valid @RequestBody Topic topic) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(topicRepository.save(topic));
    }

    @PostMapping("/posts/{postId}/topics")
    public ResponseEntity<Topic> createTopicOnArticle(@PathVariable Long postId, @Valid @RequestBody Topic topicParams) {
        Post post = postRepository.findById(postId).orElseThrow(ResourceNotFoundException::new);
        Topic topic = topicRepository.findByName(topicParams.getName())
                .orElse(topicParams);
        Set<Post> topicPosts = topic.getPosts();
        topicPosts.add(post);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(topicRepository.save(topic));
    }

    @PutMapping("/topics/{id}")
    public ResponseEntity<Topic> updateTopic(@PathVariable Long id, @Valid @RequestBody Topic updatedTopic) {
        topicRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        updatedTopic.setId(id);
        return ResponseEntity.ok(topicRepository.save(updatedTopic));
    }

    @DeleteMapping("/topics/{id}")
    public ResponseEntity<Topic> deleteTopic(@PathVariable Long id) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        topicRepository.delete(topic);
        return ResponseEntity.ok(topic);
    }

    @DeleteMapping("/posts/{postId}/topics/{topicId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTopicOnArticle(@PathVariable Long articleId, @PathVariable Long topicId) {
        Post article = postRepository.findById(articleId)
                .orElseThrow(ResourceNotFoundException::new);
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow((ResourceNotFoundException::new));

        if (topic.getPosts().contains(article)) {
            topic.getPosts().remove(article);
            topicRepository.save(topic);
        } else {
            throw new ResourceNotFoundException();
        }
    }

}
