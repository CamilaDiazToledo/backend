/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.campus.PetSociety.dto;




import java.util.List;

public class SearchUserDto {

    private List<UserDto> usersDTOList;

    // Constructor
    public SearchUserDto() {
    }

    public SearchUserDto(List<UserDto> usersDTOList) {
        this.usersDTOList = usersDTOList;
    }

    // Getters and Setters
    public List<UserDto> getUsersDTOList() {
        return usersDTOList;
    }

    public void setUsersDTOList(List<UserDto> usersDTOList) {
        this.usersDTOList = usersDTOList;
    }
}
