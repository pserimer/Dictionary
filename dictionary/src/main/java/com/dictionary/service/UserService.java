// Group Members:
// 201611007 - Batuhan Bayraktar
// 201711058 - Pelinsu Serimer
// 201711049 - Zeynep Özdoğan

package com.dictionary.service;

import com.dictionary.models.LoginReturn;
import com.dictionary.models.User;

public interface UserService {

    User findUserByEmail(String email);

    User register(User user) throws Exception;

    LoginReturn login(User user) throws Exception;

    User updateUser(String email, User user);

    void deleteUser(String email);

}
