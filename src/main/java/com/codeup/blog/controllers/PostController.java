package com.codeup.blog.controllers;

import com.codeup.blog.model.Post;
import com.codeup.blog.model.User;
import com.codeup.blog.repositories.PostsRepository;
import com.codeup.blog.repositories.UsersRepository;
import com.codeup.blog.services.PostSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


@Controller
public class PostController {


    //    this is the service placeholder that will be final that means it will not be changed
    private final PostSvc postSvc;
    private final UsersRepository usersDao;
    private PostsRepository posts;


    //    dependency injection everything ties together Services + controller / class

    @Autowired
    public PostController(PostSvc postSvc, UsersRepository usersDao, PostsRepository posts) {
        this.postSvc = postSvc;
        this.usersDao = usersDao;
        this.posts = posts;

    }
    @Value("${file-upload-path}")
    private String uploadPath;

    @GetMapping("/")
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
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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
    public String postCreate(
            @RequestParam(name = "file")MultipartFile uploadedFile, Model model, @Valid Post post, Errors validation) {
        String filename = uploadedFile.getOriginalFilename();

        Path filepath = Paths.get(uploadPath, filename);

        File destinationFile = new File(filepath.toString());

        try{
            uploadedFile.transferTo(destinationFile);
            model.addAttribute("message", "File success uploaded");

        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", "Oops something went wrong");

        }

        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("post", post);
            return "posts/create";
        }
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(user);
        post.setUploadfile(filename);
        postSvc.save(post);

        return "redirect:/posts";


    }

    @PostMapping("/posts/{id}/delete")
    public String delete(@ModelAttribute Post post, @PathVariable long id) {

        postSvc.delete(id);
        return "redirect:/posts";
    }

    @GetMapping("/posts.json")
    public @ResponseBody
    Iterable<Post> viewAllPostsInJSONFormat() {
        return posts.findAll();
    }

    @GetMapping("/posts/ajax")
    public String viewAllAdsWithAjax() {
        return "posts/ajax";
    }
}


