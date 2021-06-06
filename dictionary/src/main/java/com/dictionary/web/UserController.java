// Group Members:
// 201611007 - Batuhan Bayraktar
// 201711058 - Pelinsu Serimer
// 201711049 - Zeynep Özdoğan

package com.dictionary.web;

import com.dictionary.models.LoginReturn;
import com.dictionary.models.User;
import com.dictionary.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/api/user")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserController {

    private final UserService userService;

    @RequestMapping(path = "/{email}", method = RequestMethod.GET)
    public ResponseEntity<User> findUsersOwnProfile(@PathVariable String email) {
        return new ResponseEntity<>(
                userService.findUserByEmail(email),
                HttpStatus.OK
        );
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<User> register(@RequestBody User user) throws Exception {
        return new ResponseEntity<>(
                userService.register(user),
                HttpStatus.OK
        );
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<LoginReturn> login(@RequestBody User user) throws Exception {
        return new ResponseEntity<>(
                userService.login(user),
                HttpStatus.OK
        );
    }

    @RequestMapping(path = "/{email}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable String email, @RequestBody User user) {
        return new ResponseEntity<>(
                userService.updateUser(email, user),
                HttpStatus.OK
        );
    }

    @RequestMapping(path = "/{email}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable String email) {
        userService.deleteUser(email);
        return new ResponseEntity<>(
                HttpStatus.NO_CONTENT
        );
    }

}
