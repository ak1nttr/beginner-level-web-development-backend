package com.app.ak1n.tatar.requests;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class UserUpdateRequest {
    String username;
    String password;
}
