package se.kth.sda.skeleton.comments;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;
import se.kth.sda.skeleton.AuditModel.AuditModel;
import se.kth.sda.skeleton.posts.Post;

import javax.persistence.*;
import javax.swing.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "comments")
public class Comment extends AuditModel {

    protected interface PutValidationGroup {
   };

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotBlank
    @Column(nullable = false)
    private String comment;

    @NotBlank
    @Column(nullable = false)
    private String authorName;

    @Length(min = 5, max=100, message = "Password length most be between 5-100 characters")
    @Column(name = "password")
    private String password;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "comment_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull(groups={PutValidationGroup.class})
    @JsonIgnore
    private Post post;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
