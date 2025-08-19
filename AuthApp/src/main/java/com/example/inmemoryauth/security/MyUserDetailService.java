package com.example.inmemoryauth.security;

import com.example.inmemoryauth.entities.MyUser;
import com.example.inmemoryauth.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private MyUserRepository myUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUser> myUser = myUserRepository.findByUsername(username);

        if (myUser.isPresent()) {
            var user = myUser.get();
            return User.builder()
                    .username(user.getUsername())
                    .password("{noop}"+user.getPassword())
                    .roles(getRoles(user))
                    .build();


        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    private String[] getRoles(MyUser myUser){
        String roles = myUser.getRole();
        if(roles.isEmpty()){
            return new String[]{"USER"};
        }else {
            return roles.split(",");
        }

    }
}