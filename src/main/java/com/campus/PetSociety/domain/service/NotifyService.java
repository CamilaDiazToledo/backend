/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.campus.PetSociety.domain.service;

import com.campus.PetSociety.dto.NotificationDto;
import com.campus.PetSociety.persistence.entity.*;
import java.util.List;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author camid
 */
public interface NotifyService {
    ResponseEntity<NotificationDto> createNotificationFollow(FollowerGroup follow, Users user);
    ResponseEntity<NotificationDto> createNotificationLike(Likes like, Users user);
    ResponseEntity<NotificationDto> createNotificationComment(Comment comment, Users user);
    List<NotificationDto> getUserNotifications(String email);
    void updateOpened(Long idNotification);
    void updateOpened1(Long idNotification);
    void deleteNotifyFollow(Long idFollow);
    void deleteNotifyLike(Long idLike);
    void deleteNotifyComment(Long idComment);
}

