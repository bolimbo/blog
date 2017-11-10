package com.codeup.blog.services;

import com.codeup.blog.model.Post;

import com.codeup.blog.repositories.PostsRepository;
import com.codeup.blog.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("postSvc")
public class PostSvc {

    private PostsRepository postDao;
    private UsersRepository usersDao;


    @Autowired
    public PostSvc(PostsRepository postDao, UsersRepository usersDao) {
        this.usersDao = usersDao;
        this.postDao = postDao;
    }

    public Post save(Post post) {
//        post.setId(posts.size() + 1);
//        posts.add(post);

        return postDao.save(post);
    }

//    public Post update(Post post) {
//        post.set((int)post.getId() - 1, post);
//        return post;
//    }

    public Iterable<Post> findAll() {
        return postDao.findAll();
    }

    public Post findOne(long id) {
//        return posts.get((int) id - 1);
        return postDao.findOne(id);
    }

    public void delete(long id) {
        postDao.delete(id);
    }


}