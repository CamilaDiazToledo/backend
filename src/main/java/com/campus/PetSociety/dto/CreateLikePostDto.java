/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.campus.PetSociety.dto;

import com.campus.PetSociety.persistence.entity.Post;
import com.campus.PetSociety.persistence.entity.Users;

/**
 *
 * @author camid
 */
public class CreateLikePostDto {
    private String emailUser;
    private Long idPost;

    public CreateLikePostDto() {
    }

    public CreateLikePostDto(String emailUser, Long idPost) {
        this.emailUser = emailUser;
        this.idPost = idPost;
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

   

    
    
    
}
