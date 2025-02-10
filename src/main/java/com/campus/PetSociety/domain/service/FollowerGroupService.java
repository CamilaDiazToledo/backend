/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.campus.PetSociety.domain.service;

import com.campus.PetSociety.persistence.entity.FollowerGroup;
import java.util.List;
import com.campus.PetSociety.dto.*;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author camid
 */
public interface FollowerGroupService {
    
    
    
//    ResponseEntity<FollowerGroup>followUser1(String emailFollower , String emailFollowed);
//    
    
    ResponseEntity<FollowerGroupDto>followUser(String emailFollower , String emailFollowed);

    void unfollowUser(String emailFollower, String emailFollowed);

    List<UserDto> getFollowers(String emailFollowed );

    List<UserDto> getFolloweds(String emailFollower );
    
    boolean isFollowing(String followerEmail, String followedEmail);
    
}
