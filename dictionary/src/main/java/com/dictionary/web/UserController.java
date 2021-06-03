package com.dictionary.web;

import com.dictionary.service.UserService;
import com.dictionary.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
@RequestMapping("/api/user")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserController {

    private final UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<User> findUsersOwnProfile(Principal principal) {
        return new ResponseEntity<>(
                userService.findUserByEmail(principal.getName()),
                HttpStatus.OK
        );
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return new ResponseEntity<>(
                userService.saveUser(user),
                HttpStatus.OK
        );
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(Principal principal, @RequestBody User user) {
        return new ResponseEntity<>(
                userService.updateUser(principal.getName(), user),
                HttpStatus.OK
        );
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(Principal principal) {
        userService.deleteUser(principal.getName());
        return new ResponseEntity<>(
                HttpStatus.NO_CONTENT
        );
    }

}
