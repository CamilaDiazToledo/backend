/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.campus.PetSociety.dto;

/**
 *
 * @author camid
 */
public class CreateUserDto {
    
    private String name;
    private String userName;
    private String email;
    private String password;
    

    public CreateUserDto() {
    }

    public CreateUserDto(String name, String userName, String email, String password) {
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   
    
    
    
    @Override
    public String toString() {
        return "CreateUserDTO{" + "name=" + name +
                ", userName=" + userName +
                ", email=" + email +
                ", password=" + password + '}';
    }
    
}
