package com.app.ak1n.tatar.controllers;

import com.app.ak1n.tatar.entities.User;
import com.app.ak1n.tatar.services.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }


    @PostMapping
    public User createUser(@RequestBody User newUser){
        return userService.saveNewUser(newUser);
    }

    @GetMapping("/{userId}")
    public User getOneUser(@PathVariable Long userId){
        return userService.getOneUserById(userId);
    }




    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId , @RequestBody User newUser){
        return userService.updateOneUser(userId,newUser);
    }
    @DeleteMapping("/{userId}")
    public void deleteOneUser(@PathVariable Long userId){
        userService.deleteUserById(userId);
    }
}
