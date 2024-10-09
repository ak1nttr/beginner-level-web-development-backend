package com.app.ak1n.tatar.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "user_table")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String username;
    String password;
}
