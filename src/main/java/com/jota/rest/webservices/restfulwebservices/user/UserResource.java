package com.jota.rest.webservices.restfulwebservices.user;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    private UserDaoService service;

    public UserResource(UserDaoService service){
        this.service= service;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
       return service.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id){

        User user = service.findOne(id);
        if (user==null) {
            throw new UserNotFoundException(String.format("Id:%s",id));
        }
        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user ){
         service.save(user);
        URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}" ).buildAndExpand(user.getId()).toUri();
        return  ResponseEntity.created(location).build();

    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){

       service.deleteById(id);

    }
}
