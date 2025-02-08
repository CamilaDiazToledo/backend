/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.campus.PetSociety.dto;

import java.util.Date;
import java.util.List;

/**
 *
 * @author camid
 */
public class PostDto {
    
    private Long postId;
    private String nameUser;
    private String emailUser;
    private String photoUser;
    private String description;
    private String photo;
    private Date createdAt;
    private Date updateAt;
    private List<LikePostDto> likePostDto;
    private List<CommentDto> commentDto;
    

    public PostDto() {
    }

    public PostDto(Long postId, String nameUser, String emailUser, String photoUser, String description, String photo, Date createdAt, Date updateAt, List<LikePostDto> likePostDto, List<CommentDto> commentDto) {
        this.postId = postId;
        this.nameUser = nameUser;
        this.emailUser = emailUser;
        this.photoUser = photoUser;
        this.description = description;
        this.photo = photo;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.likePostDto = likePostDto;
        this.commentDto = commentDto;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getPhotoUser() {
        return photoUser;
    }

    public void setPhotoUser(String photoUser) {
        this.photoUser = photoUser;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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

    public List<LikePostDto> getLikePostDto() {
        return likePostDto;
    }

    public void setLikePostDto(List<LikePostDto> likePostDto) {
        this.likePostDto = likePostDto;
    }

    public List<CommentDto> getCommentDto() {
        return commentDto;
    }

    public void setCommentDto(List<CommentDto> commentDto) {
        this.commentDto = commentDto;
    }


}
