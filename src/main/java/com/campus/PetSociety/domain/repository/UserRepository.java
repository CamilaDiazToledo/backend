/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.campus.PetSociety.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.campus.PetSociety.persistence.entity.*;
import java.util.Optional;

/**
 *
 * @author camid
 */
public interface UserRepository extends JpaRepository<Users, Long>{
    
    
    public Optional<Users> findByUserName(String userName);
    public Optional<Users> findByEmail(String email);
}
