package com.example.slap_server.services;

import com.example.slap_server.models.Slap;
import com.example.slap_server.models.SlapDTO;
import com.example.slap_server.models.User;
import com.example.slap_server.repositories.SlapRepository;
import com.example.slap_server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class SlapService {

    @Autowired
    SlapRepository slapRepository;

//    @Autowired
//    UserRepository userRepository;

    @Autowired
    UserService userService;

    public List<Slap> findAllSlaps() {
        return slapRepository.findAll();
    }

    public Slap findSlapById(Long id) {
        return slapRepository.findById(id).get();
    }

    public List<Slap> findSlapsByUserId(Long userId) {
        List<Slap> allSlaps = slapRepository.findAll();
        List<Slap> filteredSlaps = new ArrayList<>();
        for (Slap slaps : allSlaps){
            if (slaps.getUser().getId() == userId){
                filteredSlaps.add(slaps);
            }
        }
        return filteredSlaps;
    }

    public void createSlap(SlapDTO slapDTO) {
        Slap slap = new Slap(slapDTO.getMood(), slapDTO.getMessage());
        User user = userService.getUserById(slapDTO.getUserId());
        slap.setUser(user);
        slapRepository.save(slap);
    }

    public Slap updateSlap(SlapDTO slapDTO, Long slapId) {
        Slap slapToUpdate = slapRepository.findById(slapId).get();
        if(slapDTO.getMood() != null){
            slapToUpdate.setMood(slapDTO.getMood());
        }
        if(slapDTO.getMessage() != null){
            slapToUpdate.setMessage(slapDTO.getMessage());
        }
//        if(slapDTO.getUserId() != null){
//            User user = userService.getUserById(slapDTO.getUserId());
//            slapToUpdate.setUser(user);
//        }
        slapRepository.save(slapToUpdate);
        return slapToUpdate;
    }

    public void deleteSlap(Long slapId) {
        slapRepository.deleteById(slapId);
    }
}


