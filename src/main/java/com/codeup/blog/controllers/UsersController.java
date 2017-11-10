package com.codeup.blog.controllers;

import com.codeup.blog.model.User;
import com.codeup.blog.repositories.Users;
import com.codeup.blog.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UsersController {
        private UsersRepository usersRepository;
        private PasswordEncoder passwordEncoder;


    public UsersController(PasswordEncoder passwordEncoder, UsersRepository usersRepository){
            this.usersRepository = usersRepository;
            this.passwordEncoder = passwordEncoder;

        }

        @GetMapping("users/sign-up")
    public String showRegistrationForm(Model model){
        model.addAttribute("user", new User());
        return "users/sign-up";
        }

        @PostMapping("users/sign-up")
        public String saveUser(@ModelAttribute User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        usersRepository.save(user);
        return "redirect:/login";
        }

}
