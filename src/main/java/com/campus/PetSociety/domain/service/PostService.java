/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.campus.PetSociety.domain.service;

import com.campus.PetSociety.dto.*;
import java.util.List;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author camid
 */
public interface PostService {
    List<PostDto> getAllPost();
    List<PostDto> getAllPostByEmailUser(String userEmail);
    ResponseEntity<PostDto>createPost(CreatePostDto createPostDto);
    ResponseEntity<Void>deletePost(Long postId);
    Boolean updateDescription(Long id,String description);
    List<PostDto> getPostsByFollowerEmail(String email);
    
}
