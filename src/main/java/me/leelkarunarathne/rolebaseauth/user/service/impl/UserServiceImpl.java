package me.leelkarunarathne.rolebaseauth.user.service.impl;

import me.leelkarunarathne.rolebaseauth.user.persistent.User;
import me.leelkarunarathne.rolebaseauth.user.repository.UserRepository;
import me.leelkarunarathne.rolebaseauth.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

   @Autowired
    private UserRepository userRepository;

    @Override
    public User getByUsername(String username) {
        Optional<User> userByUserName = userRepository.findUserByUserName(username);
        return userByUserName.get();
    }


    @Override
    public User createUser(User user) {
      return userRepository.save(user);
    }
}
