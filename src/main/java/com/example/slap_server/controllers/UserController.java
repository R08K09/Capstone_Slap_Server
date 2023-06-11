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

//    @PatchMapping(value = "/{userId}/follow")
//    public ResponseEntity<List<User>> followUser(@PathVariable Long userId) {
//        return new ResponseEntity<>(userService.followUser(userId, followingId), HttpStatus.OK);
//    }

    @PostMapping("/{followerId}/follow/{followingId}")
    public ResponseEntity<String> followUser(@PathVariable("followerId") Long followerId, @PathVariable("followingId") Long followingId) {
        try {
            User follower = userService.getUserById(followerId);
            User following = userService.getUserById(followingId);

            if (follower.getFollowing().contains(following) && following.getFollowers().contains(follower)) {
                return new ResponseEntity<>("User with ID " + followerId + " is already following user with ID " + followingId, HttpStatus.BAD_REQUEST);
            }

            follower.getFollowing().add(following);
            following.getFollowers().add(follower);

            userService.updateUserToFollow(followerId, follower);
            userService.updateUserToFollow(followingId, following);

            return new ResponseEntity<>("User with ID " + followerId + " is now following user with ID " + followingId, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{followerId}/unfollow/{followingId}")
    public ResponseEntity<String> unfollowUser(@PathVariable("followerId") Long followerId, @PathVariable("followingId") Long followingId) {
        try {
            User follower = userService.getUserById(followerId);
            User following = userService.getUserById(followingId);

            if (!follower.getFollowing().contains(following) || !following.getFollowers().contains(follower)) {
                return new ResponseEntity<>("User with ID " + followerId + " is not following user with ID " + followingId, HttpStatus.BAD_REQUEST);
            }

            follower.getFollowing().remove(following);
            following.getFollowers().remove(follower);

            userService.updateUserToUnfollow(followerId, follower);
            userService.updateUserToUnfollow(followingId, following);

            return new ResponseEntity<>("User with ID " + followerId + " has unfollowed user with ID " + followingId, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{userId}/followers")
    public ResponseEntity<List<UserDTO>> getFollowers(@PathVariable("userId") Long userId) {
        try {
            List<User> followers = userService.getFollowers(userId);
            List<UserDTO> followerDTOs = followers.stream().map(user -> userService.convertToUserDTO(user)).collect(Collectors.toList());
            return new ResponseEntity<>(followerDTOs, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{userId}/following")
    public ResponseEntity<List<UserDTO>> getFollowing(@PathVariable("userId") Long userId) {
        try {
            List<User> following = userService.getFollowing(userId);
            List<UserDTO> followingDTOs = following.stream().map(user -> userService.convertToUserDTO(user)).collect(Collectors.toList());
            return new ResponseEntity<>(followingDTOs, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}