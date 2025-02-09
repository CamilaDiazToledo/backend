/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.campus.PetSociety.domain.service;

import com.campus.PetSociety.domain.repository.FollowerGroupRepository;
import com.campus.PetSociety.domain.repository.NotifyRepository;
import com.campus.PetSociety.domain.repository.UserRepository;
import com.campus.PetSociety.dto.NotificationDto;
import com.campus.PetSociety.persistence.entity.FollowerGroup;
import com.campus.PetSociety.persistence.entity.*;
import com.campus.PetSociety.web.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
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
public class NotifyServiceImpl implements NotifyService {

    private final UserRepository userRepositorty;
    private final FollowerGroupRepository followerGroupRespository;
    private final NotifyRepository notifyRepository;

    @Autowired
    public NotifyServiceImpl(UserRepository userRepositorty, FollowerGroupRepository followerGroupRespository, NotifyRepository notifyRepository) {
        this.userRepositorty = userRepositorty;
        this.followerGroupRespository = followerGroupRespository;
        this.notifyRepository = notifyRepository;
    }

    //CREATE
    @Transactional
    @Override
    public ResponseEntity<NotificationDto> createNotificationFollow(FollowerGroup follow, Users user) {
        Notify notificationfollow = new Notify(follow, user);
        notificationfollow = notifyRepository.save(notificationfollow);
        NotificationDto notificationFolowerDTO = notificationfollow.toDTO();
        return ResponseEntity.ok(notificationFolowerDTO);
    }

    @Transactional
    @Override
    public ResponseEntity<NotificationDto> createNotificationLike(Likes like, Users user) {
        Notify notificationLike = new Notify(like, user);
        notificationLike = notifyRepository.save(notificationLike);
        NotificationDto notificationLikeDTO = notificationLike.toDTO();
        return ResponseEntity.ok(notificationLikeDTO);
    }

    @Transactional
    @Override
    public ResponseEntity<NotificationDto> createNotificationComment(Comment comment, Users user) {
        Notify notificationComment = new Notify(comment, user);
        notificationComment = notifyRepository.save(notificationComment);
        NotificationDto notificationCommentDTO = notificationComment.toDTO();
        return ResponseEntity.ok(notificationCommentDTO);

    }

    @Transactional
    @Override
    public void updateOpened(Long idNotification) {

        Optional< Notify> notification = notifyRepository.findById(idNotification);
        if (notification.isEmpty()) {
            throw new NotFoundException("Notification not found ");

        }
        notification.get().setOpened(true);
        notifyRepository.save(notification.get());
    }

    @Transactional
    @Override
    public void updateOpened1(Long idNotification) {

        Optional< Notify> notification = notifyRepository.findById(idNotification);
        if (notification.isEmpty()) {
            throw new NotFoundException("Notification not found ");

        }
        notification.get().setOpened(true);
        notifyRepository.save(notification.get());
    }

    //DELETE.........................................................................    
//    @Transactional
//    @Override
//    public void unfollowUser(String emailFollower, String emailFollowed) {
//        Optional<Users> follower = userRepositorty.findByEmail(emailFollower);
//        Optional<Users> followed = userRepositorty.findByEmail(emailFollowed);
//        
//        if (follower.isPresent() && followed.isPresent()) {
//            
//            Optional<FollowerGroup> followToRemove = followerGroupRespository.findByIdFollowerAndIdFollowed(follower.get(), followed.get());
//            
//            if (followToRemove.isPresent()) {
//                followed.get().removeFollowed(followToRemove.get());
//                follower.get().removeFollowers(followToRemove.get());
//                notifyServiceImpl.deleteNotify(followToRemove.get(),followed.get());
//                followerGroupRespository.delete(followToRemove.get());
//                userRepositorty.save(followed.get());
//                System.out.println("Follow removed successfully");
//            }
//        } else {
//            throw new NotFoundException("Followed or follower user not found");
//        }
//        
//    }
    @Transactional
    @Override
    public void deleteNotify(FollowerGroup follow, Users user) {
        Optional<Notify> notification = notifyRepository.findNotifyByFollowerGroupAndIdUser(follow, user);
        if (notification.isEmpty()) {
            throw new NotFoundException("Notification not found for the given follower group and user.");
        }

        Notify notificationToDelete = notification.get();
        notifyRepository.delete(notificationToDelete);
    }

    @Transactional
    @Override
    public List<NotificationDto> getUserNotifications(String email) {
        Optional<Users> user = userRepositorty.findByEmail(email);
        if (user.isPresent()) {
            List<Notify> notifications = notifyRepository.findByToUserEmail(email);
            System.out.println("Notifications for user " + email + ": " + notifications);
            return notifications.stream()
                    .map(Notify::toDTO)
                    .collect(Collectors.toList());
        } else {
            System.out.println("User not found: " + email);
        }
        return new ArrayList<>();
    }

}
