/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.campus.PetSociety.domain.service;

import com.campus.PetSociety.domain.repository.UserRepository;
import java.util.List;
import com.campus.PetSociety.dto.*;
import com.campus.PetSociety.persistence.entity.Users;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * @author camid
 */
@Service
public class SearchUsersServiceImpl implements SearchUsersService {

   
    private final UserRepository userRepositorty;
    
    @Autowired
    public SearchUsersServiceImpl(UserRepository userRepositorty) {
        this.userRepositorty = userRepositorty;
    }
    
    @Transactional
    @Override
    public List<UserDto> searchingUser(String query) {
        List<Users> usersByEmail = userRepositorty.findUsersByEmailSerch(query);
        List<Users> usersByUserName = userRepositorty.findUsersByUserNameContainingIgnoreCase(query);

        List<Users> combinedUsers = new ArrayList<>();
        combinedUsers.addAll(usersByEmail);
        combinedUsers.addAll(usersByUserName);

        System.out.println("Users by Email: " + usersByEmail.size());
        System.out.println("Users by UserName: " + usersByUserName.size());

        List<UserDto> usersDTOList = combinedUsers.stream()
                .map(user -> user.toDTO())
                .collect(Collectors.toList());

        System.out.println("Combined Users DTOs: " + usersDTOList.size());

        return usersDTOList;
    }
    
    
    public SearchUserDto searchAll(String query) {
        SearchUserDto searchUserDto = new SearchUserDto();
        searchUserDto.setUsersDTOList(searchingUser(query));

        System.out.println("User DTOs: " + searchUserDto.getUsersDTOList().size());
        return searchUserDto;
    }
}