package com.learn.app.controller;

import com.learn.app.JwtApplication;
import com.learn.app.model.dto.LoginRequest;
import com.learn.app.model.dto.LoginResponse;
import com.learn.app.model.dto.UserDto;
import com.learn.app.model.entity.User;
import com.learn.app.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/user/v1")
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("registration")
    public ResponseEntity<String> createUser(@RequestBody UserDto user) {

        try {
            JwtApplication.addUser(User.builder().userName(user.getUserName()).password(user.getPassword()).build());
            return ResponseEntity.ok("User created successfully");
        }catch (Exception ex) {
            log.error("Error in creating user ", ex);
        }
        return ResponseEntity.ok("User created failed !!!");
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {

        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
        String userName = authentication.getName();
        User user = User.builder().userName(userName).build();
        String token = jwtUtil.createToken(user);
        LoginResponse loginRes = LoginResponse.builder().userName(userName).token(token).build();

        return ResponseEntity.ok(loginRes);
    }
}
