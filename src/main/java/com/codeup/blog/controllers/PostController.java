package com.codeup.blog.controllers;

import com.codeup.blog.model.Post;
import com.codeup.blog.model.User;
import com.codeup.blog.repositories.UsersRepository;
import com.codeup.blog.services.PostSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
public class PostController {


    //    this is the service placeholder that will be final that means it will not be changed
    private final PostSvc postSvc;
    private final UsersRepository usersDao;


    //    dependency injection everything ties together Services + controller / class

    @Autowired
    public PostController(PostSvc postSvc, UsersRepository usersDao) {
        this.postSvc = postSvc;
        this.usersDao = usersDao;

    }

    @GetMapping("/posts")
    public String showAll(Model model) {

        model.addAttribute("posts", postSvc.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String showPost(@PathVariable int id, Model model) {

        model.addAttribute("post", postSvc.findOne((long) id));
        return "posts/show";
    }

    @GetMapping("/posts/{id}/update")
    public String showEditPostForm(@PathVariable int id, Model model) {

        Post post = postSvc.findOne((long) id);
        model.addAttribute("post", post);

        return "posts/update";
    }

    @PostMapping("/posts/{id}/update")
    public String updatePost(@ModelAttribute Post post) {
        User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(user);
        postSvc.save(post);

        return "redirect:/posts";
    }

    @GetMapping("/posts/create")
    public String showCreateForm(Model model) {
        Post post = new Post();
        model.addAttribute("post", post);

        return "posts/create";
    }


    @PostMapping("/posts/create")
    public String postCreate(@Valid Post post, Errors validation,Model model) {


        if (validation.hasErrors()){
            model.addAttribute("errors", validation);
            model.addAttribute("post", post);
            return "posts/create";
        }


        return "redirect:/posts";


    }

    @PostMapping("/posts/{id}/delete")
    public String delete(@ModelAttribute Post post, @PathVariable long id) {

        postSvc.delete(id);
        return "redirect:/posts";
    }
}
