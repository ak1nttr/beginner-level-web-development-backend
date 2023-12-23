package com.app.ak1n.tatar.controllers;


import com.app.ak1n.tatar.entities.Comment;
import com.app.ak1n.tatar.requests.CommentCreateRequest;
import com.app.ak1n.tatar.requests.CommentUpdateRequest;
import com.app.ak1n.tatar.services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> getAllComments
            (@RequestParam Optional<Long> userId
            ,@RequestParam Optional<Long> postId){

        return commentService.getAllCommentsWithParam(userId , postId);
    }


    @GetMapping("/{commentId}")
    public Comment getOneComment(@PathVariable Long commentId){
        return commentService.getCommentById(commentId);
    }


    @PostMapping
    public Comment createNewComment(
            @RequestBody CommentCreateRequest commentCreateRequest){
        return commentService.createOneComment(commentCreateRequest);
    }

    @PutMapping("/{commentId}")
    public Comment updateOneCommentById(@RequestBody CommentUpdateRequest request ,
                                 @PathVariable Long commentId){
            return commentService.updateCommentById(request, commentId);
    }

    @DeleteMapping("/{commentId}")
    public void deleteCommentById(@PathVariable Long commentId) {
        commentService.deleteCommentById(commentId);
    }



}
