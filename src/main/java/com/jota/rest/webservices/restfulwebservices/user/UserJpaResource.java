package com.jota.rest.webservices.restfulwebservices.user;

import com.jota.rest.webservices.restfulwebservices.jpa.PostRepository;
import com.jota.rest.webservices.restfulwebservices.jpa.UserRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJpaResource {

    //private UserDaoService service;

    private final UserRepository repository;
    private final PostRepository postRepository;

    public UserJpaResource(UserDaoService service, UserRepository repository, PostRepository postRepository){
        //this.service= service;
        this.repository = repository;
        this.postRepository = postRepository;
    }

    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers(){
       return repository.findAll();
    }

    @GetMapping("/jpa/posts")
    public List<Post> retrieveAllPosts(){
        return postRepository.findAll();
    }



    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id){

        Optional<User> user = repository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException(String.format("Id:%s",id));
        }

        EntityModel<User> entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder link =WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));
        return  entityModel;
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user ){
        repository.save(user);
        URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}" ).buildAndExpand(user.getId()).toUri();
        return  ResponseEntity.created(location).build();

    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id){

       repository.deleteById(id);

    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrievePostsForUser(@PathVariable int id){

        Optional<User> user = repository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException(String.format("Id:%s",id));
        }

        return user.get().getPosts();

    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post ){

        Optional<User> user = repository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException(String.format("Id:%s",id));
        }
        post.setUser(user.get());

       Post savedPost= postRepository.save(post);
        URI location= ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}" )
                .buildAndExpand(savedPost.getId()).toUri();
        return  ResponseEntity.created(location).build();

    }

    @GetMapping("/jpa/users/{idUser}/posts/{idPost}")
    public EntityModel<Post> retrievePost(@PathVariable int idUser,@PathVariable int idPost){

        Optional<User> user = repository.findById(idUser);
        if (user.isEmpty()) {
            throw new UserNotFoundException(String.format("Id:%s",idUser));
        }
        Optional<Post> post = postRepository.findById(idPost);
        if (post.isEmpty()) {
            throw new UserNotFoundException(String.format("Id:%s",idPost));
        }

        EntityModel<Post> entityModel = EntityModel.of(post.get());
        WebMvcLinkBuilder link =WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllPosts());
        entityModel.add(link.withRel("all-posts"));
        return  entityModel;
    }
}
