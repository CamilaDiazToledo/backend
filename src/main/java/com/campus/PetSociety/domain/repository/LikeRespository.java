/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.campus.PetSociety.domain.repository;

import com.campus.PetSociety.persistence.entity.*;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author camid
 */
public interface LikeRespository extends JpaRepository<Likes, Long> {
    List<Likes> findByIdPost_IdPost(Long IdPost);
    List<Likes> findByIdComment_IdComment(Long IdComment);
    

    
    @Modifying
    @Transactional
    @Query("DELETE FROM Likes l WHERE l.idLike = :likeId")
    void deleteLikeById(Long likeId);
}
