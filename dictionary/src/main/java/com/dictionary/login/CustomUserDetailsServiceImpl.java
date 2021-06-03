package com.dictionary.login;

import com.dictionary.models.User;
import com.dictionary.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service("userDetailsService")
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    private UserService userService;

    private PasswordEncoder passwordEncoder;

    public CustomUserDetailsServiceImpl(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsernameAndDomain(String username, String domain) throws UsernameNotFoundException {
        if (StringUtils.isAnyBlank(username, domain)) {
            throw new UsernameNotFoundException("Username and domain must be provided");
        }
        User user = userService.findUserByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException(
                String.format("Username not found for domain, username=%s, domain=%s", 
                    username, domain));
        }

        Collection<? extends GrantedAuthority> authorities = new ArrayList<>();
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                passwordEncoder.encode(user.getPassword()), true,
                true, true, true, authorities);
    }
}
