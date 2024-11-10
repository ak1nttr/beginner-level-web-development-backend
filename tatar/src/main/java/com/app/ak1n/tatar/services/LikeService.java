package com.app.ak1n.tatar.services;

import com.app.ak1n.tatar.entities.Like;
import com.app.ak1n.tatar.entities.Post;
import com.app.ak1n.tatar.entities.User;
import com.app.ak1n.tatar.repository.LikeRepository;
import com.app.ak1n.tatar.requests.LikeCreateRequest;
import com.app.ak1n.tatar.responses.LikeResponse;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final PostService postService;
    private final UserService userService;

    LikeService(LikeRepository likeRepository ,
                PostService postService,
                UserService userService){
        this.likeRepository= likeRepository;
        this.postService = postService;
        this.userService = userService;
    }
    public List<LikeResponse> getAllLikesWithParam(Optional<Long> userId,
                                              Optional<Long> postId) {
        if (userId.isPresent() && postId.isPresent()){
            Like like = likeRepository.findByUserIdAndPostId(userId,postId);
            return List.of(new LikeResponse(like));
        }else if (userId.isPresent()) {
            List<Like> likes = likeRepository.findByUserId(userId);
            return likes.stream()
                    .map(LikeResponse::new)
                    .toList();
        } else if (postId.isPresent()) {
            List<Like> likes = likeRepository.findByPostId(postId);
            return likes.stream()
                    .map(LikeResponse::new)
                    .toList();
        }else return likeRepository.findAll()
                .stream()
                .map(LikeResponse::new).toList();
    }

    public LikeResponse getOneLikeById(Long likeId) {
        Like like = likeRepository.findById(likeId).orElse(null);
        if(like != null){
            return new LikeResponse(like);
        }
        return null;
    }

    /*
    @Params
    * userId
    * postId
    */
    public LikeResponse createNewLike(LikeCreateRequest request) {
        Post post = postService.getPostById(request.getPostId());
        User user  = userService.getOneUserById(request.getUserId());
        if (post != null && user != null){
            Like likeToGo = new Like();
            likeToGo.setUser(user);
            likeToGo.setPost(post);
            likeRepository.save(likeToGo);
            return new LikeResponse(likeToGo);
        }
        else return null;
    }

    public void deleteOneLike(Long likeId) {
       Optional<Like> likeToGo = likeRepository.findById(likeId);
       if (likeToGo.isPresent())
            likeRepository.deleteById(likeId);
       else throw new IllegalArgumentException("Like not found with id: "+likeId);
    }
}
