package com.app.ak1n.tatar.controllers;

import com.app.ak1n.tatar.entities.User;
import com.app.ak1n.tatar.requests.UserCreateRequest;
import com.app.ak1n.tatar.requests.UserUpdateRequest;
import com.app.ak1n.tatar.responses.UserResponse;
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
    public List<UserResponse> getAllUsers(){
        return userService.getAllUsers();
    }


    @PostMapping
    public UserResponse createUser(@RequestBody UserCreateRequest newUserCreateRequest){
        return userService.saveNewUser(newUserCreateRequest);
    }

    @GetMapping("/{userId}")
    public User getOneUser(@PathVariable Long userId){
        return userService.getOneUserById(userId);
    }


    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId , @RequestBody UserUpdateRequest newUserUpdateRequest){
        return userService.updateOneUser(userId,newUserUpdateRequest);
    }
    @DeleteMapping("/{userId}")
    public void deleteOneUser(@PathVariable Long userId){
        userService.deleteUserById(userId);
    }
}
