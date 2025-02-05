/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.campus.PetSociety.web.controller;

import com.campus.PetSociety.domain.service.CommentServiceImpl;
import com.campus.PetSociety.dto.CommentDto;
import com.campus.PetSociety.dto.CreateCommentDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author camid
 */
@RestController
@RequestMapping("/api/comment")
public class CommentController {
    
    private final CommentServiceImpl commentServiceImpl;
    
    
    @Autowired
    public CommentController(CommentServiceImpl commentServiceImpl) {
        this.commentServiceImpl = commentServiceImpl;
    }
    
    //CREATE-POST
    @PostMapping("/create")
    public ResponseEntity<CommentDto> createComment(@RequestBody CreateCommentDto createCommentDto){
        System.out.println("Recibiendo solicitud para crear comment: " + createCommentDto);
        return commentServiceImpl.createComment(createCommentDto);
    }
    
    // GET all comments by post ID
    @GetMapping("/all/{postId}")
    public List<CommentDto> getAllCommentByIdPost(@PathVariable Long postId){
        return commentServiceImpl.getAllCommentByIdPost(postId);
    }
    
    //DELETE
    @DeleteMapping("/delete/{commenttId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commenttId){
        return commentServiceImpl.deleteComment(commenttId);
    }
    
    //UPDATE
    @PatchMapping("/update/content/{id}")
    public ResponseEntity<String>updateContent(@PathVariable Long id,@RequestParam String content){
        Boolean updated = commentServiceImpl.updateContent(id, content);
        if (updated) {
            return ResponseEntity.ok("Description updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found.");
        }
    
    }
    
    
    
}
