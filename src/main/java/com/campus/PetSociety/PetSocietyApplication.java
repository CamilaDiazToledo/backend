package com.campus.PetSociety;

import com.campus.PetSociety.domain.service.*;
import com.campus.PetSociety.dto.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.campus.PetSociety.persistence.entity.*;
import com.campus.PetSociety.web.exceptions.NotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.ResponseEntity;

@SpringBootApplication
public class PetSocietyApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(PetSocietyApplication.class, args);

        UserServiceImpl userService = context.getBean(UserServiceImpl.class);
        PostServiceImpl postService = context.getBean(PostServiceImpl.class);
        CommentServiceImpl commentService = context.getBean(CommentServiceImpl.class);
        LikeServiceImpl likeService = context.getBean(LikeServiceImpl.class); // Obtener el servicio de likes
        FollowerGroupServiceImpl followerGroupService = context.getBean(FollowerGroupServiceImpl.class);
        

        // Crear Usuario
        CreateUserDto userDto = new CreateUserDto("pancho", "pancho05", "pancho05@gmail.com", "12345");
        ResponseEntity<UserDto> userResponse = userService.createUser(userDto);
        UserDto createdUser = userResponse.getBody();
        
        
        CreateUserDto userDto2 = new CreateUserDto("lucho", "lucho09", "lucho09@gmail.com", "54321");
        ResponseEntity<UserDto> userResponse2 = userService.createUser(userDto2);
        UserDto createdUser2 = userResponse2.getBody();
        // Actualizar foto y biografía del usuario
        userService.updatePhoto(createdUser.getPhoto(), "fotico");
        userService.updateBiography(createdUser.getEmail(), "nueva biografia");

        // Crear Post relacionado al Usuario
        CreatePostDto postDto = new CreatePostDto();
        postDto.setEmailUser(createdUser.getEmail());
        postDto.setDiscription("Mi primer post");
        postDto.setPhoto("photo_url");

        ResponseEntity<PostDto> postResponse = postService.createPost(postDto);
        PostDto createdPost = postResponse.getBody();

        // Crear Comment relacionado al Post y Usuario
        CreateCommentDto commentDto = new CreateCommentDto();
        commentDto.setEmailUser(createdUser.getEmail());
        commentDto.setIdPost(createdPost.getPostId());
        commentDto.setContent("Mi primer comentario");

        ResponseEntity<CommentDto> commentResponse = commentService.createComment(commentDto);
        CommentDto createdComment = commentResponse.getBody();

        // Crear Like relacionado al Post y Usuario
        CreateLikePostDto likePostDto = new CreateLikePostDto();
        likePostDto.setEmailUser(createdUser.getEmail());
        likePostDto.setIdPost(createdPost.getPostId());

        ResponseEntity<LikePostDto> likePostResponse = likeService.createLikePost(likePostDto);
        LikePostDto createdLikePost = likePostResponse.getBody();

        // Ejecutar el método deleteLike
//        try {
//            System.out.println("Intentando eliminar el like con ID: " + createdLikePost.getIdLike());
//            likeService.deleteLike(createdLikePost.getIdLike());
//        } catch (NotFoundException e) {
//            System.out.println(e.getMessage());
//        }

        // Imprimir detalles para verificar
//        System.out.println("Usuario creado: " + createdUser);
//        System.out.println("Post creado: " + createdPost);
//        System.out.println("Comentario creado: " + createdComment);
//        System.out.println("Like creado: " + createdLikePost);


        // Crear FollowerGroup
        CreateFollowerGroupDto createFollowerGroupDto = new CreateFollowerGroupDto();
        ResponseEntity<FollowerGroupDto> followerGroupResponse = followerGroupService.followUser(createdUser.getEmail(), createdUser2.getEmail());
        System.out.println("FollowerGroup creado: " + followerGroupResponse);

        // Obtener Followeds
        List<UserDto> followeds = followerGroupService.getFolloweds(createdUser.getEmail());
        System.out.println("Followeds: " + followeds);

        // Obtener Followers
        List<UserDto> followers = followerGroupService.getFollowers(createdUser2.getEmail());
        System.out.println("Followers: " + followers);
        
        
        
        
        
       

    }

}
