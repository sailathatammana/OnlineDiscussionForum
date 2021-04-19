package se.kth.sda.skeleton.posts;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import se.kth.sda.skeleton.AuditModel.AuditModel;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="posts")
public class Post extends AuditModel {

    @Id
    @GeneratedValue(generator = "post_generator")
    @SequenceGenerator(
            name = "post_generator",
            sequenceName = "post_sequence",
            initialValue =3000
    )
    private Long id;

    @NotBlank
    @Size(min=3, max=100)
    private String title;

    @Column(columnDefinition = "text")
    private String body;


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

}
