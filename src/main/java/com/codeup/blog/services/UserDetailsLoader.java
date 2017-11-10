package com.codeup.blog.services;

import com.codeup.blog.model.User;
import com.codeup.blog.model.UserWithRoles;
import com.codeup.blog.repositories.Users;
import com.codeup.blog.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
@Service
public class UserDetailsLoader implements UserDetailsService {
    private final Users users;
    private UsersRepository repository;


    @Autowired
    public UserDetailsLoader(Users users, UsersRepository repository){
        this.users = users;
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = users.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user found for " + username);
        }

        return new UserWithRoles(user, Collections.emptyList());
    }

    }


