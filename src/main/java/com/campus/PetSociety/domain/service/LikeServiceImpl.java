/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.campus.PetSociety.domain.service;

import com.campus.PetSociety.domain.repository.LikeRespository;
import com.campus.PetSociety.domain.repository.*;
import com.campus.PetSociety.dto.CreateLikeCommentDto;
import com.campus.PetSociety.dto.CreateLikePostDto;
import com.campus.PetSociety.dto.LikeCommentDto;
import com.campus.PetSociety.dto.LikePostDto;
import com.campus.PetSociety.persistence.entity.*;
import com.campus.PetSociety.web.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author camid
 */
@Service
public class LikeServiceImpl implements LikeService {

    private final LikeRespository likeRepositorty;
    private final UserRepository userRepositorty;
    private final PostRepository postRepositorty;
    private final CommentRepository commentRepository;
    private final NotifyServiceImpl notifyServiceImpl;
    @Autowired
    public LikeServiceImpl(LikeRespository likeRepositorty, UserRepository userRepositorty, PostRepository postRepositorty, CommentRepository commentRepository, NotifyServiceImpl notifyServiceImpl) {
        this.likeRepositorty = likeRepositorty;
        this.userRepositorty = userRepositorty;
        this.postRepositorty = postRepositorty;
        this.commentRepository = commentRepository;
        this.notifyServiceImpl = notifyServiceImpl;
    }
    
    

//CREATE......................................................................
    //like post
    @Transactional
    @Override
    public ResponseEntity<LikePostDto> createLikePost(CreateLikePostDto createLikePostDto) {
        System.out.println("Iniciando creación de like para el post con datos: " + createLikePostDto);

        Optional<Users> userOptional = userRepositorty.findByEmail(createLikePostDto.getEmailUser());
        if (!userOptional.isPresent()) {
            System.out.println("Usuario no encontrado con email: " + createLikePostDto.getEmailUser());
            throw new NotFoundException("User not found with email: " + createLikePostDto.getEmailUser());
        }
        System.out.println("Usuario encontrado: " + userOptional.get());

        Optional<Post> postOptional = postRepositorty.findById(createLikePostDto.getIdPost());
        if (!postOptional.isPresent()) {
            System.out.println("Post no encontrado con id: " + createLikePostDto.getIdPost());
            throw new NotFoundException("Post not found with id: " + createLikePostDto.getIdPost());
        }
        System.out.println("Post encontrado: " + postOptional.get());

        Users userEntity = userOptional.get();
        Post postEntity = postOptional.get();

        Likes likecreated = Likes.fromDTOCreateLikePost(createLikePostDto, userEntity, postEntity);
        likecreated.setIdUser(userEntity);
        likecreated.setIdPost(postEntity);

        userEntity.addLikes(likecreated);
        postEntity.addLikes(likecreated);

        likecreated = likeRepositorty.save(likecreated);
        System.out.println("Like creado: " + likecreated);
        notifyServiceImpl.createNotificationLike(likecreated,postEntity.getIdUser() );
        return ResponseEntity.ok(likecreated.toDTOLikePost());
    }

    

    //like comment
    @Transactional
    @Override
    public ResponseEntity<LikeCommentDto> createLikeComment(CreateLikeCommentDto createLikeCommentDto) {

        Optional<Users> userOptional = userRepositorty.findByEmail(createLikeCommentDto.getEmailUser());
        if (!userOptional.isPresent()) {
            throw new NotFoundException("User not found with email: " + createLikeCommentDto.getEmailUser());
        }

        Optional<Comment> commentOptional = commentRepository.findById(createLikeCommentDto.getIdComment());
        if (!commentOptional.isPresent()) {
            throw new NotFoundException("Comment not found with id: " + createLikeCommentDto.getIdComment());
        }

        Users userEntity = userOptional.get();
        Comment commentEntity = commentOptional.get();

        Likes likecreated = Likes.fromDTOCreateLikeComment(createLikeCommentDto, userEntity, commentEntity);
        likecreated.setIdUser(userEntity);
        likecreated.setIdComment(commentEntity);

        userEntity.addLikes(likecreated);
        commentEntity.addLikes(likecreated);

        likecreated = likeRepositorty.save(likecreated);
        notifyServiceImpl.createNotificationLike(likecreated,commentEntity.getIdUser());
        return ResponseEntity.ok(likecreated.toDTOLikeComment());

    }

//GET.........................................................................
    //todos por idPost
    @Transactional
    @Override
    public List<LikePostDto> getAllLikeByIdPost(Long postId) {
        List<Likes> likes = likeRepositorty.findByIdPost_IdPost(postId);
        return likes.stream()
                .map(Likes::toDTOLikePost)
                .collect(Collectors.toList());
    }

