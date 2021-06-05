// Group Members:
// 201611007 - Batuhan Bayraktar
// 201711058 - Pelinsu Serimer
// 201711049 - Zeynep Özdoğan

package com.dictionary.models.dao;

import com.dictionary.base.BaseDao;
import com.dictionary.models.User;

public interface UserDao extends BaseDao<User> {

    User findUserByEmail(String email);

}
