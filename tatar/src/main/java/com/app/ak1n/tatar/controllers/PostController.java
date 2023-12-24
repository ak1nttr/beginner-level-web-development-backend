package com.app.ak1n.tatar.controllers;

import com.app.ak1n.tatar.entities.Post;
import com.app.ak1n.tatar.requests.PostCreateRequest;
import com.app.ak1n.tatar.requests.PostUpdateRequest;
import com.app.ak1n.tatar.responses.PostResponse;
import com.app.ak1n.tatar.services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<PostResponse> getAllPosts(@RequestParam Optional<Long> userId){
        return postService.getAll(userId);
    }

    @GetMapping("/{postId}")
    public Post getOnePost(@PathVariable Long postId){
        return postService.getPostById(postId);
    }


    @PostMapping
    public Post createNewPost(@RequestBody PostCreateRequest newPostRequest){
        return postService.createOnePost(newPostRequest);
    }
    @PutMapping("/{postId}")
    public Post updateOnePost(@PathVariable Long postId , @RequestBody PostUpdateRequest postUpdateRequest){
            return postService.updateOnePostById(postId , postUpdateRequest);
    }
    @DeleteMapping("/{postId}")
    public void deleteOnePost(@PathVariable Long postId){
        postService.deletePostById(postId);
    }
}
