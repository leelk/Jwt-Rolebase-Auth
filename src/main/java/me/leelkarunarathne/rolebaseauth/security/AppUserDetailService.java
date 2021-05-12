package me.leelkarunarathne.rolebaseauth.security;

import lombok.AllArgsConstructor;
import me.leelkarunarathne.rolebaseauth.user.persistent.User;
import me.leelkarunarathne.rolebaseauth.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AppUserDetailService {
    

    private final UserService userService;
    

}
