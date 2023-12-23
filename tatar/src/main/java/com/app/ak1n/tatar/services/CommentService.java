package com.app.ak1n.tatar.services;

import com.app.ak1n.tatar.entities.Comment;
import com.app.ak1n.tatar.entities.Post;
import com.app.ak1n.tatar.entities.User;
import com.app.ak1n.tatar.repository.CommentRepository;
import com.app.ak1n.tatar.requests.CommentCreateRequest;
import com.app.ak1n.tatar.requests.CommentUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final UserService userService;
    private final CommentRepository commentRepository;
    private final PostService postService;

    public CommentService(CommentRepository commentRepository ,
                          UserService userService ,
                          PostService postService
                          ){
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<Comment> getAllCommentsWithParam(Optional<Long> userId,
                                                 Optional<Long> postId){

        if (userId.isPresent() && postId.isPresent()) {
            return commentRepository.findByUserIdAndPostId(userId.get() , postId.get());
        } // if all parameters exist
        else if (userId.isPresent()){
            return commentRepository.findByUserId(userId.get());
        } //if only user id is given as param
        else if (postId.isPresent()) {
            return commentRepository.findByPostId(postId.get());
        }// if we only have post id
        else
            return commentRepository.findAll();

    }


    public Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public Comment createOneComment(CommentCreateRequest commentCreateRequest) {
        // create a new one and assign all the values to it
        // then return
        User createdUser = userService.getOneUserById(commentCreateRequest.getUserId());
        Post createdPost = postService.getPostById(commentCreateRequest.getPostId());

        if  (   createdPost != null &&
                createdUser != null ){

            Comment commentToGo = new Comment();
            commentToGo.setId(commentCreateRequest.getId());
            commentToGo.setText(commentCreateRequest.getText());
            commentToGo.setUser(createdUser);
            commentToGo.setPost(createdPost);
            return commentRepository.save(commentToGo);
        }
        else return null;
    }

    public Comment updateCommentById(CommentUpdateRequest request,
                                    Long commentId) {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        if (commentOptional.isPresent()) {
            Comment commentToGo = commentOptional.get();
            commentToGo.setText(request.getText());
            return commentRepository.save(commentToGo);
        }else {
            // custom exception
            return null;
        }
    }

    public void deleteCommentById(Long commentId) {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        if (commentOptional.isPresent()) {
            commentRepository.deleteById(commentId);
        }
    }
}
