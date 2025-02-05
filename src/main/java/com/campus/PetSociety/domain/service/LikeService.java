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
public interface LikeService {
    
    
    List<LikePostDto> getAllLikeByIdPost(Long postId);
    ResponseEntity<LikePostDto>createLikePost(CreateLikePostDto createLikePostDto);
    
   
    List<LikeCommentDto> getAllLikeByIdComment(Long commentId);
    ResponseEntity<LikeCommentDto>createLikeComment(CreateLikeCommentDto createLikeCommentDto);
    
    
    
    ResponseEntity<Void>deleteLike(Long idLike);
}
