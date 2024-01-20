package com.learn.app.service;

import com.learn.app.model.dto.UserDto;

public interface UserService {

    UserDto getUserDetails(String userName);
    boolean createUser(UserDto user);
}
