package com.crm.controller;

import com.crm.entity.Comment;
import com.crm.entity.Post;
import com.crm.service.CommentService1;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController1 {

    private CommentService1 commentService;

    public CommentController1(CommentService1 commentService){
        this.commentService = commentService;
    }

    @PostMapping
    public String createComment(
            @RequestBody Comment comment,
            @RequestParam long postId
            ){
        commentService.createComment(postId, comment);
        return "comment created successfully";
    }
}
