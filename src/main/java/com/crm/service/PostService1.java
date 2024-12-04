package com.crm.service;

import com.crm.entity.Post;
import com.crm.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService1 {

    private PostRepository postRepository;

    public PostService1(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    public void createPost(Post post){
        postRepository.save(post);
    }

    public void deletePost(long id){
        postRepository.deleteById(id);
    }
}
