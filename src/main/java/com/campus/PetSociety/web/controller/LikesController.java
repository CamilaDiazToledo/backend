/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.campus.PetSociety.web.controller;

import com.campus.PetSociety.domain.service.LikeServiceImpl;
import com.campus.PetSociety.dto.CreateLikeCommentDto;
import com.campus.PetSociety.dto.CreateLikePostDto;
import com.campus.PetSociety.dto.LikeCommentDto;
import com.campus.PetSociety.dto.LikePostDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author camid
 */
@RestController
@RequestMapping("/api/likes")
public class LikesController {
    
    private final LikeServiceImpl likeServiceImpl;
 
    
    @Autowired
    public LikesController(LikeServiceImpl likeServiceImpl){
        this.likeServiceImpl = likeServiceImpl;
    }
    
    //CREATE-LIKEPOST
    @PostMapping("/create/likepost")
    public ResponseEntity<LikePostDto> createLikePost(@RequestBody CreateLikePostDto createLikePostDto) {
        System.out.println("Recibiendo solicitud para crear like: " + createLikePostDto);
        try {
            ResponseEntity<LikePostDto> response = likeServiceImpl.createLikePost(createLikePostDto);
            System.out.println("Like creado exitosamente: " + response.getBody());
            return response;
        } catch (Exception e) {
            System.err.println("Error al crear like: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
    
    //CREATE-LIKECOMMENT
    @PostMapping("/create/likecomment")
    public ResponseEntity<LikeCommentDto> createLikeComment(@RequestBody CreateLikeCommentDto createLikeCommentDto) {
        System.out.println("Recibiendo solicitud para crear like: " + createLikeCommentDto);
        return likeServiceImpl.createLikeComment(createLikeCommentDto);
    }
    
    
    
    //GET-ALL-LIKEPOST-IDPOST
    @GetMapping("/all/likepost/{idPost}")
    public  List<LikePostDto> getAllLikeByIdPost(@PathVariable Long idPost) {
        return likeServiceImpl.getAllLikeByIdPost(idPost);
    }
    
    
    //GET-ALL-LIKECOMMENT-IDCOMMENT
    @GetMapping("/all/likecomment/{idComment}")
    public  List<LikeCommentDto> getAllLikeByIdComment(@PathVariable Long idComment) {
        return likeServiceImpl.getAllLikeByIdComment(idComment);
    }
    
//    //FIND LIKEPOST
//    @GetMapping("/all/findlikepost/{idPost}/{email}")
//    public ResponseEntity<LikePostDto> findLikedPost(@PathVariable Long idPost, @PathVariable String email) {
//        return likeServiceImpl.findLikedPost(idPost, email);
//    }
//    
//    //FIND LIKECOMMENT
//    @GetMapping("/all/findlikecomment/{idComment}/{email}")
//    public ResponseEntity<LikeCommentDto> findLikedComment(Long idComment, String email) {
//        return likeServiceImpl.findLikedComment(idComment, email);
//    }
    
    //DELETE
    @DeleteMapping("/delete/likepost/{id}")
    public ResponseEntity<Void> deleteLike(@PathVariable Long id){
        
        return likeServiceImpl.deleteLikePost(id);
    }
    
    
    @DeleteMapping("/delete/likecomment/{id}")
    public ResponseEntity<Void> deleteLikeComment(@PathVariable Long id){
        
        return likeServiceImpl.deleteLikeComment(id);
    }
    
    
    
    
    
    
}
