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
import org.springframework.data.repository.query.Param;

/**
 *
 * @author camid
 */
public interface FollowerGroupRepository extends JpaRepository<FollowerGroup, Long> {

//    @Query("SELECT f FROM FollowerGroup f JOIN f.idFollower follower JOIN f.idFollowed followed WHERE follower.email = :followerEmail AND followed.email = :followedEmail")
//    Optional<FollowerGroup> findByFollowerEmailAndFollowedEmail1(String followerEmail, String followedEmail);

    @Query("SELECT fg FROM FollowerGroup fg "
            + "JOIN fg.idFollower uf "
            + "JOIN fg.idFollowed uf2 "
            + "WHERE uf.email = :followerEmail AND uf2.email = :followedEmail")
    Optional<FollowerGroup> findByFollowerEmailAndFollowedEmail(@Param("followerEmail") String followerEmail, @Param("followedEmail") String followedEmail);

    Optional<FollowerGroup> findByIdFollowerAndIdFollowed(Users idFollower, Users idFollowed);
    
    @Query("SELECT f.idFollowed FROM FollowerGroup f JOIN f.idFollower follower WHERE follower.email = :followerEmail")
    List<Users> findFollowedByFollowerEmail(String followerEmail);

    @Query("SELECT f.idFollower FROM FollowerGroup f JOIN f.idFollowed followed WHERE followed.email = :followedEmail")
    List<Users> findFollowerByFollowedEmail(String followedEmail);

}
