package com.app.ak1n.tatar.services;

import com.app.ak1n.tatar.entities.User;
import com.app.ak1n.tatar.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public User saveNewUser(@RequestBody User newUser){
        return userRepository.save(newUser);
    }
    public User getOneUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }
    public User updateOneUser(Long id , User newUser){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            User foundUser = user.get();
            foundUser.setUserName(newUser.getUserName());
            foundUser.setPassword(newUser.getPassword());
            foundUser.setId(newUser.getId());
            return  userRepository.save(foundUser);
        }
        else return null;
    }

    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }
}
