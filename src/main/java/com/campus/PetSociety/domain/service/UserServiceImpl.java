/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.campus.PetSociety.domain.service;

import com.campus.PetSociety.dto.*;
import com.campus.PetSociety.domain.repository.*;
import com.campus.PetSociety.persistence.entity.*;
import com.campus.PetSociety.web.exceptions.DataInUseException;
import com.campus.PetSociety.web.exceptions.*;
import jakarta.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author camid
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepositorty;

    @Autowired
    public UserServiceImpl(UserRepository userRepositorty) {
        this.userRepositorty = userRepositorty;

    }

//CREATE......................................................................
    @Transactional
    @Override
    public ResponseEntity<UserDto> createUser(CreateUserDto createUserDTO) {

        //validacion email
        ResponseEntity<UserDto> userResponse = findByEmailToCreate(createUserDTO.getEmail());
        if (userResponse.getStatusCode().is2xxSuccessful()) {
            throw new DataInUseException(createUserDTO.getEmail() + " Email can not be used");
        }

        //validacion userName
        ResponseEntity<UserDto> userResponse2 = findByUserNameToCreate(createUserDTO.getUserName());
        if (userResponse2.getStatusCode().is2xxSuccessful()) {
            throw new DataInUseException(createUserDTO.getUserName() + " User name can not be used");
        }

        Users usercreated = Users.fromDTOCreate(createUserDTO);
        usercreated = userRepositorty.save(usercreated);
        return ResponseEntity.ok(usercreated.toDTO());

    }

//GET.........................................................................
    // todos
    @Transactional
    @Override
    public List<UserDto> getAllUsers() {

        List<Users> users = userRepositorty.findAll();
        if (users.isEmpty()) {
            throw new NotFoundException("No users found");
        }
        return users.stream()
                .map(Users::toDTO)
                .collect(Collectors.toList());
    }

    //por ID
    @Transactional
    @Override
    public ResponseEntity<UserDto> getUserById(String email) {

        Optional<Users> usersOpt = userRepositorty.findByEmail(email);
        if (usersOpt.isEmpty()) {
            throw new NotFoundException("No user found with this Id" + email);
        }
        return usersOpt.map(users -> ResponseEntity.ok(users.toDTO()))
                .orElse(ResponseEntity.notFound().build());
    }

//FIND.........................................................................
    @Transactional
    @Override
    public ResponseEntity<UserDto> findByEmail(String email) {
        Optional<Users> usersOpt = userRepositorty.findByEmail(email);
        if (usersOpt.isEmpty()) {
            throw new NotFoundException("No user found with this email: " + email);
        }
        return usersOpt.map(users -> ResponseEntity.ok(users.toDTO()))
                .orElse(ResponseEntity.notFound().build());
    }

    @Transactional
    @Override
    public ResponseEntity<UserDto> findByEmailToCreate(String email) {
        Optional<Users> usersOpt = userRepositorty.findByEmail(email);
        if (usersOpt.isPresent()) {
            throw new NotFoundException("User found with this email: " + email);
        }
        return usersOpt.map(users -> ResponseEntity.ok(users.toDTO()))
                .orElse(ResponseEntity.notFound().build());
    }

    @Transactional
    @Override
    public ResponseEntity<UserDto> findByUserName(String userName) {
        Optional<Users> usersOpt = userRepositorty.findByUserName(userName);
        if (usersOpt.isEmpty()) {
            throw new NotFoundException("No user found with this user name: " + userName);
        }
        return usersOpt.map(users -> ResponseEntity.ok(users.toDTO()))
                .orElse(ResponseEntity.notFound().build());
    }

    @Transactional
    @Override
    public ResponseEntity<UserDto> findByUserNameToCreate(String userName) {
        Optional<Users> usersOpt = userRepositorty.findByUserName(userName);
        if (usersOpt.isPresent()) {
            throw new NotFoundException("User found with this user name: " + userName);
        }
        return usersOpt.map(users -> ResponseEntity.ok(users.toDTO()))
                .orElse(ResponseEntity.notFound().build());
    }
//VERIFY.........................................................................

    @Transactional
    @Override
    public void verifyEmailPassword(String email, String password) {
        Optional<Users> userOpt = userRepositorty.findByEmail(email);
        if (!userOpt.isPresent()) {
            throw new NotFoundException("No user found with this email: " + email);
        }

        Users user = userOpt.get();
        if (!user.getPassword().equals(password)) {
            throw new NotFoundException("The password does not match for the email: " + email);
        }
    }

//UPDATE.........................................................................
    @Transactional
    @Override
    public Boolean updateLastLogin(String email) {
        Optional<Users> userOpt = userRepositorty.findByEmail(email);

        if (userOpt.isPresent()) {

            Users user = userOpt.get();
            user.setLastLogin(new Date());

            userRepositorty.save(user);
            System.out.println("LastLogin updated it");
            return true;
        } else {
            System.out.println("User with this email  " + email + " does not exist.");
            return false;
        }
    }

    @Transactional
    @Override
    public Boolean updatePhoto(String email, String photo) {
        Optional<Users> userOpt = userRepositorty.findByEmail(email);

        if (userOpt.isPresent()) {
            Users user = userOpt.get();
            user.setPhoto(photo);
            user.setUpdatedAt(new Date());

            userRepositorty.save(user);
            System.out.println("Photo profile updated.");
            return true;
        } else {
            System.out.println("User with this email " + email + " does not exist.");
            return false;
        }
    }

    @Transactional
    @Override
    public Boolean updateBiography(String email, String biography) {
        Optional<Users> userOpt = userRepositorty.findByEmail(email);

        if (userOpt.isPresent()) {
            Users user = userOpt.get();
            user.setBiography(biography);
            user.setUpdatedAt(new Date());

            userRepositorty.save(user);
            System.out.println("biography profile updated.");
            return true;
        } else {
            System.out.println("User with this email " + email + " does not exist.");
            return false;
        }
    }

    @Transactional
    @Override
    public Boolean updateAll(String email, String fullName, String newPhoto, String newBiography) {
        Optional<Users> userOpt = userRepositorty.findByEmail(email);

        if (userOpt.isPresent()) {

            Users user = userOpt.get();
            user.setName(fullName);
            user.setPhoto(newPhoto);
            user.setBiography(newBiography);
            user.setUpdatedAt(new Date());

            userRepositorty.save(user);
            System.out.println("biography profile updated.");
            return true;
        } else {
            System.out.println("User with this email " + email + " does not exist.");
            return false;
        }

    }

    @Transactional
    @Override
    public Boolean updateActive(String email) {
        Optional<Users> userOpt = userRepositorty.findByEmail(email);

        if (!userOpt.isPresent()) {
            throw new NotFoundException("User not found with email: " + email);
        }
        Users user = userOpt.get();

        if (user.getActive().equals(Boolean.TRUE)) {
            user.setActive(Boolean.FALSE);
        } else {
            user.setActive(Boolean.TRUE);
        }

        userRepositorty.save(user);

        return true;
    }

}
