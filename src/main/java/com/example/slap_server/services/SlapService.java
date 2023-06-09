package com.example.slap_server.services;

import com.example.slap_server.models.Slap;
import com.example.slap_server.models.SlapDTO;
import com.example.slap_server.models.User;
import com.example.slap_server.repositories.SlapRepository;
import com.example.slap_server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlapService {

    @Autowired
    SlapRepository slapRepository;

    @Autowired
    UserService userService;

    public List<Slap> findAllSlaps(){
        return slapRepository.findAll();
    }

    public Slap findSlapById(Long id){
        return slapRepository.findById(id).get();
    }

    public List<Slap> findSlapByUser(Long user_id){
        return slapRepository.findSlapByUserId(user_id);
    }

    public void deleteSlapById(Long id){
        slapRepository.deleteById(id);
    }

    public void saveSlap(SlapDTO slapDTO) {
//        String messageSlap = slapDTO.getMessage(); // maybe do getMood() too
//        String moodSlap = slapDTO.getMood();
//        User userSlap = userRepository.findById(slapDTO.getUserId()).get();
//        Slap slap = slapRepository.findById(slapDTO.getUserId()).get();
//        Slap createdSlap = new Slap(moodSlap, messageSlap, userSlap);
        User user = userService.getUserById(slapDTO.getUserId());
        Slap slap = new Slap(slapDTO.getMood(), slapDTO.getMessage(), user);
        slapRepository.save(slap);
        userService.
    }
}

