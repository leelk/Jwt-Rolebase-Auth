package me.leelkarunarathne.rolebaseauth.user.controller;

import lombok.AllArgsConstructor;
import me.leelkarunarathne.rolebaseauth.user.model.request.CreateUserRQ;
import me.leelkarunarathne.rolebaseauth.user.persistent.User;
import me.leelkarunarathne.rolebaseauth.user.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public void createUser(@RequestBody CreateUserRQ createUserRQ) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        User user = User.builder()
                .userName(createUserRQ.getUserName())
                .password(encoder.encode(createUserRQ.getPassword()))
                .role(createUserRQ.getUserRole())
                .build();

        userService.createUser(user);
    }

}
