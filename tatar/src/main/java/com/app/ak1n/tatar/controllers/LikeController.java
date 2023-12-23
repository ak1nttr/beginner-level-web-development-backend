package com.app.ak1n.tatar.controllers;

import com.app.ak1n.tatar.entities.Like;
import com.app.ak1n.tatar.requests.LikeCreateRequest;
import com.app.ak1n.tatar.services.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
public class LikeController {

    private final LikeService likeService;

    LikeController(LikeService likeService){
        this.likeService = likeService;
    }
    @GetMapping
    public List<Like>  getAllLikesWithParam(@RequestParam Optional<Long> userId,
                                            @RequestParam Optional<Long> postId){
        return likeService.getAllLikesWithParam(userId,postId);
    }

    @GetMapping("/{likeId}")
    public Like getOneLike(@PathVariable Long likeId){
        return likeService.getOneLikeById(likeId);
    }

    @PostMapping
    public Like createNewLike(@RequestBody LikeCreateRequest request){
        return likeService.createNewLike(request);
    }

    @DeleteMapping("/{likeId}")
    public void deleteLikeById(@PathVariable Long likeId){
        likeService.deleteOneLike(likeId);
    }



}
