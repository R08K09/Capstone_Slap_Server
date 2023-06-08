package com.example.slap_server.services;

import com.example.slap_server.models.User;
import com.example.slap_server.models.UserDTO;
import com.example.slap_server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

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

    public User updateUser(UserDTO userDTO, Long userId){
        User userToUpdate = userRepository.findById(userId).get();
        userToUpdate.setUsername(userDTO.getUsername());
        userRepository.save(userToUpdate);
        return userToUpdate;
    }








}
