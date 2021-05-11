package me.leelkarunarathne.rolebaseauth.user.service;

import me.leelkarunarathne.rolebaseauth.user.persistent.User;

public interface UserService {
    User getByUsername(String username);
}
