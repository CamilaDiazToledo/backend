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
public class CommentDto {
    private Long idComment;
    private String emailUser;
    private Long idPost;
    private String content;
    private Date createdAt;
    private Date updateAt;
    private String userName;
    private String userPhoto;
    private List<LikeCommentDto> likeCommentDto;

    public CommentDto() {
    }

    public CommentDto(Long idComment, String emailUser, Long idPost, String content, Date createdAt, Date updateAt, String userName, String userPhoto, List<LikeCommentDto> likeCommentDto) {
        this.idComment = idComment;
        this.emailUser = emailUser;
        this.idPost = idPost;
        this.content = content;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.userName = userName;
        this.userPhoto = userPhoto;
        this.likeCommentDto = likeCommentDto;
    }

    public Long getIdComment() {
        return idComment;
    }

    public void setIdComment(Long idComment) {
        this.idComment = idComment;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public Long getIdPost() {
        return idPost;
    }

    public void setIdPost(Long idPost) {
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public List<LikeCommentDto> getLikeCommentDto() {
        return likeCommentDto;
    }

    public void setLikeCommentDto(List<LikeCommentDto> likeCommentDto) {
        this.likeCommentDto = likeCommentDto;
    }

    
    

   
    
    
}
