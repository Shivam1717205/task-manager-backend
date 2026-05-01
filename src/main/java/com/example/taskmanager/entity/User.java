package com.example.taskmanager.entity;

import com.example.taskmanager.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role; //ADMIN / USER
}
