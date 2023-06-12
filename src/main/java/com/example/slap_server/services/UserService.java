package com.example.slap_server.services;

import com.example.slap_server.models.Slap;
import com.example.slap_server.models.User;
import com.example.slap_server.models.UserDTO;
import com.example.slap_server.repositories.SlapRepository;
import com.example.slap_server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SlapRepository slapRepository;


    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long id){
        return userRepository.findById(id).get();
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
        User newUser = new User(userDTO.getUsername(), userDTO.getBio(), userDTO.getEmail());
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
}
