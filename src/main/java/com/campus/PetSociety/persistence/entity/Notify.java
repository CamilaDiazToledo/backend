/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.campus.PetSociety.persistence.entity;

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
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNotification;
    
    @OneToOne
    private Comment idComment;
    
    @OneToOne
    private Likes idLike;
    
    @ManyToOne
    private Users idUser;
    
    private Boolean readStatus;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    
    private Boolean opened;

    public Notify() {
    }

    public Notify(Likes likeId, Users userId) {
        this.idLike = likeId;
        this.idUser = userId;
    }

    public Notify(Long idNotification, Comment comment, Likes likeId, Users userId, Boolean readStatus, Date date, Boolean opened) {
        this.idNotification = idNotification;
        this.idComment = comment;
        this.idLike = likeId;
        this.idUser = userId;
        this.readStatus = readStatus;
        this.date = date;
        this.opened = opened;
    }

    public Long getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(Long idNotification) {
        this.idNotification = idNotification;
    }

    public Comment getIdComment() {
        return idComment;
    }

    public void setIdComment(Comment idComment) {
        this.idComment = idComment;
    }

    public Likes getIdLike() {
        return idLike;
    }

    public void setIdLike(Likes idLike) {
        this.idLike = idLike;
    }

    public Users getIdUser() {
        return idUser;
    }

    public void setIdUser(Users idUser) {
        this.idUser = idUser;
    }

    public Boolean getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(Boolean readStatus) {
        this.readStatus = readStatus;
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
    
    

    

    
    
}
