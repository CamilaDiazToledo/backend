/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.campus.PetSociety.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author camid
 */
public class LoginDto {
    
    @JsonIgnore
    private String email;

    @JsonIgnore
    private String pass;

    private String token;

    public LoginDto() {
    }

    public LoginDto(String user, String pass, String token) {
        this.email = user;
        this.pass = pass;
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    

    
}
