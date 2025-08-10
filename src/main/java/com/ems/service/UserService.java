package com.ems.service;

import com.ems.model.User;

public interface UserService {
    void registerNewUserAsUser(User user) throws Exception;
    User findByEmail(String email);
    void save(User user);
}