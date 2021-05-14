package me.leelkarunarathne.rolebaseauth.user.controller;

import lombok.AllArgsConstructor;
import me.leelkarunarathne.rolebaseauth.user.model.request.CreateUserRQ;
import me.leelkarunarathne.rolebaseauth.user.persistent.User;
import me.leelkarunarathne.rolebaseauth.user.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public void createUser(@RequestBody CreateUserRQ createUserRQ) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        Optional<User> existUser = userService.getByUsername(createUserRQ.getUserName());

        if (existUser.isPresent()) {
            throw new Exception("username " + createUserRQ.getUserName() + " already taken.");
        }


        User user = User.builder()
                .userName(createUserRQ.getUserName())
                .password(encoder.encode(createUserRQ.getPassword()))
                .role(createUserRQ.getUserRole())
                .build();

        userService.createUser(user);
    }

    @GetMapping(value = "/test")
    @PreAuthorize("hasRole('ONE')")
    public String dosomething() {
        return  "Welcome USER ROLE ONE ";
    }


    @GetMapping(value = "/test2")
    @PreAuthorize("hasRole('TWO')")
    public String doPrint() {
        return  "Welcome USER ROLE TWO ";
    }

    @GetMapping(value = "/test3")
    @PreAuthorize("hasRole('THREE')")
    public String doDisplay() {
        return  "Welcome USER ROLE THREE ";
    }


}
