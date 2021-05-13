package me.leelkarunarathne.rolebaseauth.auth.service.impl;

import lombok.AllArgsConstructor;
import me.leelkarunarathne.rolebaseauth.auth.service.AuthService;
import me.leelkarunarathne.rolebaseauth.user.persistent.User;
import me.leelkarunarathne.rolebaseauth.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthServiceImpl  implements AuthService {


    private final UserService userService;

    @Override
    public Optional<User> findUserByUserName(String username) {
       return userService.getByUsername(username);
    }
}
