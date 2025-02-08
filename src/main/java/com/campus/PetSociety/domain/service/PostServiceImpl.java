/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.campus.PetSociety.domain.service;

import com.campus.PetSociety.domain.repository.CommentRepository;
import com.campus.PetSociety.domain.repository.LikeRespository;
import com.campus.PetSociety.domain.repository.PostRepository;
import com.campus.PetSociety.domain.repository.UserRepository;
import com.campus.PetSociety.dto.CreatePostDto;
import com.campus.PetSociety.dto.PostDto;
import com.campus.PetSociety.persistence.entity.Post;
import com.campus.PetSociety.persistence.entity.*;
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
public class PostServiceImpl implements PostService {

    private final PostRepository postRepositorty;
    private final UserRepository userRepositorty;
    private final CommentRepository commentRepository;
    private final LikeRespository likeRepositorty;

    @Autowired
     public PostServiceImpl(PostRepository postRepositorty, UserRepository userRepositorty, CommentRepository commentRepository, LikeRespository likeRepositorty) {
        this.postRepositorty = postRepositorty;
        this.userRepositorty = userRepositorty;
        this.commentRepository = commentRepository;
        this.likeRepositorty = likeRepositorty;
    }   
//CREATE......................................................................

    @Transactional
    @Override
    public ResponseEntity<PostDto> createPost(CreatePostDto createPostDto) {
        // Buscar el usuario en la base de datos usando el ID del usuario
        Optional<Users> userOptional = userRepositorty.findByEmail(createPostDto.getEmailUser());

        if (!userOptional.isPresent()) {
            throw new NotFoundException("User not found with id: " + createPostDto.getEmailUser());
        }

        Users userEntity = userOptional.get();
        Post postcreated = Post.fromDTOCreate(createPostDto, userEntity);
        postcreated.setIdUser(userEntity); // Asignar el usuario encontrado al post
        userEntity.addPost(postcreated);
        postcreated = postRepositorty.save(postcreated);
        return ResponseEntity.ok(postcreated.toDTO());
    }

    

//GET.........................................................................
    // todos
    @Transactional
    @Override
    public List<PostDto> getAllPost() {
        List<Post> post = postRepositorty.findAll();
        if (post.isEmpty()) {
            throw new NotFoundException("No posts found");
        }
        return post.stream()
                .map(Post::toDTO)
                .collect(Collectors.toList());
    }

    //todos por idUser
    @Transactional
    @Override
    public List<PostDto> getAllPostByEmailUser(String userEmail) {
        List<Post> posts = postRepositorty.findByUserEmail(userEmail);
        return posts.stream()
                .map(Post::toDTO)
                .collect(Collectors.toList());
    }

    //todos los seguidos
    @Transactional
    @Override
    public List<PostDto> getPostsByFollowerEmail(String email) {
        List<Post> posts = postRepositorty.findPostsByFollowerEmail(email);
        return posts.stream()
                .map(Post::toDTO)
                .collect(Collectors.toList());
    }

//DELETE.........................................................................
//    @Transactional
//    @Override
//    public ResponseEntity<Void> deletePost(Long postId) {
//        System.out.println("Verificando si el post con ID: " + postId + " existe.");
//        if (postRepositorty.existsById(postId)) {
//            System.out.println("El post existe. Procediendo a eliminar.");
//            postRepositorty.deletePostById(postId);
//            System.out.println("Post eliminado.");
//            return ResponseEntity.noContent().build(); // 204 No Content
//        } else {
//            System.out.println("El post con ID: " + postId + " no existe.");
//            throw new NotFoundException("No post found");
//        }
//    }
    @Transactional
    @Override
    public ResponseEntity<Void> deletePost(Long postId) {
        System.out.println("Verificando si el post con ID: " + postId + " existe.");
        if (postRepositorty.existsById(postId)) {
            System.out.println("El post existe. Procediendo a eliminar dependencias.");
            
            Post post = postRepositorty.findById(postId).get();
            
            Optional<Users> user = userRepositorty.findByEmail(post.getIdUser().getEmail());
            
            // Eliminar comentarios asociados y sus likes
            List<Comment> comments = commentRepository.findByIdPost(post);
            for (Comment comment : comments) {
                // Eliminar likes de cada comentario
                List<Likes> commentLikes = likeRepositorty.findByIdComment(comment);
                for (Likes like : commentLikes) {
                comment.removeLikes(like);
                }
                
                likeRepositorty.deleteAll(commentLikes); 
                post.removeComment(comment);
            }
            // Eliminar comentario
            commentRepository.deleteAll(comments);
            // Eliminar likes asociados
            List<Likes> postLikes  = likeRepositorty.findByIdPost(post);
            for (Likes like : postLikes) {
                post.removeLikes(like);
                }
            likeRepositorty.deleteAll(postLikes);
            // Eliminar el post
            user.get().removePost(post);
            postRepositorty.delete(post);
            System.out.println("Post eliminado.");
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            System.out.println("El post con ID: " + postId + " no existe.");
            throw new NotFoundException("No post found");
        }
    }

//UPDATE.........................................................................
    @Transactional
    @Override
    public Boolean updateDescription(Long id, String description) {
        Optional<Post> postOpt = postRepositorty.findById(id);

        if (postOpt.isPresent()) {
            Post post = postOpt.get();
            post.setDiscription(description);
            post.setUpdateAt(new Date());

            postRepositorty.save(post);
            System.out.println("Description updated.");
            return true;
        } else {
            System.out.println("Post with this ID " + id + " does not exist.");
            return false;
        }
    }

}
