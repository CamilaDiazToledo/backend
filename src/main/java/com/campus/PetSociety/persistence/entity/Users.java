/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.campus.PetSociety.persistence.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import com.campus.PetSociety.dto.*;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;


/**
 *
 * @author camid
 */
@Entity
public class Users {
    // ----------------- ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    
    @Column(nullable = false, length = 100)
    private String name;
    
    @Column(nullable = false, unique = true, length = 100)
    private String userName;
    
    @Column(nullable = false, unique = true, length = 100)
    private String email;
    
    @Column(nullable = false, length = 18)
    private String password;
    
    private String biography;

    private String photo;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    
    @Column(nullable = false)
    private Boolean active;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    
    // ----------------- LISTAS
    
    
    @OneToMany(mappedBy = "idUser", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Post> posts = new ArrayList<>();
    
    @OneToMany(mappedBy = "idUser", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Notify> notifications = new ArrayList<>();
    
    @OneToMany(mappedBy = "idUser", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Likes> likes = new ArrayList<>();
    
    @OneToMany(mappedBy = "idUser", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Comment> commentList = new ArrayList<>();
    
    @OneToMany(mappedBy = "idFollower", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<FollowerGroup> followers = new ArrayList<>();
    
    @OneToMany(mappedBy = "idFollowed", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<FollowerGroup> followed = new ArrayList<>();
    
    
    
    
    
    
    
    
    
    
    
    // ----------------- CONSTRUCTORES

    public Users() {
        this.creationDate = new Date();
        this.active = true;
    }
    
    public Users(String name, String userName, String email, String password) {
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.creationDate = new Date();
        this.active = true;
    }

    public Users(Long idUser, String name, String userName, String email, String password, String biography, String photo, Date creationDate, Boolean active, Date lastLogin, Date updatedAt) {
        this.idUser = idUser;
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.biography = biography;
        this.photo = photo;
        this.creationDate = creationDate;
        this.active = active;
        this.lastLogin = lastLogin;
        this.updatedAt = updatedAt;
    }

    
    // ----------------- GETTER & SETTER
    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getCreationDate() {
        return creationDate;
    }


    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    
    // ----------------- LIST FIXED   
    //post
    public void addPost(Post post) {
        this.posts.add(post);
        post.setIdUser(this);
    }
    
    public void removePost(Post post){
        this.posts.remove(post);
        post.setIdUser(null);
    } 
    //notifications
    public void addNotification(Notify notify) {
        this.notifications.add(notify);
        notify.setIdUser(this);
    }
    
    public void removeNotification(Notify notify){
        this.notifications.remove(notify);
        notify.setIdUser(null);
    }
    
    //likes
    public void addLikes(Likes likes) {
        this.likes.add(likes);
        likes.setIdUser(this);
    }
    
    public void removeLikes(Likes likes){
        this.likes.remove(likes);
        likes.setIdUser(null);
    }
    //comments
    public void addComment(Comment comment) {
        this.commentList.add(comment);
        comment.setIdUser(this);
    }
    
    public void removeComment(Comment comment){
        this.commentList.remove(comment);
        comment.setIdUser(null);
    }
    
    //followers
    
    public void addFollowers(FollowerGroup followers) {
        this.followers.add(followers);
        followers.setIdFollower(this);
    }
    
    public void removeFollowers(FollowerGroup followers){
        this.followers.remove(followers);
        followers.setIdFollower(null);
    }
    
    //followers
    
    public void addFollowed(FollowerGroup followed) {
        this.followed.add(followed);
        followed.setIdFollower(this);
    }
    
    public void removeFollowed(FollowerGroup followed){
        this.followed.remove(followed);
        followed.setIdFollower(null);
    }
    
    // ----------------- DTOS  
   
      public CreateUserDto toDTOCreate() {
        CreateUserDto createUserDTO = new CreateUserDto();
        createUserDTO.setName(this.name);
        createUserDTO.setEmail(this.email);
        createUserDTO.setPassword(this.password);
        createUserDTO.setUserName(this.userName);
        return createUserDTO;

    }
      
    public  static  Users fromDTOCreate( CreateUserDto createUserDTO){
        Users user = new Users();
        user.setName(createUserDTO.getName());
        user.setUserName(createUserDTO.getUserName());
        user.setEmail(createUserDTO.getEmail());
        user.setPassword(createUserDTO.getPassword());
        user.setActive(Boolean.TRUE);
        user.setCreationDate(new Date());
        
        return  user;
    }
    
    public UserDto toDTO() {
        UserDto usersDTO = new UserDto();
        usersDTO.setBiography("New Biography");
        usersDTO.setCreationDate(this.creationDate);
        usersDTO.setUpdatedAt(this.updatedAt);
        usersDTO.setName(this.name);
        usersDTO.setEmail(this.email);
        usersDTO.setPhoto(this.photo);
        usersDTO.setLastLogin(this.lastLogin);
        usersDTO.setActive(this.active); 
        return usersDTO;
                
        
    }
    
    
    
    
    
    
}
