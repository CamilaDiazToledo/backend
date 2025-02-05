/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.campus.PetSociety.domain.service;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.campus.PetSociety.dto.*;

/**
 *
 * @author camid
 */
public interface UserService {
    
    List<UserDto> getAllUsers();
    ResponseEntity<UserDto>getUserById(String email);
    ResponseEntity<UserDto>createUser(CreateUserDto createUserDto);
    ResponseEntity<UserDto>findByEmail(String email);
    void verifyEmailPassword(String username, String password);
    ResponseEntity<UserDto>findByEmailToCreate(String email);
    ResponseEntity<UserDto>findByUserName(String userName);
    ResponseEntity<UserDto>findByUserNameToCreate(String userName);
    Boolean updateLastLogin(String email);
    Boolean updatePhoto(String email,String photo);
    Boolean updateAll(String email, String fullName, String newPhoto, String newBiography);
    Boolean updateBiography(String email,String biography);
    Boolean updateActive(String email);
}
