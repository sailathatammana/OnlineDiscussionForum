package se.kth.sda.skeleton.posts;

import se.kth.sda.skeleton.comments.Comment;
import se.kth.sda.skeleton.user.User;

import javax.persistence.*;
import java.util.List;

// @TODO add Hibernate annotations
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String postTitle;
    private String postBody;

    @OneToMany(mappedBy = "commentForPost", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @ManyToOne
    private User user;

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Post() {
    }

    public Post(String postTitle,String postBody) {
        this.postTitle = postTitle;
        this.postBody = postBody;
    }

    public String getpostTitle() {
        return postTitle;
    }

    public void setpostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getpostBody() {
        return postBody;
    }

    public void setpostBody(String postBody) {
        this.postBody = postBody;
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
