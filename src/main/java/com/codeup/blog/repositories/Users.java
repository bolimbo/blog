package com.codeup.blog.repositories;

import com.codeup.blog.model.User;
import org.springframework.data.repository.CrudRepository;

public interface Users extends CrudRepository<User, Long> {
    public User findByUsername(String username);
}