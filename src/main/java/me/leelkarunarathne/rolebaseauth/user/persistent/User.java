package me.leelkarunarathne.rolebaseauth.user.persistent;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class User {

    @Id
    private Long id;
    private ROLE role;
    private String userName;
    private String password;
}
