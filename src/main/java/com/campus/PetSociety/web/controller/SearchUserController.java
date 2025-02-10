/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.campus.PetSociety.web.controller;

import com.campus.PetSociety.domain.service.SearchUsersServiceImpl;
import com.campus.PetSociety.dto.SearchUserDto;
import com.campus.PetSociety.dto.UserDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author camid
 */
@RestController
@RequestMapping("/api/search")
public class SearchUserController {
    
    private final SearchUsersServiceImpl searchUsersService;

    @Autowired
    public SearchUserController(SearchUsersServiceImpl searchUsersService) {
        this.searchUsersService = searchUsersService;
    }

    @GetMapping("/searchUsers")
    public ResponseEntity<SearchUserDto> searchUsers(@RequestParam("query") String query) {
        SearchUserDto searchUserDto = searchUsersService.searchAll(query);
        return ResponseEntity.ok(searchUserDto);
    }
    
    
    
}
