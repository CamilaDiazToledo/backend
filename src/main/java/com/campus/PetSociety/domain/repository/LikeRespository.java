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
public interface LikeRespository extends JpaRepository<Likes, Long> {

    List<Likes> findByIdPost_IdPost(Long IdPost);

    List<Likes> findByIdPost(Post post);

    List<Likes> findByIdComment(Comment comment);

    List<Likes> findByIdComment_IdComment(Long IdComment);

    Optional<Likes> findByIdPostAndIdUser(Post idPost, Users idUser);

    Optional<Likes> findByIdCommentAndIdUser(Comment idComment, Users idUser);

    @Query("SELECT l FROM Likes l "
            + "WHERE l.idPost.idPost = :postId "
            + "AND l.idUser.active = TRUE "
            + "ORDER BY l.reactionDate DESC")
    List<Likes> findLikesByPostId(@Param("postId") Long postId);

    @Query("SELECT l FROM Likes l "
            + "WHERE l.idComment.idComment = :commentId "
            + "AND l.idUser.active = TRUE "
            + "ORDER BY l.reactionDate DESC")
    List<Likes> findLikesByCommentId(@Param("commentId") Long commentId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Likes l WHERE l.idLike = :likeId")
    int deleteLikeById(Long likeId);

}
