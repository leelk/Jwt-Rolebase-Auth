package me.leelkarunarathne.rolebaseauth.auth.model.respond;

import lombok.Data;

@Data
public class LoginRS {
    private String jwtAccessToken;
    private String userName;
    private Long id;

}
