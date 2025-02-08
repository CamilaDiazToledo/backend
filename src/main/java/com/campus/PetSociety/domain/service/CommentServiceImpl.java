/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.campus.PetSociety.domain.service;

import com.campus.PetSociety.domain.repository.CommentRepository;
import com.campus.PetSociety.domain.repository.LikeRespository;
import com.campus.PetSociety.domain.repository.PostRepository;
import com.campus.PetSociety.domain.repository.UserRepository;
import com.campus.PetSociety.dto.CommentDto;
import com.campus.PetSociety.dto.CreateCommentDto;
import com.campus.PetSociety.persistence.entity.Comment;
import com.campus.PetSociety.persistence.entity.Likes;
import com.campus.PetSociety.persistence.entity.Post;
import com.campus.PetSociety.persistence.entity.Users;
import com.campus.PetSociety.web.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import java.util.Date;
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
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final LikeRespository likeRepositorty;
    private final UserRepository userRepositorty;
    private final PostRepository postRepositorty;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, LikeRespository likeRepositorty, UserRepository userRepositorty, PostRepository postRepositorty) {
        this.commentRepository = commentRepository;
        this.likeRepositorty = likeRepositorty;
        this.userRepositorty = userRepositorty;
        this.postRepositorty = postRepositorty;
    }
    
    

    //CREATE......................................................................
    @Transactional
    @Override
    public ResponseEntity<CommentDto> createComment(CreateCommentDto createCommentDto) {

        Optional<Users> userOptional = userRepositorty.findByEmail(createCommentDto.getEmailUser());
        if (!userOptional.isPresent()) {
            throw new NotFoundException("User not found with id: " + createCommentDto.getEmailUser());
        }

        Optional<Post> postOptional = postRepositorty.findById(createCommentDto.getIdPost());
        if (!postOptional.isPresent()) {
            throw new NotFoundException("Post not found with id: " + createCommentDto.getIdPost());
        }

        Users userEntity = userOptional.get();
        Post postEntity = postOptional.get();

        Comment comentcreated = Comment.fromDTOCreate(createCommentDto, userEntity, postEntity);
        comentcreated.setIdUser(userEntity);
        comentcreated.setIdPost(postEntity);

        userEntity.addComment(comentcreated);
        postEntity.addComment(comentcreated);

        comentcreated = commentRepository.save(comentcreated);

        return ResponseEntity.ok(comentcreated.toDTO());

    }

//GET.........................................................................
    //todos por idPost
    @Transactional
    @Override
    public List<CommentDto> getAllCommentByIdPost(Long postId) {
        List<Comment> comments = commentRepository.findByIdPost_IdPost(postId);
        return comments.stream()
                .map(Comment::toDTO)
                .collect(Collectors.toList());
    }

//DELETE.........................................................................
//    @Transactional
//    @Override
//    public ResponseEntity<Void> deleteComment(Long commenttId) {
//        System.out.println("Verificando si el comment con ID: " + commenttId + " existe.");
//        if (commentRepository.existsById(commenttId)) {
//            System.out.println("El comment existe. Procediendo a eliminar.");
//            commentRepository.deleteCommentById(commenttId);
//            System.out.println("Comment eliminado.");
//            return ResponseEntity.noContent().build(); // 204 No Content
//        } else {
//            System.out.println("El comment con ID: " + commenttId + " no existe.");
//            throw new NotFoundException("No post found");
//        }
//    
//    }
    
    
    @Transactional
    @Override
    public ResponseEntity<Void> deleteComment(Long commenttId) {
        System.out.println("Verificando si el comment con ID: " + commenttId + " existe.");
        if (commentRepository.existsById(commenttId)) {
            System.out.println("El comment existe. Procediendo a eliminar dependencias.");
            
            Comment comment = commentRepository.findById(commenttId).get();
            
            Optional<Post> post = postRepositorty.findById(comment.getIdPost().getIdPost());
            
           
            List<Likes> commentLikes  = likeRepositorty.findByIdComment(comment);
            for (Likes like : commentLikes) {
                comment.removeLikes(like);
                }
            likeRepositorty.deleteAll(commentLikes);
            // Eliminar el post
            post.get().removeComment(comment);
            commentRepository.delete(comment);
            System.out.println("Comment eliminado.");
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            System.out.println("El comment con ID: " + commenttId + " no existe.");
            throw new NotFoundException("No post found");
        }
    }
    
//UPDATE.........................................................................

    
    @Transactional
    @Override
    public Boolean updateContent(Long id, String content) {
        Optional<Comment> commentOpt = commentRepository.findById(id);

        if (commentOpt.isPresent()) {
            Comment comment = commentOpt.get();
            comment.setContent(content);
            comment.setUpdateAt(new Date());

            commentRepository.save(comment);
            System.out.println("Content updated.");
            return true;
        } else {
            System.out.println("Comment with this ID " + id + " does not exist.");
            return false;   
        }

    }

}
