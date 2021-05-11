package me.leelkarunarathne.rolebaseauth.user.service.impl;

import me.leelkarunarathne.rolebaseauth.user.persistent.User;
import me.leelkarunarathne.rolebaseauth.user.repository.UserRepository;
import me.leelkarunarathne.rolebaseauth.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserServiceImpl implements UserService {

   @Autowired
    private UserRepository userRepository;

    @Override
    public User getByUsername(String username) {
        Optional<User> userByUserName = userRepository.findUserByUserName(username);
        return userByUserName.get();
    }
}
