package me.leelkarunarathne.rolebaseauth.auth.model.request;

import lombok.Data;

@Data
public class LoginRQ {
    private String userName;
    private String password;
}
