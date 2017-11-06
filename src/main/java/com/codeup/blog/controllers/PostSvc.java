package com.codeup.blog.controllers;

import com.codeup.blog.model.Post;

import org.springframework.stereotype.Service;

import java.util.List;

@Service("postSvc")
public class PostSvc {
    private List<Post> posts;

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
        posts.add(new Post("yo","this is a test"));
        posts.add(new Post("this is my title","this is my description"));
    }


}