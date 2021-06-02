package com.dictionary.service;

import com.dictionary.models.User;

public interface UserService {

    User findUserByEmail(String email); //for login

    User saveUser(User user);

    User updateUser(String email, User user);

    void deleteUser(String email);

}
