package com.app.ak1n.tatar.responses;


import com.app.ak1n.tatar.entities.Like;
import com.app.ak1n.tatar.entities.Post;
import lombok.Data;

import java.util.List;

@Data
public class PostResponse {


    Long id;
    Long userId;
    String userName;
    String title;
    String text;
    List<Like> likes;

    public PostResponse(Post entity){
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.userName = entity.getUser().getUsername();
        this.title = entity.getTitle();
        this.text = entity.getText();
    }
}
