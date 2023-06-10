package com.example.slap_server.services;

import com.example.slap_server.models.Slap;
import com.example.slap_server.models.SlapDTO;
import com.example.slap_server.models.User;
import com.example.slap_server.repositories.SlapRepository;
import com.example.slap_server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class SlapService {

    @Autowired
    private SlapRepository slapRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    public List<Slap> findAllSlaps() {
//        List<Slap> slaps = slapRepository.findAll(Sort.by(Sort.Direction.DESC, "dateTime"));

//        List<SlapDTO> slapDTOs = new ArrayList<>();

//        for (Slap slap : slaps) {
//            SlapDTO slapDTO = new SlapDTO();
//            slapDTO.setDateTime(slap.getDateTime());
//            slapDTO.setMood(slap.getMood());
//            slapDTO.setMessage(slap.getMessage());
//            slapDTO.setUserId(slap.getUser().getId());
//            slapDTOs.add(slapDTO);
//        }

        return slapRepository.findAll();
    }

    public Slap findSlapById(Long id) {
        Slap slap = slapRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Slap not found"));
//        SlapDTO slapDTO = new SlapDTO();
//        slapDTO.setDateTime(slap.getDateTime());
//        slapDTO.setMood(slap.getMood());
//        slapDTO.setMessage(slap.getMessage());
//        slapDTO.setUserId(slap.getUser().getId());
        return slap;
    }

    // converting each slap object to its corresponding 'SlapDTO' representation without setting the id explictily
    public SlapDTO convertToSlapDTO(Slap slap) {
        SlapDTO slapDTO = new SlapDTO();
        slapDTO.setDateTime(slap.getDateTime());
        slapDTO.setMood(slap.getMood());
        slapDTO.setMessage(slap.getMessage());
        slapDTO.setUserId(slap.getUser().getId());
        return slapDTO;
    }

    public List<SlapDTO> findSlapsByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + userId));
        List<Slap> slaps = slapRepository.findByUserOrderByDateTimeDesc(user);
        return slaps.stream().map(this::convertToSlapDTO).collect(Collectors.toList());
    }

    public void createSlap(SlapDTO slapDTO) {
//        User user = userRepository.findById(slapDTO.getUserId()).orElseThrow(() -> new NoSuchElementException("User not found"));

        Slap slap = new Slap(slapDTO.getMood(), slapDTO.getMessage());
        User user = userService.getUserById(slapDTO.getUserId());
        user.addSlap(slap);
        slapRepository.save(slap);
        userRepository.save(user);
    }

    public Slap updateSlap(SlapDTO slapDTO, Long slapId) {
//        Slap slap = slapRepository.findById(slapId).orElseThrow(() -> new NoSuchElementException("Slap not found"));
//        slap.setMood(slapDTO.getMood());
//        slap.setMessage(slapDTO.getMessage());
//        slapRepository.save(slap);
//
//        SlapDTO updatedSlapDTO = new SlapDTO();
//        updatedSlapDTO.setDateTime(slap.getDateTime());
//        updatedSlapDTO.setMood(slap.getMood());
//        updatedSlapDTO.setMessage(slap.getMessage());
//        updatedSlapDTO.setUserId(slap.getUser().getId());
//        return updatedSlapDTO;
        Slap slapToUpdate = slapRepository.findById(slapId).get();
        if(slapDTO.getMood() != null){
            slapToUpdate.setMood(slapDTO.getMood());
        }
        if(slapDTO.getMessage() != null){
            slapToUpdate.setMessage(slapDTO.getMessage());
        }
        if(slapDTO.getUserId() != null){
            User user = userService.getUserById(slapDTO.getUserId());
            slapToUpdate.setUser(user);
        }
        slapRepository.save(slapToUpdate);
        return slapToUpdate;
    }

    public void deleteSlap(Long slapId) {
        slapRepository.deleteById(slapId);
    }
}


