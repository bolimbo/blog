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

    public Post findOne(long id){
        return posts.get((int) id - 1);
    }

    public void createPost(){
        save(new Post("Yo","This is a test"));
        save(new Post("This is my title","Tthis is my description"));
        save(new Post("Robot","I am robot"));
        save(new Post("Food","I am hungry"));
    }


}