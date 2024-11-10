package com.app.ak1n.tatar.services;

import com.app.ak1n.tatar.entities.Comment;
import com.app.ak1n.tatar.entities.Post;
import com.app.ak1n.tatar.entities.User;
import com.app.ak1n.tatar.repository.CommentRepository;
import com.app.ak1n.tatar.requests.CommentCreateRequest;
import com.app.ak1n.tatar.requests.CommentUpdateRequest;
import com.app.ak1n.tatar.responses.CommentResponse;
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


    public List<CommentResponse> getAllCommentsWithParam(Optional<Long> userId,
                                                 Optional<Long> postId){

        if (userId.isPresent() && postId.isPresent()) {
            List<Comment> comments = commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
            return comments.stream().map(CommentResponse::new).toList();
        }
        else if (userId.isPresent()){
            List<Comment> comments = commentRepository.findByUserId(userId.get());
            return comments.stream().map(CommentResponse::new).toList();
        }
        else if (postId.isPresent()) {
            List<Comment> comments = commentRepository.findByPostId(postId.get());
            return comments.stream().map(CommentResponse::new).toList();
        }
        else
            return commentRepository.findAll().stream().map(CommentResponse::new).toList();
    }


    public CommentResponse getCommentById(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (comment != null)
            return new CommentResponse(comment);
        else throw new IllegalArgumentException("Comment not found with id: " + commentId);
    }

    public CommentResponse createOneComment(CommentCreateRequest commentCreateRequest) {
        // create a new one and assign all the values to it
        // then return
        User createdUser = userService.getOneUserById(commentCreateRequest.getUserId());
        Post createdPost = postService.getPostById(commentCreateRequest.getPostId());

        if  (createdPost != null && createdUser != null){
            Comment commentToGo = new Comment();
            commentToGo.setText(commentCreateRequest.getText());
            commentToGo.setUser(createdUser);
            commentToGo.setPost(createdPost);
            commentRepository.save(commentToGo);
            return new CommentResponse(commentToGo);
        }
        else if(createdPost == null){
            throw new IllegalArgumentException("Post not found with id: " + commentCreateRequest.getPostId());
        }
        else {
            throw new IllegalArgumentException("User not found with id: " + commentCreateRequest.getUserId());
        }
    }

    public CommentResponse updateCommentById(CommentUpdateRequest request,
                                             Long commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isPresent()) {
            Comment commentToGo = comment.get();
            commentToGo.setText(request.getText());
            commentRepository.save(commentToGo);
            return new CommentResponse(commentToGo);
        }else throw new IllegalArgumentException("Comment not found with id: " + commentId);
    }

    public void deleteCommentById(Long commentId){
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isPresent())
            commentRepository.deleteById(commentId);
        else throw new IllegalArgumentException("Comment not found with id: " + commentId);
    }
}
