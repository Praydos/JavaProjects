package com.example.inmemoryauth.repository;

import com.example.inmemoryauth.entities.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyUserRepository extends JpaRepository<MyUser, Integer> {
    Optional<MyUser> findByUsername(String username);
}
