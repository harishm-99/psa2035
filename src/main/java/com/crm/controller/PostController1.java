package com.crm.controller;

import com.crm.entity.Post;
import com.crm.service.PostService1;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/posts")
public class PostController1 {

    private PostService1 postService;

    public PostController1(PostService1 postService){
        this.postService = postService;
    }

    @PostMapping
    public String createPost(
            @RequestBody Post post
    ){
        postService.createPost(post);
        return "saved";
    }

    @DeleteMapping
    public void deletePost(@RequestParam long id){
        postService.deletePost(id);
    }
}
