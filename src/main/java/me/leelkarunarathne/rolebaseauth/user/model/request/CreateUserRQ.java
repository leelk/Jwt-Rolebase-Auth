package me.leelkarunarathne.rolebaseauth.user.model.request;

import lombok.Data;
import me.leelkarunarathne.rolebaseauth.user.persistent.ROLE;

@Data
public class CreateUserRQ {

    private String userName;

    private String password;

    private ROLE userRole;
}
