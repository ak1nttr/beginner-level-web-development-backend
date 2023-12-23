package com.app.ak1n.tatar.requests;


import com.app.ak1n.tatar.entities.Post;
import com.app.ak1n.tatar.entities.User;
import lombok.Data;

@Data
public class CommentCreateRequest {
        //comment creating properties
    Long id;
    Long postId;
    Long userId;
    String text;

}
