package com.example.slap_server.services;

import com.example.slap_server.models.LoginDTO;
import com.example.slap_server.models.Slap;
import com.example.slap_server.models.User;
import com.example.slap_server.models.UserDTO;
import com.example.slap_server.repositories.SlapRepository;
import com.example.slap_server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SlapRepository slapRepository;


    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public List<User> getUserFollowing(Long id){
        User user = userRepository.findById(id).get();
        return user.getFollowing();
    }

    public List<User> getUserFollowers(Long id){
        User user = userRepository.findById(id).get();
        return user.getFollowers();
    }

    public User createUser(UserDTO userDTO) {

        // Create the new user
        User newUser = new User(userDTO.getUsername(), userDTO.getBio(), userDTO.getEmail());
        newUser.setPassword(userDTO.getPassword());
        if(userDTO.getFollowingIds() != null){
            for(Long followingId : userDTO.getFollowingIds()){
                User user = userRepository.findById(followingId).get();
                newUser.addUserToFollow(user);
            }
        }
        if(userDTO.getSlapIds() != null){
            for(Long slapId : userDTO.getSlapIds()){
                Slap slap = slapRepository.findById(slapId).get();
                newUser.addSlap(slap);
            }
        }

        return userRepository.save(newUser);
    }

    public User updateUser(UserDTO userDTO, Long userId) {
        User userToUpdate = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found"));
        if(userDTO.getUsername() != null){
            userToUpdate.setUsername(userDTO.getUsername());
        }
        if(userDTO.getBio() != null){
            userToUpdate.setBio(userDTO.getBio());
        }
        if(userDTO.getEmail() != null){
            userToUpdate.setEmail(userDTO.getEmail());
        }
        if(userDTO.getSlapIds() != null){
            userToUpdate.setSlaps(new ArrayList<Slap>());
            for(long slapIds : userDTO.getSlapIds()){
                Slap slap = slapRepository.findById(slapIds).get();
                userToUpdate.addSlap(slap);
            }
        }
        userRepository.save(userToUpdate);
        return userToUpdate;
    }

    public User updateUserAddFollowing(Long userId, Long followingId){
        User userToUpdate = userRepository.findById(userId).get();
        User following = userRepository.findById(followingId).get();
        if(!userToUpdate.getFollowing().contains(following)) {
            userToUpdate.addUserToFollow(following);
        }
        userRepository.save(userToUpdate);
        return userToUpdate;
    }

    public User updateUserUnfollowing(Long userId, Long unfollowingId){
        User userToUpdate = userRepository.findById(userId).get();
        User unfollowing = userRepository.findById(unfollowingId).get();
        if(userToUpdate.getFollowing().contains(unfollowing)) {
            userToUpdate.unfollow(unfollowing);
        }
        userRepository.save(userToUpdate);
        return userToUpdate;
    }

    public void deleteUser(Long id) {
        User userToDelete = userRepository.findById(id).get();
        ArrayList<Long> slapIdsToDelete = new ArrayList<>();
//        get all ids of userToDelete slaps, then use list of ids to delete from slap repo
        for (Slap slap : userToDelete.getSlaps()) {
            slapIdsToDelete.add(slap.getId());
        }
        for (Long slapId : slapIdsToDelete) {
            slapRepository.deleteById(slapId);
        }
//        unfollow all users that userToDelete currently follows
        for(int i = 0; i < userToDelete.getFollowing().size(); i++){
            userToDelete.unfollow(userToDelete.getFollowing().get(i));
        }
//        Remove userToDelete from other users following list
        for (User userFollower : userToDelete.getFollowers()){
            userFollower.unfollow(userToDelete);
        }
        userRepository.deleteById(id);
    }

    public User checkLogin(LoginDTO loginDTO) {
       User user = userRepository.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());
       return user;
    }


    // find a user where name is x and password is y
    //

}


