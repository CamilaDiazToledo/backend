/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.campus.PetSociety.domain.repository;

import com.campus.PetSociety.persistence.entity.Likes;
import com.campus.PetSociety.persistence.entity.Post;
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
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p JOIN p.idUser u WHERE u.email = :email ORDER BY p.createdAt DESC")
    List<Post> findByUserEmail(String email);
    

    @Modifying
    @Transactional
    @Query("DELETE FROM Post p WHERE p.idPost = :postId")
    void deletePostById(Long postId);
    
    @Query("SELECT p FROM Post p JOIN p.likes l WHERE l = :like")
    Optional<Post> findPostByLike(@Param("like") Likes like);

    @Query("SELECT p FROM Post p "
            + "JOIN p.idUser u "
            + "JOIN FollowerGroup fg ON fg.idFollowed = u "
            + "WHERE fg.idFollower.email = :email "
            + "AND u.active = TRUE "
            + "ORDER BY p.createdAt DESC")
    List<Post> findPostsByFollowerEmail(@Param("email") String email);

    
    
    
}
