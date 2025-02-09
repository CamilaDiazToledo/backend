/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.campus.PetSociety.persistence.entity;

import com.campus.PetSociety.dto.NotificationDto;
import jakarta.persistence.Entity;
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
public class Notify {

    // ----------------- ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNotification;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    private Boolean opened;

    @OneToOne
    private Comment comments;

    @OneToOne
    private Likes likes;


    @OneToOne
    private FollowerGroup followerGroup;

    @ManyToOne
    private Users idUser;

    // ----------------- CONSTRUCTORES
    public Notify() {
    }

    public Notify(Comment idComment, Users idUser) {
        this.comments = idComment;
        this.idUser = idUser;
        this.date = new Date();
        this.opened = Boolean.FALSE;
        
    }

    public Notify(Likes likeId, Users userId) {
        this.likes = likeId;
        this.idUser = userId;
        this.date = new Date();
        this.opened = Boolean.FALSE;
    }

    public Notify(FollowerGroup idFollowerGroup, Users idUser) {
        this.followerGroup = idFollowerGroup;
        this.idUser = idUser;
        this.date = new Date();
        this.opened = Boolean.FALSE;
    }
    
    
    

    public Notify(Date date, FollowerGroup idFollowerGroup, Users idUser) {
        this.date = date;
        this.followerGroup = idFollowerGroup;
        this.idUser = idUser;
    }
    public Long getIdNotification() {
        return idNotification;

    }    

    public void setIdNotification(Long idNotification) {
        this.idNotification = idNotification;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getOpened() {
        return opened;
    }

    public void setOpened(Boolean opened) {
        this.opened = opened;
    }

    public Comment getComments() {
        return comments;
    }

    public void setComments(Comment comments) {
        this.comments = comments;
    }

    public Likes getLikes() {
        return likes;
    }

    public void setLikes(Likes likes) {
        this.likes = likes;
    }

    public FollowerGroup getFollowerGroup() {
        return followerGroup;
    }

    public void setFollowerGroup(FollowerGroup followerGroup) {
        this.followerGroup = followerGroup;
    }

    public Users getIdUser() {
        return idUser;
    }

    // ----------------- GETTER & SETTER
    public void setIdUser(Users idUser) { 
        this.idUser = idUser;
    }

    // ----------------- DTOS 
    public NotificationDto toDTO() {
        NotificationDto notificationDto = new NotificationDto();

        notificationDto.setIdNotification(this.idNotification);
        notificationDto.setDate(this.date);
        notificationDto.setEmailRecipient(this.idUser.getEmail());
        notificationDto.setStatus(this.opened);
        
//        notificationDto.setUserPhoto(this.idUser.getPhoto());
//        notificationDto.setName(this.idUser.getName());
//        notificationDto.setUsername(this.idUser.getUserName());


// Determinar el tipo de notificación y asignar los valores correspondientes
if (this.followerGroup != null) {
    notificationDto.setTipo("Follow");
    notificationDto.setIdFollow(this.followerGroup.getIdFG());
    notificationDto.setEmail(this.followerGroup.getIdFollower().getEmail());
    notificationDto.setUserPhoto(this.followerGroup.getIdFollower().getPhoto());
    notificationDto.setUsername(this.followerGroup.getIdFollower().getUserName());
    notificationDto.setName(this.followerGroup.getIdFollower().getName());
} else if (this.likes != null) {
    notificationDto.setTipo("Like");
    notificationDto.setIdLike(this.likes.getIdLike());
    notificationDto.setEmail(this.likes.getIdUser().getEmail());
    notificationDto.setUserPhoto(this.likes.getIdUser().getPhoto());
    notificationDto.setUsername(this.likes.getIdUser().getUserName());
    notificationDto.setName(this.likes.getIdUser().getName());
} else if (this.comments != null) {
    notificationDto.setTipo("Comment");
    notificationDto.setIdComment(this.comments.getIdComment());
    notificationDto.setEmail(this.comments.getIdUser().getEmail());
    notificationDto.setUserPhoto(this.comments.getIdUser().getPhoto());
    notificationDto.setUsername(this.comments.getIdUser().getUserName());
    notificationDto.setName(this.comments.getIdUser().getName());
}



return notificationDto;
    }
    
    
    
    
    
    

}



