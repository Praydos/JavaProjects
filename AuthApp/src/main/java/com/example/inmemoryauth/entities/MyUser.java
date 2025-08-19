package com.example.inmemoryauth.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "USERS")
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String role;
}
