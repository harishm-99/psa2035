package com.crm.service;

import com.crm.entity.Comment;
import com.crm.entity.Post;
import com.crm.repository.CommentRepository;
import com.crm.repository.PostRepository;

public class CommentService1 {

    private PostRepository postRepository;
    private CommentRepository commentRepository;

    public CommentService1(PostRepository postRepository, CommentRepository commentRepository){
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public void createComment(long postId, Comment comment){
        Post post = postRepository.findById(postId).get();

        comment.setPost(post);
        commentRepository.save(comment);
    }
}
