// Group Members:
// 201611007 - Batuhan Bayraktar
// 201711058 - Pelinsu Serimer
// 201711049 - Zeynep Özdoğan

package com.dictionary.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.dictionary.models.LoginReturn;
import com.dictionary.models.User;
import com.dictionary.models.dao.UserDao;
import com.dictionary.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final String SECRET = "secretKey";

    private final String salt = BCrypt.gensalt("$2b", 5);

    @Autowired
    UserDao userDao;

    @Override
    public User findUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }

    @Override
    public User register(User user) throws Exception {
        User emailControl = userDao.findUserByEmail(user.getEmail());

        if (emailControl == null) {
            User newUser = new User();
            newUser.setEmail(user.getEmail());

            newUser.setPassword(BCrypt.hashpw(user.getPassword(), salt));

            return userDao.saveAndFlush(newUser);
        }

        throw new Exception("cannot use this email");
    }

    @Override
    public LoginReturn login(User user) throws Exception {
        User userControl = findUserByEmail(user.getEmail());
        LoginReturn loginReturn = new LoginReturn();

        if (userControl != null) {
            if (BCrypt.checkpw(user.getPassword(), userControl.getPassword())) {
                String accessToken = JWT.create()
                        .withSubject(user.getEmail())
                        .sign(Algorithm.HMAC512(SECRET.getBytes()));

                loginReturn.setToken(accessToken);
                loginReturn.setEmail(user.getEmail());
                return loginReturn;

            } else throw new Exception("mismatch");
        } else throw new Exception("user not found");
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
