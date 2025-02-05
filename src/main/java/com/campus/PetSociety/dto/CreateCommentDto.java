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
public class CreateCommentDto {
    
   
    private String emailUser;
    private Long idPost; 
    private String content;
    

    public CreateCommentDto() {
    }

    public CreateCommentDto(String emailUser, Long idPost, String content) {
        this.emailUser = emailUser;
        this.idPost = idPost;
        this.content = content;
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

    

   
    
    
    
    
    
    
    
}
