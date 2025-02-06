/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.campus.PetSociety.web.controller;

import com.campus.PetSociety.domain.service.*;
import com.campus.PetSociety.dto.FollowerGroupDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;





@RestController
@RequestMapping("/api/follow")
public class FollowerGroupController {
    
    private final FollowerGroupServiceImpl followerGroupServiceImpl;
    
    @Autowired
    public FollowerGroupController(FollowerGroupServiceImpl followerGroupServiceImpl) {
        this.followerGroupServiceImpl = followerGroupServiceImpl;
    }
    
    //CREATE
    @PostMapping("/create/{emailFollower}")
    public ResponseEntity<FollowerGroupDto> followUser(@PathVariable String emailFollower, @RequestParam String emailFollowed) {
        return followerGroupServiceImpl.followUser(emailFollower, emailFollowed);
    }
    
    //DELETE
    @DeleteMapping("/delete/{emailFollower}")
    public void unfollowUser(@PathVariable String emailFollower,@RequestParam String emailFollowed){
        followerGroupServiceImpl.unfollowUser(emailFollower, emailFollowed);
    }
    
    
    
    
    
    
    
    
}