package com.example.slap_server.controllers;
import com.example.slap_server.models.User;
import com.example.slap_server.models.UserDTO;
import com.example.slap_server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping
    public ResponseEntity<List<User>>displayAllUsers(){
        try {
            return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<User>createUser(@RequestBody User user){
        try {
            return new ResponseEntity<>(userService.createUser(user),HttpStatus.CREATED);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

//     INDEX:
    @GetMapping(value = "/{userId}")
    public ResponseEntity<User>displayUserById(@PathVariable Long userId){
        try{
            return new ResponseEntity<>(userService.getUserById(userId),HttpStatus.OK);
        } catch(NoSuchElementException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


// EDIT:
    @GetMapping(value = "/{userId}/edit")
    public ResponseEntity<User> editUser (@PathVariable Long userId){
        try{
            User foundUser = userService.getUserById(userId);
            return new ResponseEntity<>(foundUser, HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

// UPDATE:
    @PatchMapping(value = "/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody UserDTO userDTO){
        try {
            User updateUser = userService.updateUser(userDTO, userId);
            return new ResponseEntity<>(updateUser,HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> deleteUser (@PathVariable Long id){
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch  (NoSuchElementException e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
