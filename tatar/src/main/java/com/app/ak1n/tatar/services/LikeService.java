package com.app.ak1n.tatar.services;

import com.app.ak1n.tatar.entities.Like;
import com.app.ak1n.tatar.entities.Post;
import com.app.ak1n.tatar.entities.User;
import com.app.ak1n.tatar.repository.LikeRepository;
import com.app.ak1n.tatar.requests.LikeCreateRequest;
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
    public List<Like> getAllLikesWithParam(Optional<Long> userId,
                                              Optional<Long> postId) {
        if (userId.isPresent() && postId.isPresent()){
            return likeRepository.findByUserIdAndPostId(userId,postId);
        }else if (userId.isPresent()) {
            return likeRepository.findByUserId(userId);
        } else if (postId.isPresent()) {
            return likeRepository.findByPostId(postId);
        }else return likeRepository.findAll();
    }

    public Like getOneLikeById(Long likeId) {
        return likeRepository.findById(likeId).orElse(null);
    }

    public Like createNewLike(LikeCreateRequest request) {
        Post post = postService.getPostById(request.getPostId());
        User user  = userService.getOneUserById(request.getUserId());
        if (post != null && user != null){
            Like likeToGo = new Like();
            likeToGo.setId(request.getId());
            likeToGo.setUser(user);
            likeToGo.setPost(post);
            return likeRepository.save(likeToGo);
        }
        else return null;
    }

    public void deleteOneLike(Long likeId) {
       Optional<Like> likeToGo = likeRepository.findById(likeId);
       if (likeToGo.isPresent())
            likeRepository.deleteById(likeId);
    }
}
