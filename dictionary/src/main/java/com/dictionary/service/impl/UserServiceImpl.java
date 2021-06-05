// Group Members:
// 201611007 - Batuhan Bayraktar
// 201711058 - Pelinsu Serimer
// 201711049 - Zeynep Özdoğan

package com.dictionary.service.impl;

import com.dictionary.models.User;
import com.dictionary.models.dao.UserDao;
import com.dictionary.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private PasswordEncoder passwordEncoder;

    @Autowired
    UserDao userDao;

    @Override
    public User findUserByEmail(String username) {
        if (StringUtils.isBlank(username)){
            return null;
        } else {
            User user =  new User(username,
                    passwordEncoder.encode("secret"));
            return user;
        }
    }

    @Override
    public User saveUser(User user) {
        return userDao.saveAndFlush(user);
    }

    @Override
    public User updateUser(String email, User user) {
        User retrievedUser = findUserByEmail(email);
        if (user.getBestScore() != null)
            retrievedUser.setBestScore(user.getBestScore());
        if (user.getNoOfWordsSearched() != null)
            retrievedUser.setNoOfWordsSearched(user.getNoOfWordsSearched());
        if (CollectionUtils.isNotEmpty(user.getWords()))
            retrievedUser.setWords(user.getWords());

        return userDao.saveAndFlush(retrievedUser);
    }

    @Override
    public void deleteUser(String email) {
        User user = findUserByEmail(email);
        userDao.deleteById(user.getId());
    }
}
