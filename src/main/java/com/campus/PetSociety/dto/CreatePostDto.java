/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.campus.PetSociety.dto;

import com.campus.PetSociety.persistence.entity.Users;

/**
 *
 * @author camid
 */
public class CreatePostDto {
    
    private String emailUser;
    private String discription;
    private String photo;

    public CreatePostDto() {
        
    }

    public CreatePostDto(String emailUser, String discription, String photo) {
        this.emailUser = emailUser;
        this.discription = discription;
        this.photo = photo;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
    

    
    
}
