package com.codeup.blog.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.File;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Posts must have a title")
    @Size(min = 3, message = "A title msut be at least 3 characters long")
    @Column(nullable = false, length = 100)
    private String title;


    @NotBlank(message = "Posts must have a description")
    @Column(nullable = false)
    private String body;

    @Column(insertable = false, updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date date;


    private String uploadfile;


    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private User user;


    public Post() {

    }


    public Post(long id, String title, String body, Date date){
        this.id=id;
        this.title=title;
        this.body=body;
        this.date=date;
    }

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public Post(long id, String title, String body, User user) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.user = user; // this gives me access to properties from user, user.getUsername()
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUsername() {
        return user.getUsername();
    }


    public String getUploadfile() {
        return uploadfile;
    }

    public void setUploadfile(String uploadfile) {
        this.uploadfile = uploadfile;
    }
}




