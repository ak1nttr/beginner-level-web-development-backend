package com.app.ak1n.tatar.requests;

import lombok.Data;

@Data
public class PostCreateRequest {

    String text;
    String title;
    Long userId;
}
