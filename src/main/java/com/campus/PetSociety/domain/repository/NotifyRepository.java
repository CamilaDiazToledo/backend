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
import org.springframework.data.repository.query.Param;

/**
 *
 * @author camid
 */
public interface NotifyRepository extends JpaRepository<Notify, Long> {

    @Query("SELECT n FROM Notify n JOIN n.idUser u WHERE u.email = :email")
    List<Notify> findByToUserEmail(@Param("email") String email);

    

    @Query("SELECT n FROM Notify n WHERE n.followerGroup.idFG = :idFG")
    List<Notify> findByFollowerGroupId(@Param("idFG") Long idFG);

    @Query("SELECT n FROM Notify n WHERE n.likes.idLike = :idLike")
    List<Notify> findByLikeId(@Param("idLike") Long idLike);
    
    @Query("SELECT n FROM Notify n WHERE n.comments.idComment = :idComment")
    List<Notify> findByCommentId(@Param("idComment") Long idComment);
}
