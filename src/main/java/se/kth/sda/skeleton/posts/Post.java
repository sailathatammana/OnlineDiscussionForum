package se.kth.sda.skeleton.posts;

import se.kth.sda.skeleton.comments.Comment;

import javax.persistence.*;
import java.util.List;

// @TODO add Hibernate annotations
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String body;

    @OneToMany(mappedBy = "commentForPost", cascade = CascadeType.ALL)
    private List<Comment> comments;

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Post() {
    }

    public Post(String title,String body) {
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    

}
