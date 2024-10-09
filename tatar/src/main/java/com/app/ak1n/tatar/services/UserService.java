package com.app.ak1n.tatar.services;

import com.app.ak1n.tatar.entities.User;
import com.app.ak1n.tatar.repository.UserRepository;
import com.app.ak1n.tatar.requests.UserCreateRequest;
import com.app.ak1n.tatar.requests.UserUpdateRequest;
import com.app.ak1n.tatar.responses.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<UserResponse> getAllUsers(){
        List<User> userList = userRepository.findAll();

        return userList
                .stream()
                .map(user -> new UserResponse(user))
                .collect(Collectors.toList());
    }
    public UserResponse saveNewUser(@RequestBody UserCreateRequest newUserCreateRequest){
        User userToSave = new User();
        userToSave.setUsername(newUserCreateRequest.getUsername());
        userToSave.setPassword(newUserCreateRequest.getPassword());
        User savedUser = userRepository.save(userToSave);

        return new UserResponse(savedUser);
    }
    public User getOneUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }
    public User updateOneUser(Long id , UserUpdateRequest newUserUpdateRequest){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            User foundUser = user.get();
            foundUser.setUsername(newUserUpdateRequest.getUsername());
            foundUser.setPassword(newUserUpdateRequest.getPassword());
            return  userRepository.save(foundUser);
        }
        else return null;
    }

    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }
}
