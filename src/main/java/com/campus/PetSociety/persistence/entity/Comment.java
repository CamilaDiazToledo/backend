/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.campus.PetSociety.persistence.entity;

import com.campus.PetSociety.dto.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author camid
 */
@Entity
public class Comment {
    // ----------------- ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComment;
    
    @ManyToOne
    private Users idUser;
    
    @ManyToOne
    private Post idPost;
    
    
    @Column(nullable = false)
    private String content;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;
    
    // ----------------- LISTAS
    @OneToMany(mappedBy = "idComment", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Likes> likes = new ArrayList<>();
    
    
    // ----------------- CONSTRUCTORES
    public Comment() {
    }

    public Comment(Long idComment, Users idUser, Post idPost, String content, Date createdAt, Date updateAt) {
        this.idComment = idComment;
        this.idUser = idUser;
        this.idPost = idPost;
        this.content = content;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }
    
    // ----------------- GETTER & SETTER
    public Long getIdComment() {
        return idComment;
    }

    public void setIdComment(Long idComment) {
        this.idComment = idComment;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
    

    // ----------------- LIST FIXED   
    //post
    public void addLikes(Likes like) {
        this.likes.add(like);
        like.setIdComment(this);
    }
    
    public void removeLikes(Likes like){
        this.likes.remove(like);
        like.setIdComment(null);
    } 
    
    // ----------------- DTOS  
    public CreateCommentDto toDTOCreate() {
        CreateCommentDto createCommentDto = new CreateCommentDto();
        createCommentDto.setEmailUser(this.idUser.getEmail());
        createCommentDto.setIdPost(this.idPost.getIdPost());
        createCommentDto.setContent(this.content);
        return createCommentDto;

    }
    
    
    public static Comment fromDTOCreate(CreateCommentDto createCommentDto, Users userEntity, Post postEntity) {
        Comment comment = new Comment();
        comment.setIdUser(userEntity);
        comment.setIdPost(postEntity);
        comment.setContent(createCommentDto.getContent());
        comment.setCreatedAt(new Date());
        
        return comment;
    }
    
    
    public CommentDto toDTO() {
        CommentDto commentDto = new CommentDto();
        commentDto.setIdComment(this.idComment);
        commentDto.setIdUser(this.idUser.getIdUser());
        commentDto.setIdPost(this.idPost.getIdPost());
        commentDto.setContent(this.content);
        commentDto.setCreatedAt(this.createdAt);
        commentDto.setUpdateAt(this.updateAt);
        commentDto.setUserName(this.idUser.getName());
        commentDto.setUserPhoto(this.idUser.getPhoto());
        
        // Aquí convertimos la lista de Likes a LikeCommentDto
        List<LikeCommentDto> likeCommentDtoList = this.likes.stream()
                .map(like -> like.toDTOLikeComment())
                .collect(Collectors.toList());
        
        // Establecemos la lista convertida en commentDto
        commentDto.setLikeCommentDto(likeCommentDtoList);
        
        
        return commentDto;

    }
    
    

}
