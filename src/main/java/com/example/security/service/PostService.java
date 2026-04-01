package com.example.security.service;
import com.example.security.model.Post;
import com.example.security.model.User;
import com.example.security.repository.PostRepo;
import com.example.security.repository.UsersRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepo postRepository;
    private final UsersRepo userRepository;

    public Post createPost(Long userId, Post post) {
        User user = userRepository.findById(userId).orElseThrow();
        post.setUser(user);
        return postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public List<Post> getPostsByUser(Long userId) {
        return postRepository.findByUserId(userId);
    }
}
