package se.kth.sda.skeleton.posts;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import se.kth.sda.skeleton.AuditModel.AuditModel;
import se.kth.sda.skeleton.Topic.Topic;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="posts")
public class Post extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min=3, max=100)
    private String title;

    @Column(columnDefinition = "text")
    private String body;

    public Post() {
    }

   @ManyToMany(mappedBy = "posts")
    Set<Topic> topics = new HashSet<>();

    public Post(String body) {
        this.body = body;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Set<Topic> getTopics() {
        return topics;
    }

    public void setTopics(Set<Topic> topics) {
        this.topics = topics;
    }
}
