package com.app.ak1n.tatar.requests;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class LikeCreateRequest {
    Long postId;
    Long userId;
}
