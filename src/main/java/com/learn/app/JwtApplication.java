package com.learn.app;


import com.learn.app.model.entity.User;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class JwtApplication {

    private static List<User> userDB = new ArrayList<>();
    public static void main(String[] args) {
        SpringApplication.run(JwtApplication.class, args);
    }

    public static void addUser(User user) {
        userDB.add(user);
    }

    public static User getUser(String userName) {
        Optional<User> user = userDB.stream().filter(e -> e.getUserName().equals(userName)).findFirst();
        return user.orElse(null);
    }

}
