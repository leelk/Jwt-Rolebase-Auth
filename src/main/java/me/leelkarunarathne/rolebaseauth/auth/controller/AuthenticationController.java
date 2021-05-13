package me.leelkarunarathne.rolebaseauth.auth.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.leelkarunarathne.rolebaseauth.auth.model.request.LoginRQ;
import me.leelkarunarathne.rolebaseauth.auth.model.respond.LoginRS;
import me.leelkarunarathne.rolebaseauth.auth.service.AuthService;
import me.leelkarunarathne.rolebaseauth.security.utill.JwtUtil;
import me.leelkarunarathne.rolebaseauth.user.persistent.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/auth")
@AllArgsConstructor
@Slf4j
public class AuthenticationController {




    private JwtUtil jwtUtil;
    private final AuthService authService;

    @PostMapping(value = "/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRQ loginRQ) throws  Exception {

            Optional<User> userByUserName = authService.findUserByUserName(loginRQ.getUserName());

            if (userByUserName.isPresent()){
                User user = userByUserName.get();
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                if (!passwordEncoder.matches(loginRQ.getPassword(), user.getPassword())){
                    return new ResponseEntity<>("invaild password", HttpStatus.UNAUTHORIZED);

                }

                Map<String, Object> claims = new HashMap<>();

                claims.put("id", user.getId());
                claims.put("userName", user.getUserName());


                String token = jwtUtil.generateToken(user.getUserName(), claims, user.getRole().toString());
                return new ResponseEntity<>(token, HttpStatus.OK);
            }
             return new ResponseEntity<>("invaild", HttpStatus.UNAUTHORIZED);




    }
}
