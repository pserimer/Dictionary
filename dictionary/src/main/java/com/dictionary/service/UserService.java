// Group Members:
// 201611007 - Batuhan Bayraktar
// 201711058 - Pelinsu Serimer
// 201711049 - Zeynep Özdoğan

package com.dictionary.service;

import com.dictionary.models.User;

public interface UserService {

    User findUserByEmail(String email);

    User saveUser(User user);

    User register(User user) throws Exception;

    String login(User user) throws Exception;

    User updateUser(String email, User user);

    void deleteUser(String email);

}
