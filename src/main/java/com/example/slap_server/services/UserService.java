package com.example.slap_server.services;

import com.example.slap_server.models.Slap;
import com.example.slap_server.models.User;
import com.example.slap_server.models.UserDTO;
import com.example.slap_server.repositories.SlapRepository;
import com.example.slap_server.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SlapRepository slapRepository;

    @Autowired
    SlapService slapService;


    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUserName(String username, Long userId){
        User user = userRepository.findById(userId).get();
        user.setUsername(username);
        return userRepository.save(user);
    }

//    COME BACK TO THIS::
    public User updateUser(UserDTO userDTO, Long userId){
        User userToUpdate = userRepository.findById(userId).get();
        userToUpdate.setUsername(userDTO.getUsername());
        userToUpdate.setBio(userDTO.getBio());
        userToUpdate.setEmail(userDTO.getEmail());
        userRepository.save(userToUpdate);
        return userToUpdate;
    }

    public void deleteUser(Long id) {
        User userToDelete = userRepository.findById(id).get();
        ArrayList<Long> slapIdsToDelete = new ArrayList<>();
        for (Slap slap : userToDelete.getSlaps()) {
            slapIdsToDelete.add(slap.getId());
        }
        for (Long slapId : slapIdsToDelete) {
            slapService.deleteSlapById(slapId);
        }
//        for (Slap slap : userToDelete.getSlaps()){
//            userToDelete.removeSlap(slap);
////            slapService.deleteSlapById(slap.getId());
//            slapRepository.delete(slap);
//        }
        for (User userFollowing : userToDelete.getFollowing()){
            userToDelete.unfollow(userFollowing);
        }
        for (User userFollower : userToDelete.getFollowers()){
            userFollower.unfollow(userToDelete);
        }
        userRepository.deleteById(id);
    }








}
