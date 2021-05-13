package me.leelkarunarathne.rolebaseauth.user.service;

import me.leelkarunarathne.rolebaseauth.user.persistent.User;

import java.util.Optional;

public interface UserService {
    Optional<User> getByUsername(String username);

    User createUser(User user);
}
