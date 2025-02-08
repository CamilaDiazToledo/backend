/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.campus.PetSociety.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.campus.PetSociety.domain.service.*;
import com.campus.PetSociety.dto.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author camid
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserServiceImpl userServiceimpl;

    @Autowired
    public UserController(UserServiceImpl userServiceimpl) {
        this.userServiceimpl = userServiceimpl;
    }

    //CREATE
    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserDto createUserDto) {
        System.out.println("Recibiendo solicitud para crear usuario: " + createUserDto);
        return userServiceimpl.createUser(createUserDto);
    }

    //GET
    @GetMapping("/all")
    public List<UserDto> getAllUsers() {
        return userServiceimpl.getAllUsers();
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String email) {
        return userServiceimpl.getUserById(email);
    }

    //UPDATE
    @PatchMapping("/update/photo/{email}")
    public ResponseEntity<String> updatePhoto(@PathVariable String email, @RequestParam String photo) {
        Boolean updated = userServiceimpl.updatePhoto(email, photo);

        if (updated) {
            return ResponseEntity.ok("Photo updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
    }

    @PatchMapping("/update/biography/{email}")
    public ResponseEntity<String> updateBiography(@PathVariable String email, @RequestParam String biography) {
        
        Boolean updated = userServiceimpl.updateBiography(email, biography);

        if (updated) {
            return ResponseEntity.ok("Biography updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
    }

    @PatchMapping("/update/all/{email}")
    public ResponseEntity<String> updateAll(
            @PathVariable String email,
            @RequestParam String fullName, @RequestParam String newPhoto,
            @RequestParam String newBiography) {

        Boolean updated = userServiceimpl.updateAll(email, fullName, newPhoto, newBiography);

        if (updated) {
            return ResponseEntity.ok("All info updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
    }

    @PatchMapping("/update/active/{email}")
    public ResponseEntity<String> updateDeactive(@PathVariable String email) {
        Boolean updated = userServiceimpl.updateActive(email);

        if (updated) {
            return ResponseEntity.ok("User active status updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
    }

}
