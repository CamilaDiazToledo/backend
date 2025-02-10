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
public class UserDto {
    
    
    private String name;
    private String userName;
    private String email;
    private String biography;
    private String photo;
    private Date creationDate;
    private Date lastLogin;
    private Date updatedAt;
    private Boolean active;
    private Boolean friend;

    public UserDto() {
    }

    public UserDto(String name, String userName, String email, String biography, String photo, Date creationDate, Date lastLogin, Date updatedAt, Boolean active,Boolean friend) {
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.biography = biography;
        this.photo = photo;
        this.creationDate = creationDate;
        this.lastLogin = lastLogin;
        this.updatedAt = updatedAt;
        this.active = active;
        this.friend = friend;
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getFriend() {
        return friend;
    }

    public void setFriend(Boolean friend) {
        this.friend = friend;
    }
    
    

  

    
    
    
}
