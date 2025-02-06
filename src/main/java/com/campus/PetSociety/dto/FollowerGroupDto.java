/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.campus.PetSociety.dto;

/**
 *
 * @author camid
 */
public class FollowerGroupDto {
    
    private String emailFollower;
    private String emailFollowed;
    private String photoFollower;
    private String photoFollowed;
    private String nameFollower;
    private String nameFollowed;

    public FollowerGroupDto() {
    }

    public FollowerGroupDto(String emailFollower, String emailFollowed, String photoFollower, String photoFollowed, String nameFollower, String nameFollowed) {
        this.emailFollower = emailFollower;
        this.emailFollowed = emailFollowed;
        this.photoFollower = photoFollower;
        this.photoFollowed = photoFollowed;
        this.nameFollower = nameFollower;
        this.nameFollowed = nameFollowed;
    }

    public String getEmailFollower() {
        return emailFollower;
    }

    public void setEmailFollower(String emailFollower) {
        this.emailFollower = emailFollower;
    }

    public String getEmailFollowed() {
        return emailFollowed;
    }

    public void setEmailFollowed(String emailFollowed) {
        this.emailFollowed = emailFollowed;
    }

    public String getPhotoFollower() {
        return photoFollower;
    }

    public void setPhotoFollower(String photoFollower) {
        this.photoFollower = photoFollower;
    }

    public String getPhotoFollowed() {
        return photoFollowed;
    }

    public void setPhotoFollowed(String photoFollowed) {
        this.photoFollowed = photoFollowed;
    }

    public String getNameFollower() {
        return nameFollower;
    }

    public void setNameFollower(String nameFollower) {
        this.nameFollower = nameFollower;
    }

    public String getNameFollowed() {
        return nameFollowed;
    }

    public void setNameFollowed(String nameFollowed) {
        this.nameFollowed = nameFollowed;
    }
    
    
    
}
