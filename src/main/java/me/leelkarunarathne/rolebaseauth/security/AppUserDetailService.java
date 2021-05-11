package me.leelkarunarathne.rolebaseauth.security;

import lombok.AllArgsConstructor;
import me.leelkarunarathne.rolebaseauth.user.persistent.User;
import me.leelkarunarathne.rolebaseauth.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AppUserDetailService  implements UserDetailsService {
    

    private final UserService userService;
    
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.getByUsername(userName);

        List<SimpleGrantedAuthority>  grantedAuthorities = null;
        grantedAuthorities.add(user.getRole());
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword());
        
    }
}
