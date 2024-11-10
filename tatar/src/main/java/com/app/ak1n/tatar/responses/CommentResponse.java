package com.app.ak1n.tatar.responses;

import com.app.ak1n.tatar.entities.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentResponse {
    Long id;
    Long postId;
    Long userId;
    String text;

    public CommentResponse(Comment comment){
        this.id = comment.getId();
        this.postId = comment.getPost().getId();
        this.userId = comment.getUser().getId();
        this.text = comment.getText();
    }
}
