package com.app.ak1n.tatar.responses;

import com.app.ak1n.tatar.entities.Like;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LikeResponse {
    Long likeId;
    Long postId;
    Long userId;
    public LikeResponse(Like like){
        this.likeId = like.getId();
        this.postId = like.getPost().getId();
        this.userId = like.getUser().getId();
    }
}
