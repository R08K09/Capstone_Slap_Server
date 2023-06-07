package com.example.slap_server.services;

import com.example.slap_server.models.User;
import com.example.slap_server.models.UserDTO;
import com.example.slap_server.repositories.FriendshipRepository;
import com.example.slap_server.repositories.SlapRepository;
import com.example.slap_server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendshipService {

    @Autowired
    FriendshipRepository friendshipRepository;

    @Autowired
    SlapRepository slapRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    User user;

//    public void saveFriendship(UserDTO userDto1, Long id) throws NullPointerException{
//
//        User user = userRepository.getOne(id);
//        UserDTO userDto2 = User.map(user, userDto.class);
//    }


}
