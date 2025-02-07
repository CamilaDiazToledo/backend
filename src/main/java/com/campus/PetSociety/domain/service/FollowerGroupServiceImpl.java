/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.campus.PetSociety.domain.service;

import ch.qos.logback.core.joran.spi.ActionException;
import com.campus.PetSociety.domain.repository.FollowerGroupRepository;
import com.campus.PetSociety.domain.repository.UserRepository;
import com.campus.PetSociety.dto.CreateFollowerGroupDto;
import com.campus.PetSociety.dto.FollowerGroupDto;
import com.campus.PetSociety.dto.UserDto;
import com.campus.PetSociety.persistence.entity.FollowerGroup;
import com.campus.PetSociety.web.exceptions.ActionNotAllowed;
import com.campus.PetSociety.persistence.entity.Users;
import com.campus.PetSociety.web.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
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
public class FollowerGroupServiceImpl implements FollowerGroupService {

    private final UserRepository userRepositorty;
    private final FollowerGroupRepository followerGroupRespository;

    @Autowired
    public FollowerGroupServiceImpl(UserRepository userRepositorty, FollowerGroupRepository followerGroupRespository) {
        this.userRepositorty = userRepositorty;
        this.followerGroupRespository = followerGroupRespository;
    }

//CREATE......................................................................
//    @Transactional
//    @Override
//    public ResponseEntity<FollowerGroup> followUser(String emailFollower, String emailFollowed) {
//        System.out.println("Intentando seguir. Follower: " + emailFollower + ", Followed: " + emailFollowed);
//
//        Optional<Users> possibleFollower = userRepositorty.findByEmail(emailFollower);
//        Optional<Users> possibleFollowed = userRepositorty.findByEmail(emailFollowed);
//
//        if (possibleFollower.isPresent() && possibleFollowed.isPresent()) {
//            Users follower = possibleFollower.get();
//            Users followed = possibleFollowed.get();
//
//            System.out.println("Follower encontrado: " + follower.getEmail() + ", Followed encontrado: " + followed.getEmail());
//
//            // Verificación adicional
//            if (follower.getEmail().equals(followed.getEmail())) {
//                System.out.println("El usuario está tratando de seguirse a sí mismo. Acción no permitida.");
//                throw new ActionNotAllowed("A user cannot follow themselves.");
//            }
//
//            CreateFollowerGroupDto followerGroupCreated = new CreateFollowerGroupDto(follower.getEmail(), followed.getEmail());
//            FollowerGroup followGroup = new FollowerGroup(follower, followed);
//
//            System.out.println("FollowerGroup creado exitosamente.");
//            return ResponseEntity.ok(followerGroupRespository.save(followGroup));
//        } else {
//            System.out.println("Uno o ambos usuarios no fueron encontrados. Proceso de seguimiento fallido.");
//            throw new ActionNotAllowed("Followers process failure: one or both users not found.");
//        }
//    }
    @Transactional
    @Override
    public ResponseEntity<FollowerGroupDto> followUser(String emailFollower, String emailFollowed) {
        System.out.println("Intentando seguir. Follower: " + emailFollower + ", Followed: " + emailFollowed);

        Optional<Users> possibleFollower = userRepositorty.findByEmail(emailFollower);
        Optional<Users> possibleFollowed = userRepositorty.findByEmail(emailFollowed);

        if (possibleFollower.isPresent() && possibleFollowed.isPresent()) {
            Users follower = possibleFollower.get();
            Users followed = possibleFollowed.get();

            System.out.println("Follower encontrado: " + follower.getEmail() + ", Followed encontrado: " + followed.getEmail());

            // Verificación adicional
            if (follower.getEmail().equals(followed.getEmail())) {
                System.out.println("El usuario está tratando de seguirse a sí mismo. Acción no permitida.");
                throw new ActionNotAllowed("A user cannot follow themselves.");
            }

            CreateFollowerGroupDto followerGroupCreated1 = new CreateFollowerGroupDto(follower.getEmail(), followed.getEmail());

            FollowerGroup followerGroupCreated = FollowerGroup.fromDTOCreate(followerGroupCreated1, follower, followed);

            followerGroupCreated = followerGroupRespository.save(followerGroupCreated);
            return ResponseEntity.ok(followerGroupCreated.toDTO());
        } else {
            System.out.println("Uno o ambos usuarios no fueron encontrados. Proceso de seguimiento fallido.");
            throw new ActionNotAllowed("Followers process failure: one or both users not found.");
        }
    }

//DELETE.........................................................................    
    @Transactional
    @Override
    public void unfollowUser(String emailFollower, String emailFollowed) {
        Optional<Users> follower = userRepositorty.findByEmail(emailFollower);
        Optional<Users> followed = userRepositorty.findByEmail(emailFollowed);

        if (follower.isPresent() && followed.isPresent()) {

            Optional<FollowerGroup> followToRemove = followerGroupRespository.findByIdFollowerAndIdFollowed(follower.get(), followed.get());

            if (followToRemove.isPresent()) {
                followed.get().removeFollowed(followToRemove.get());
                follower.get().removeFollowers(followToRemove.get());
              followerGroupRespository.delete(followToRemove.get());
                userRepositorty.save(   followed.get());
                System.out.println("Follow removed successfully");
            }
        } else {
            throw new NotFoundException("Followed or follower user not found");
        }

    }

//GET.........................................................................
    //todo 
    @Override
    public List<UserDto> getFolloweds(String emailFollower) {
        List<Users> followedUsers = followerGroupRespository.findFollowedByFollowerEmail(emailFollower);

        // Transformar la lista de Users a UserDto
        return followedUsers.stream()
                .map(user -> new UserDto(user.getName(), user.getEmail(),
                user.getBiography(), user.getPhoto(),
                user.getCreationDate(), user.getLastLogin(),
                user.getUpdatedAt(), user.getActive()))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getFollowers(String emailFollowed) {

        List<Users> followerUsers = followerGroupRespository.findFollowerByFollowedEmail(emailFollowed);

        // Transformar la lista de Users a UserDto
        return followerUsers.stream()
                .map(user -> new UserDto(user.getName(), user.getEmail(),
                user.getBiography(), user.getPhoto(),
                user.getCreationDate(), user.getLastLogin(),
                user.getUpdatedAt(), user.getActive()))
                .collect(Collectors.toList());
    }

}
