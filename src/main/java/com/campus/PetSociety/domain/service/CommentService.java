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
public interface CommentService {
    
    List<CommentDto> getAllCommentByIdPost(Long postId);
    ResponseEntity<CommentDto>createComment(CreateCommentDto createCommentDto);
    ResponseEntity<Void>deleteComment(Long commenttId);
    Boolean updateContent(Long id,String content);
    
}
