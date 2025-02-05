/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.campus.PetSociety.dto;

import com.campus.PetSociety.persistence.entity.*;

/**
 *
 * @author camid
 */
public class CreateLikeCommentDto {
     private String emailUser;
    private Long idComment;

    public CreateLikeCommentDto() {
    }

    public CreateLikeCommentDto(String emailUser, Long idComment) {
        this.emailUser = emailUser;
        this.idComment = idComment;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public Long getIdComment() {
        return idComment;
    }

    public void setIdComment(Long idComment) {
        this.idComment = idComment;
    }

    

    
    
    
}
