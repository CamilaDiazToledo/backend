/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.campus.PetSociety.dto;

import java.util.Date;

/**
 *
 * @author camid
 */
public class LikePostDto {
    private Long idLike;
    private String emailUser;
    private Long idPost;
    private String name;
    private String photo;
    private Date reactionDate;
    

    public LikePostDto() {
    }

    public LikePostDto(Long idLike, String emailUser, Long idPost, String name, String photo, Date reactionDate) {
        this.idLike = idLike;
        this.emailUser = emailUser;
        this.idPost = idPost;
        this.name = name;
        this.photo = photo;
        this.reactionDate = reactionDate;
    }

    public Long getIdLike() {
        return idLike;
    }

    public void setIdLike(Long idLike) {
        this.idLike = idLike;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getReactionDate() {
        return reactionDate;
    }

    public void setReactionDate(Date reactionDate) {
        this.reactionDate = reactionDate;
    }

    

    

    

    
    
    
}
