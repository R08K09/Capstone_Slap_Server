package com.example.slap_server.controllers;
import com.example.slap_server.models.LoginDTO;
import com.example.slap_server.models.User;
import com.example.slap_server.models.UserDTO;
import com.example.slap_server.services.UserService;
import com.example.slap_server.models.Slap;
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


    // INDEX
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }


    // SHOW
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/following")
    public ResponseEntity<List<User>> getUserFollowing(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.getUserFollowing(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/followers")
    public ResponseEntity<List<User>> getUserFollowers(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.getUserFollowers(id), HttpStatus.OK);
    }


    // CREATE
    @PostMapping
    public ResponseEntity<User> createAuthenticatedUser(@RequestBody UserDTO userDTO) {
        User newUser = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody LoginDTO loginDTO) {
        User user = userService.checkLogin(loginDTO);
        if (user == null){
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    // UPDATE
    @PatchMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) {
        User updatedUser = userService.updateUser(userDTO, id);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PatchMapping("/{userId}/following/{followingId}")
    public ResponseEntity<User> updateUserAddFollowing(@PathVariable Long userId, @PathVariable Long followingId) {
        User updatedUser = userService.updateUserAddFollowing(userId, followingId);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PatchMapping("/{userId}/unfollowing/{unfollowingId}")
    public ResponseEntity<User> updateUserUnfollowing(@PathVariable Long userId, @PathVariable Long unfollowingId) {
        User updatedUser = userService.updateUserUnfollowing(userId, unfollowingId);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }


    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
