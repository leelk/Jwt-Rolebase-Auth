package me.leelkarunarathne.rolebaseauth.user.persistent;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ROLE role;
    private String userName;
    private String password;
}
