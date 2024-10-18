package com.app.ak1n.tatar.responses;

import com.app.ak1n.tatar.entities.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse {
    Long id;
    String username;
    public UserResponse(User user){
        this.id = user.getId();
        this.username = user.getUsername();
    }
}
