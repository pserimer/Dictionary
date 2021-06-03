package com.dictionary.service.impl;

import com.dictionary.models.User;
import com.dictionary.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Override
    public User findUserByEmail(String email) {
        //TODO: find user from database
        return null;
    }

    @Override
    public User saveUser(User user) {
        //TODO: save user
        return user;
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
        //TODO: update user
        return retrievedUser;
    }

    @Override
    public void deleteUser(String email) {
        User user = findUserByEmail(email);
        // TODO: delete user
    }
}
