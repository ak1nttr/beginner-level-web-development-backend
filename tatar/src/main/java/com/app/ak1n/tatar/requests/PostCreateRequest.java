package com.app.ak1n.tatar.requests;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class PostCreateRequest {

    String text;
    String title;
    Long userId;
}
