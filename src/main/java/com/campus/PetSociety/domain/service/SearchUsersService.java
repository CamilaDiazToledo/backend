/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.campus.PetSociety.domain.service;

import com.campus.PetSociety.dto.UserDto;
import java.util.List;

/**
 *
 * @author camid
 */
public interface SearchUsersService {
    List<UserDto> searchingUser(String query);
}
