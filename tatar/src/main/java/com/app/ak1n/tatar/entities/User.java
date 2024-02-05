package com.app.ak1n.tatar.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "user_table")
@Entity
public class User {
    @Id
    Long id;
    String userName;
    String password;


}
