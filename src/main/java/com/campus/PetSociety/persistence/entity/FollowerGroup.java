/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.campus.PetSociety.persistence.entity;

import com.campus.PetSociety.dto.CreateFollowerGroupDto;
import com.campus.PetSociety.dto.FollowerGroupDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;

/**
 *
 * @author camid
 */
@Entity
public class FollowerGroup {
    // ----------------- ATRIBUTOS

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFG;

    @ManyToOne(fetch = FetchType.LAZY)
    private Users idFollower;//from

    @ManyToOne(fetch = FetchType.LAZY)
    private Users idFollowed;//to 

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    
    @OneToOne(mappedBy = "followerGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    private Notify notification;

    // ----------------- CONSTRUCTORES
    public FollowerGroup() {
    }

    public FollowerGroup( Users idFollower, Users idFollowed) {
        this.idFollower = idFollower;
        this.idFollowed = idFollowed;
        this.date = new Date();
    }
    public Long getIdFG() {
        return idFG;

    }    

    public void setIdFG(Long idFG) {
        this.idFG = idFG;
    }

    public Users getIdFollower() {
        return idFollower;
    }

    public void setIdFollower(Users idFollower) {
        this.idFollower = idFollower;
    }

    public Users getIdFollowed() {
        return idFollowed;
    }

    public void setIdFollowed(Users idFollowed) {
        this.idFollowed = idFollowed;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Notify getNotification() {
        return notification;
    }

    // ----------------- GETTER & SETTER
    public void setNotification(Notify notification) {   
        this.notification = notification;
    }

    // ----------------- DTOS  
    public CreateFollowerGroupDto toDTOCreate() {
        CreateFollowerGroupDto createFollowerGroupDto = new CreateFollowerGroupDto();
        createFollowerGroupDto.setEmailFollowed(this.idFollowed.getEmail());
        createFollowerGroupDto.setEmailFollower(this.idFollower.getEmail());

        return createFollowerGroupDto;
    }

    public static FollowerGroup fromDTOCreate(CreateFollowerGroupDto createFollowerGroupDto, Users userEntityFollower, Users userEntityFollowed) {
        FollowerGroup followerGroup = new FollowerGroup();
        
        followerGroup.setIdFollower(userEntityFollower);
        followerGroup.setIdFollowed(userEntityFollowed);
        followerGroup.setDate(new Date());
        return followerGroup;
    }
    
    public  FollowerGroupDto toDTO(){
        FollowerGroupDto followerGroupDto = new FollowerGroupDto();
        followerGroupDto.setIdFollow(this.getIdFG());
        followerGroupDto.setEmailFollowed(this.idFollowed.getEmail());
        followerGroupDto.setEmailFollower(this.idFollower.getEmail());
        followerGroupDto.setPhotoFollowed(this.idFollowed.getPhoto());
        followerGroupDto.setPhotoFollower(this.idFollower.getPhoto());
        followerGroupDto.setNameFollowed(this.idFollowed.getName());
        followerGroupDto.setNameFollower(this.idFollower.getName());
        
        return followerGroupDto;
    }

}
