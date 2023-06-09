package com.example.slap_server.controllers;
import com.example.slap_server.models.Slap;
import com.example.slap_server.models.SlapDTO;
import com.example.slap_server.models.User;
import com.example.slap_server.models.UserDTO;
import com.example.slap_server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping
    public ResponseEntity<List<User>>displayAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User>createUser(@RequestBody User user){
        return new ResponseEntity<>(userService.createUser(user),HttpStatus.CREATED);
    }

//     INDEX:
    @GetMapping(value = "/{userId}")
    public ResponseEntity<User>displayUserById(@PathVariable Long userId){
        return new ResponseEntity<>(userService.getUserById(userId),HttpStatus.OK);
    }


// EDIT:
    @GetMapping(value = "/{userId}/edit")
    public ResponseEntity<User> editUser (@PathVariable Long userId){
        User foundUser = userService.getUserById(userId);
        return new ResponseEntity<>(foundUser, HttpStatus.OK);
    }

// UPDATE:
    @PatchMapping(value = "/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody UserDTO userDTO){
        User updateUser = userService.updateUser(userDTO, userId);
        return new ResponseEntity<>(updateUser,HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> deleteUser (@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

}