    //todos por idComment
    @Transactional
    @Override
    public List<LikeCommentDto> getAllLikeByIdComment(Long commentId) {
        List<Likes> likes = likeRepositorty.findByIdComment_IdComment(commentId);
        return likes.stream()
                .map(Likes::toDTOLikeComment)
                .collect(Collectors.toList());
    }

//DELETE.........................................................................
    //delete like post
    @Transactional
    @Override
    public ResponseEntity<Void> deleteLikePost(Long idLike) {
        System.out.println("Verificando si el like con ID: " + idLike + " existe.");
        if (likeRepositorty.existsById(idLike)) {
            System.out.println("El like existe. Procediendo a eliminar.");
            Likes like = likeRepositorty.findById(idLike).get();
            Post post = postRepositorty.findPostByLike(like).get();
            notifyServiceImpl.deleteNotifyLike(idLike);
            post.removeLikes(like);
            
            postRepositorty.save(post);
            
            likeRepositorty.deleteLikeById(like.getIdLike());

        } else {
            System.out.println("El like con ID: " + idLike + " no existe.");
            throw new NotFoundException("No like found");
        }
        return null;

    }

    //delete like comment
    @Transactional
    @Override
    public ResponseEntity<Void> deleteLikeComment(Long idLike) {
        System.out.println("Verificando si el like con ID: " + idLike + " existe.");
        if (likeRepositorty.existsById(idLike)) {
            System.out.println("El like existe. Procediendo a eliminar.");
            Likes like = likeRepositorty.findById(idLike).get();
            Comment comment = commentRepository.findCommentByLike(like).get();
            notifyServiceImpl.deleteNotifyLike(idLike);
            comment.removeLikes(like);
            commentRepository.save(comment);
            
            likeRepositorty.deleteLikeById(like.getIdLike());

        } else {
            System.out.println("El like con ID: " + idLike + " no existe.");
            throw new NotFoundException("No like found");
        }
        return null;

    }
    
    //FIND
//    @Transactional
//    @Override
//    public ResponseEntity<LikePostDto> findLikedPost(Long idPost, String email) {
//        Optional<Users> usersOpt = userRepositorty.findByEmail(email);
//        Optional<Post> postOpt = postRepositorty.findById(idPost);
//        Optional<Likes> like = likeRepositorty.findByIdPostAndIdUser(postOpt.get(), usersOpt.get());
//        if (like.isEmpty()) {
//            throw new NotFoundException("No like found with this Id" + email +" and id post" + idPost );
//        }
//        return like.map(likes -> ResponseEntity.ok(likes.toDTOLikePost()))
//                .orElse(ResponseEntity.notFound().build());
//    }
//    
//    @Transactional
//    @Override
//    public ResponseEntity<LikeCommentDto> findLikedComment(Long idComment, String email) {
//        Optional<Users> usersOpt = userRepositorty.findByEmail(email);
//        Optional<Comment> commentOpt = commentRepository.findById(idComment);
//        Optional<Likes> like = likeRepositorty.findByIdCommentAndIdUser(commentOpt.get(), usersOpt.get());
//        if (like.isEmpty()) {
//            throw new NotFoundException("No like found with this Id" + email+" and id comment" +idComment);
//        }
//        return like.map(likes -> ResponseEntity.ok(likes.toDTOLikeComment()))
//                .orElse(ResponseEntity.notFound().build());
//    }
    
    


}
