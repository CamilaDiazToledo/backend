/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.campus.PetSociety.web.controller;

import com.campus.PetSociety.domain.repository.PostRepository;
import com.campus.PetSociety.domain.service.PostServiceImpl;
import com.campus.PetSociety.dto.CreatePostDto;
import com.campus.PetSociety.dto.PostDto;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author camid
 */

@RestController
@RequestMapping("/api/post")
public class PostController {
    private final PostServiceImpl postServiceImpl;
    
    @Autowired
    public PostController(PostServiceImpl postServiceImpl) {
        this.postServiceImpl = postServiceImpl;
    }
    
    //CREATE-POST
    @PostMapping("/create")
    public ResponseEntity<PostDto>createPost(@RequestBody CreatePostDto createPostDto) {
        System.out.println("Recibiendo solicitud para crear post: " + createPostDto);
        return postServiceImpl.createPost(createPostDto);
    }
    
    
    
    //GET
    @GetMapping("/all")
    public List<PostDto>getAllPost(){
        return postServiceImpl.getAllPost();
    }
    
    // GET all posts by user ID
    @GetMapping("/all/{email}")
    public List<PostDto> getAllPostByEmailUser(@PathVariable String email) {
        return postServiceImpl.getAllPostByEmailUser(email);
    }
    
    @GetMapping("/followed/{email}")
    public List<PostDto> getFollowedPosts(@PathVariable String email) {
        return postServiceImpl.getPostsByFollowerEmail(email);
    }

    
    //DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        return postServiceImpl.deletePost(id);
    }
    
    //UPDATE
    @PatchMapping("/update/description/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String>updateDescription(@PathVariable Long id,@RequestParam String description){
        Boolean updated = postServiceImpl.updateDescription(id, description);
        if (updated) {
            return ResponseEntity.ok("Description updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found.");
        }
    }
}
