package com.codeup.blog.services;

import com.codeup.blog.model.Post;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("postSvc")
public class PostSvc {
    private List<Post> posts = new ArrayList<>();

    public PostSvc(){
        createPost();
    }

    public Post save(Post post) {
        post.setId(posts.size() + 1);
        posts.add(post);
        return post;
    }
    public List<Post> findall() {
        return posts;
    }

    public Post findOne(int id){
        return posts.get(id - 1);
    }

    public void createPost(){
        posts.add(new Post("Yo","This is a test"));
        posts.add(new Post("This is my title","Tthis is my description"));
        posts.add(new Post("Robot","I am robot"));
        posts.add(new Post("Food","I am hungry"));
    }


}