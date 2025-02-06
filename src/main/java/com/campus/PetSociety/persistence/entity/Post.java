/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.campus.PetSociety.persistence.entity;

import com.campus.PetSociety.dto.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author camid
 */
@Entity
public class Post {

    // ----------------- ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPost;

    @ManyToOne
    private Users idUser;
    @Size(min = 5, max = 500, message = "La descripción debe tener entre 5 y 500 caracteres.")
    private String discription;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;

    private String photo;
    
    @OneToOne(mappedBy = "idPost", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Notify notification;

    // ----------------- LISTAS
    @OneToMany(mappedBy = "idPost", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Likes> likes = new ArrayList<>();

    @OneToMany(mappedBy = "idPost", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Comment> commentList = new ArrayList<>();

    // ----------------- CONSTRUCTORES
    public Post() {
    }

    public Post(Long idPost, Users idUser, String discription, Date createdAt, Date updateAt, String photo, Notify notification) {
        this.idPost = idPost;
        this.idUser = idUser;
        this.discription = discription;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.photo = photo;
        this.notification = notification;
    }
    public Long getIdPost() {
        return idPost;
    }

    public void setIdPost(Long idPost) {    
        this.idPost = idPost;
    }

    public Users getIdUser() {
        return idUser;
    }

    public void setIdUser(Users idUser) {
        this.idUser = idUser;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Notify getNotification() {
        return notification;
    }

    public void setNotification(Notify notification) {
        this.notification = notification;
    }

    public List<Likes> getLikes() {
        return likes;
    }

    public void setLikes(List<Likes> likes) {
        this.likes = likes;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    // ----------------- GETTER & SETTER
    public void setCommentList(List<Comment> commentList) {    
        this.commentList = commentList;
    }

    // ----------------- LIST FIXED  
    //likes
    public void addLikes(Likes likes) {
        this.likes.add(likes);
        likes.setIdPost(this);
    }

    public void removeLikes(Likes likes) {
        this.likes.remove(likes);
        likes.setIdPost(null);
    }

    //comments
    public void addComment(Comment comment) {
        this.commentList.add(comment);
        comment.setIdPost(this);
    }

    public void removeComment(Comment comment) {
        this.commentList.remove(comment);
        comment.setIdPost(null);
    }

    // ----------------- DTOS  
    public CreatePostDto toDTOCreate() {
        CreatePostDto createPostDto = new CreatePostDto();
        createPostDto.setEmailUser(this.idUser.getEmail());
        createPostDto.setDiscription(this.discription);
        createPostDto.setPhoto(this.photo);
        return createPostDto;

    }

    public static Post fromDTOCreate(CreatePostDto createPostDto, Users userEntity) {
        Post post = new Post();
        post.setIdUser(userEntity);
        post.setDiscription(createPostDto.getDiscription());
        post.setCreatedAt(new Date());
        post.setPhoto(createPostDto.getPhoto());
        return post;
    }

    public PostDto toDTO() {
        PostDto postDTO = new PostDto();
        postDTO.setPostId(this.idPost);
        postDTO.setEmailUser(this.idUser.getEmail());
        postDTO.setDescription(this.discription);
        postDTO.setPhoto(this.photo);
        postDTO.setCreatedAt(this.createdAt);
        postDTO.setPhoto(this.photo);
        postDTO.setUpdateAt(this.updateAt);
        
        
        // Aquí convertimos la lista de Likes a LikeCommentDto
        List<LikePostDto> likePostDtoList = this.likes.stream()
                .map(like -> like.toDTOLikePost())
                .collect(Collectors.toList());
        
        // Establecemos la lista convertida en commentDto
        postDTO.setLikePostDto(likePostDtoList);
        
        
        List<CommentDto> commentDtoList = this.commentList.stream()
                .map(comment -> comment.toDTO())
                .collect(Collectors.toList());
        
        postDTO.setCommentDto(commentDtoList);
        
        return postDTO;

    }

}
