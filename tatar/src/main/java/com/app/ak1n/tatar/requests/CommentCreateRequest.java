package com.app.ak1n.tatar.requests;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class CommentCreateRequest {
        //comment creating properties
    Long id;
    Long postId;
    Long userId;
    String text;

}
