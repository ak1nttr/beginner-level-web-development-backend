package com.app.ak1n.tatar.responses;


import com.app.ak1n.tatar.entities.Post;
import lombok.Data;

@Data
public class PostResponse {
//attributes

    Long id;
    Long userId;
    String userName;
    String title;
    String text;


    public PostResponse(Post entity){
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.userName = entity.getUser().getUserName();
        this.title = entity.getTitle();
        this.text = entity.getText();
    }
}
