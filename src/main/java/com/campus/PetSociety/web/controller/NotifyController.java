/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.campus.PetSociety.web.controller;

import com.campus.PetSociety.domain.service.NotifyServiceImpl;
import com.campus.PetSociety.dto.NotificationDto;
import com.campus.PetSociety.persistence.entity.FollowerGroup;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author camid
 */

@RestController
@RequestMapping("/api/notify")
public class NotifyController {
    private final NotifyServiceImpl notifyServiceImpl;
    
    @Autowired
    public NotifyController(NotifyServiceImpl notifyServiceImpl) {
        this.notifyServiceImpl = notifyServiceImpl;
    }
    
    //GET
    @GetMapping("/all/{email}")
    public List<NotificationDto> getUserNotifications(@PathVariable String email){
        return notifyServiceImpl.getUserNotifications(email);
    }
    
    //UPDATE
    @PatchMapping("/update/opened/{idNotification}")
    public void updateOpened(@PathVariable Long idNotification){
        System.out.println("Marking notification as read: " + idNotification);
        notifyServiceImpl.updateOpened(idNotification);
    }
    
    
    
    
    
}   
