package com.app.ak1n.tatar.repository;


import com.app.ak1n.tatar.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

}
