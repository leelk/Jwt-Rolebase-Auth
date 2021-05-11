package me.leelkarunarathne.rolebaseauth.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {


    @GetMapping("/home")
    public String home() {
        return ("<h1> Welcome home </h1>");
    }

    @GetMapping("/user1")
    public String user1() {
        return ("<h1> Welcome user1 </h1>");
    }

    @GetMapping("/user2")
    public String user2() {
        return ("<h1> Welcome user1 </h1>");
    }
}
