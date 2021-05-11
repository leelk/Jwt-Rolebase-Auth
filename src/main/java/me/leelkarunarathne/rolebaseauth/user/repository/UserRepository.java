package me.leelkarunarathne.rolebaseauth.user.repository;

import me.leelkarunarathne.rolebaseauth.user.persistent.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUserName(String userName);
}
