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
public interface CommentRepository extends JpaRepository<Comment, Long>{
    List<Comment> findByIdPost_IdPost(Long IdPost);
    List<Comment> findByIdPost(Post post);
    @Modifying
    @Transactional
    @Query("DELETE FROM Comment c WHERE c.idComment = :commentId")
    void deleteCommentById(Long commentId);
    
    @Query("SELECT c FROM Comment c JOIN c.likes l WHERE l = :like")
    Optional<Comment> findCommentByLike(@Param("like") Likes like);
    
    
    
}
