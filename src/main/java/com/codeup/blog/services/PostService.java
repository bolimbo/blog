//package com.codeup.blog.services;
//
//
//import com.codeup.blog.model.Post;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service("adSvc")
//public class PostService {
//    private List<Post> posts;
//
//    public PostService() {
//        create();
//    }
//
//    public List<Post> findAll() {
//        return posts;
//    }
//
//    public Post findOne(long id) {
//
//        return posts.get((int) (id - 1));
//    }
//
//    public Post save(Post post) {
//        post.setId((long) (posts.size() + 1));
//        posts.add(post);
//        return post;
//    }
//
//
//    private void create() {
//        posts.add(new Post(1l,"hello", "i am at codeup"));
//        posts.add(new Post(2L,"hi", "i am Robot"));
//    }
//}
