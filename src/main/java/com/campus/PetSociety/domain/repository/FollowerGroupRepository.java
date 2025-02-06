/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.campus.PetSociety.domain.repository;

import com.campus.PetSociety.persistence.entity.FollowerGroup;
import com.campus.PetSociety.persistence.entity.Users;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author camid
 */
public interface FollowerGroupRepository extends JpaRepository<FollowerGroup, Long>{
    
    @Query("SELECT f FROM FollowerGroup f JOIN f.idFollower follower JOIN f.idFollowed followed WHERE follower.email = :followerEmail AND followed.email = :followedEmail")
    Optional<FollowerGroup> findByFollowerEmailAndFollowedEmail(String followerEmail, String followedEmail);
    
    @Query("SELECT f.idFollowed FROM FollowerGroup f JOIN f.idFollower follower WHERE follower.email = :followerEmail")
    List<Users> findFollowedByFollowerEmail(String followerEmail);

    @Query("SELECT f.idFollower FROM FollowerGroup f JOIN f.idFollowed followed WHERE followed.email = :followedEmail")
    List<Users> findFollowerByFollowedEmail(String followedEmail);

}
