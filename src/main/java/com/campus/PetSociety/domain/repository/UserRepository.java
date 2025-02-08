/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.campus.PetSociety.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.campus.PetSociety.persistence.entity.*;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author camid
 */
public interface UserRepository extends JpaRepository<Users, Long> {

    public Optional<Users> findByUserName(String userName);

    public Optional<Users> findByEmail(String email);

    @Query("SELECT u FROM Users u "
            + "WHERE u.active = true "
            + // Solo usuarios activos
            "AND u NOT IN ("
            + "    SELECT f.idFollowed FROM FollowerGroup f WHERE f.idFollower = :user"
            + ")")
    List<Users> findUsersNotFollowedBy(@Param("user") Users user);
}
