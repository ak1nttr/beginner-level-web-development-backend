package com.app.ak1n.tatar.requests;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class UserCreateRequest {
    String username;
    String password;
}
