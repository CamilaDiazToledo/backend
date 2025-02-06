/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.campus.PetSociety.persistence.entity;

import com.campus.PetSociety.dto.CreateLikeCommentDto;
import com.campus.PetSociety.dto.CreateLikePostDto;
import com.campus.PetSociety.dto.LikeCommentDto;
import com.campus.PetSociety.dto.LikePostDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;

/**
 *
 * @author camid
 */
@Entity
public class Likes {
    // ----------------- ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLike;
    
    @ManyToOne
    private Users idUser;
    
    @ManyToOne
    private Post idPost;
    
    
    @ManyToOne
    private Comment idComment;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date reactionDate;
    
    @OneToOne(mappedBy = "idLike", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Notify notification;
    
    // ----------------- CONSTRUCTORES
    public Likes() {
    }

    public Likes(Long idLike, Users idUser, Post idPost, Comment idComment, Date reactionDate, Notify notification) {
        this.idLike = idLike;
        this.idUser = idUser;
        this.idPost = idPost;
        this.idComment = idComment;
        this.reactionDate = reactionDate;
        this.notification = notification;
    }
    
    // ----------------- GETTER & SETTER
    public Long getIdLike() {
        return idLike;

    }

    public void setIdLike(Long idLike) {
        this.idLike = idLike;
    }

    public Users getIdUser() {
        return idUser;
    }

    public void setIdUser(Users idUser) {
        this.idUser = idUser;
    }

    public Post getIdPost() {
        return idPost;
    }

    public void setIdPost(Post idPost) {
        this.idPost = idPost;
    }

    public Comment getIdComment() {
        return idComment;
    }

    public void setIdComment(Comment idComment) {
        this.idComment = idComment;
    }

    public Date getReactionDate() {
        return reactionDate;
    }

    public void setReactionDate(Date reactionDate) {
        this.reactionDate = reactionDate;
    }

    public Notify getNotification() {
        return notification;
    }

    
    public void setNotification(Notify notification) {
        this.notification = notification;
    }

    // ----------------- DTOS
    public CreateLikePostDto toDTOCreateLikePost() {
        CreateLikePostDto createLikePostDto = new CreateLikePostDto();
        createLikePostDto.setEmailUser(this.idUser.getEmail());
        createLikePostDto.setIdPost(this.idPost.getIdPost());
        
        return createLikePostDto;
    }
    
    public  static  Likes fromDTOCreateLikePost( CreateLikePostDto createLikePostDto , Users userEntity, Post postEntity){
        Likes like = new Likes();
        like.setIdUser(userEntity);
        like.setIdPost(postEntity);
        like.setReactionDate(new Date());
        return  like;
    }
    
    public LikePostDto toDTOLikePost() {
        LikePostDto likeDTO = new LikePostDto();
        likeDTO.setIdLike(this.idLike);
        likeDTO.setEmailUser(this.idUser.getEmail());
        likeDTO.setName(this.idUser.getName());
        likeDTO.setPhoto(this.idUser.getPhoto());
        likeDTO.setIdPost(this.idPost.getIdPost());
        likeDTO.setName(this.idUser.getName());
        likeDTO.setReactionDate(this.reactionDate);
         
        return likeDTO;

    }
    
    public CreateLikeCommentDto toDTOCreateLikeComment() {
        CreateLikeCommentDto createLikeCommentDto = new CreateLikeCommentDto();
        createLikeCommentDto.setEmailUser(this.idUser.getEmail());
        createLikeCommentDto.setIdComment(this.idComment.getIdComment());
        
        return createLikeCommentDto;

    }
    
    public  static  Likes fromDTOCreateLikeComment( CreateLikeCommentDto createLikeCommentDto, Users userEntity, Comment commentEntity){
        Likes like = new Likes();
        like.setIdUser(userEntity);
        like.setIdComment(commentEntity);
        like.setReactionDate(new Date());
        return  like;
    }

    
    public LikeCommentDto toDTOLikeComment() {
        LikeCommentDto likeDTO = new LikeCommentDto();
        likeDTO.setIdLike(this.idLike);
        likeDTO.setEmailUser(this.idUser.getEmail());
        likeDTO.setIdComment(this.idComment.getIdComment());
        likeDTO.setName(this.idUser.getName());
        likeDTO.setPhoto(this.idUser.getPhoto());
        likeDTO.setReactionDate(this.reactionDate);
         
        return likeDTO;
                
        
    }
    
    
    
}
