package com.codeup.blog.controllers;

import com.codeup.blog.model.User;
import com.codeup.blog.repositories.Users;
import com.codeup.blog.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
public class UsersController {
    private UsersRepository usersRepository;
    private PasswordEncoder passwordEncoder;


    public UsersController(PasswordEncoder passwordEncoder, UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;

    }

    @GetMapping("/sign-up")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "users/sign-up";
    }

    @PostMapping("/sign-up")
    public String saveUser(@Valid User user, Errors validation, String username, String email, Model model) {
        User existingUser = usersRepository.findByUsername(user.getUsername());

        User existingEmail = usersRepository.findByEmail(user.getEmail());

        if (existingUser != null) {
            validation.rejectValue("username", "user.username", "username already taken");
        }
        if (existingEmail != null) {
            validation.rejectValue("email", "user.email", "Email already in use");
        }

        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("user", user);

            return "users/sign-up";

        }

        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        usersRepository.save(user);
        return "redirect:/login";
    }

}
