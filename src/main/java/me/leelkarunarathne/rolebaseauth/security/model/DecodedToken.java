package me.leelkarunarathne.rolebaseauth.security.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class DecodedToken {

    private String userName;

    private String scope;

    private String exp;

    private Long id;
}
