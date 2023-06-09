package com.example.slap_server.services;

import com.example.slap_server.models.Slap;
import com.example.slap_server.models.SlapDTO;
import com.example.slap_server.models.User;
import com.example.slap_server.models.UserDTO;
import com.example.slap_server.repositories.SlapRepository;
import com.example.slap_server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SlapRepository slapRepository;

    public UserDTO convertToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setBio(user.getBio());
        userDTO.setEmail(user.getEmail());
        userDTO.setFollowerIds(user.getFollowers().stream().map(User::getId).collect(Collectors.toList()));
        userDTO.setFollowingIds(user.getFollowing().stream().map(User::getId).collect(Collectors.toList()));
        userDTO.setSlapIds(user.getSlaps().stream().map(Slap::getId).collect(Collectors.toList()));
        return userDTO;
    }



    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setBio(user.getBio());
        userDTO.setEmail(user.getEmail());
        return user;
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setBio(userDTO.getBio());
        user.setEmail(userDTO.getEmail());
        userRepository.save(user);

        UserDTO createdUserDTO = new UserDTO();
        createdUserDTO.setUsername(user.getUsername());
        createdUserDTO.setBio(user.getBio());
        createdUserDTO.setEmail(user.getEmail());
        return createdUserDTO;
    }

    public UserDTO updateUser(UserDTO userDTO, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found"));
        user.setUsername(userDTO.getUsername());
        user.setBio(userDTO.getBio());
        user.setEmail(userDTO.getEmail());
        userRepository.save(user);

        UserDTO updatedUserDTO = new UserDTO();
        updatedUserDTO.setUsername(user.getUsername());
        updatedUserDTO.setBio(user.getBio());
        updatedUserDTO.setEmail(user.getEmail());
        return updatedUserDTO;
    }

    public void deleteUser(Long id) {
        User userToDelete = userRepository.findById(id).get();
        ArrayList<Long> slapIdsToDelete = new ArrayList<>();
        for (Slap slap : userToDelete.getSlaps()) {
            slapIdsToDelete.add(slap.getId());
        }
        for (Long slapId : slapIdsToDelete) {
            slapRepository.deleteById(slapId);
        }
//        remove userToDelete follows
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
