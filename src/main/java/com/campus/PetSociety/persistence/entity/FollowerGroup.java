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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;

/**
 *
 * @author camid
 */
@Entity
public class FollowerGroup {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFG;
    
    @ManyToOne
    private Users idFollower;
    
    @ManyToOne
    private Users idFollowed;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public FollowerGroup() {
    }

    public FollowerGroup(Long idFG, Users idFollower, Users idFollowed, Date date) {
        this.idFG = idFG;
        this.idFollower = idFollower;
        this.idFollowed = idFollowed;
        this.date = date;
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

    

    

    
    
    
}
