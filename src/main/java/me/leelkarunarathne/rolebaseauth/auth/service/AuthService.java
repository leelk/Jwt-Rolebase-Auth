package me.leelkarunarathne.rolebaseauth.auth.service;

import me.leelkarunarathne.rolebaseauth.user.persistent.User;

import java.util.Optional;

public interface AuthService {

    Optional<User> findUserByUserName(String username);
}
