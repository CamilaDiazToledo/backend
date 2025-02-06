/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.campus.PetSociety.dto;



/**
 *
 * @author camid
 */
public class CreateFollowerGroupDto {
    
    private String emailFollower;
    private String emailFollowed;
    

    public CreateFollowerGroupDto() {
    }

    public CreateFollowerGroupDto(String emailFollower, String emailFollowed) {
        this.emailFollower = emailFollower;
        this.emailFollowed = emailFollowed;
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
    
    
}
