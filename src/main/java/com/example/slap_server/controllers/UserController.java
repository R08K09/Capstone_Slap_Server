package com.example.slap_server.controllers;
import com.example.slap_server.models.User;
import com.example.slap_server.models.UserDTO;
import com.example.slap_server.services.UserService;
import com.example.slap_server.models.Slap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            List<UserDTO> userDTOs = new ArrayList<>();

            for (User user : users) {
                UserDTO userDTO = userService.convertToUserDTO(user);
                userDTO.setFollowerIds(user.getFollowers().stream().map(User::getId).collect(Collectors.toList()));
                userDTO.setFollowingIds(user.getFollowing().stream().map(User::getId).collect(Collectors.toList()));
                userDTO.setSlapIds(user.getSlaps().stream().map(Slap::getId).collect(Collectors.toList()));
                userDTOs.add(userDTO);
            }

            return new ResponseEntity<>(userDTOs, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) {
        try {
            User user = userService.getUserById(id);
            UserDTO userDTO = userService.convertToUserDTO(user);
            userDTO.setFollowerIds(user.getFollowers().stream().map(User::getId).collect(Collectors.toList()));
            userDTO.setFollowingIds(user.getFollowing().stream().map(User::getId).collect(Collectors.toList()));
            userDTO.setSlapIds(user.getSlaps().stream().map(Slap::getId).collect(Collectors.toList()));
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        try {
            UserDTO createdUser = userService.createUser(userDTO);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) {
        try {
            UserDTO updatedUser = userService.updateUser(userDTO, id);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

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