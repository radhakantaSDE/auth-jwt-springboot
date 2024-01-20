package com.learn.app.service;

import com.learn.app.JwtApplication;
import com.learn.app.model.dto.UserDto;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    /**
     * Required by Spring Security to fetch user details for Login
     * @param username for which user need to fetch
     * */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.learn.app.model.entity.User user = JwtApplication.getUser(username);
        return User.builder().username(user.getUserName()).password(user.getPassword()).build();
    }

    @Override
    public UserDto getUserDetails(String userName) {
        return null;
    }

    @Override
    public boolean createUser(UserDto user) {
        return false;
    }
}
