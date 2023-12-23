package com.app.ak1n.tatar.services;

import com.app.ak1n.tatar.entities.Post;
import com.app.ak1n.tatar.entities.User;
import com.app.ak1n.tatar.repository.PostRepository;
import com.app.ak1n.tatar.requests.PostCreateRequest;
import com.app.ak1n.tatar.requests.PostUpdateRequest;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

        private final PostRepository postRepository;
        private final UserService userService;

        public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService=userService;
        }
        public List<Post> getAll(Optional<Long> userId){
        if(userId.isPresent()){
            return postRepository.findByUserId(userId);
        }
        else return postRepository.findAll();
        }

        public Post getPostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
        }

        public Post createOnePost(PostCreateRequest newPostRequest) {
            User user = userService.getOneUserById(newPostRequest.getUserId());
            if(user == null){
                //custom exception
                return null;
            }else {
            Post postToSave = new Post();
            postToSave.setId(newPostRequest.getId());
            postToSave.setText(newPostRequest.getText());
            postToSave.setTitle(newPostRequest.getTitle());
            postToSave.setUser(user);
            return postRepository.save(postToSave);
            }
        }

    public Post updateOnePostById(Long postId , PostUpdateRequest postUpdateRequest) {
            Optional<Post> post = postRepository.findById(postId);
            if(post.isPresent()){
                Post postToUpdate = new Post();
                postToUpdate.setId(postId);
                postToUpdate.setTitle(postUpdateRequest.getTitle());
                postToUpdate.setText(postUpdateRequest.getText());
                return postRepository.save(postToUpdate);
            }else
                return null;
    }

    public void deletePostById(Long postId) {
            Optional<Post> optionalPost = postRepository.findById(postId);
            if (optionalPost.isPresent())
                postRepository.deleteById(postId);

    }
}
