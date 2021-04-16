package se.kth.sda.skeleton.Topic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import se.kth.sda.skeleton.posts.Post;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany
    private Set<Post> posts = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> articles) {
        this.posts = articles;
    }
}
