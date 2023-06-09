package com.example.slap_server.services;

import com.example.slap_server.models.Slap;
import com.example.slap_server.models.SlapDTO;
import com.example.slap_server.models.User;
import com.example.slap_server.repositories.SlapRepository;
import com.example.slap_server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<SlapDTO> findAllSlaps() {
        List<Slap> slaps = slapRepository.findAll();
        List<SlapDTO> slapDTOs = new ArrayList<>();

        for (Slap slap : slaps) {
            SlapDTO slapDTO = new SlapDTO();
            slapDTO.setDateTime(slap.getDateTime());
            slapDTO.setMood(slap.getMood());
            slapDTO.setMessage(slap.getMessage());
            slapDTO.setUserId(slap.getUser().getId());
            slapDTOs.add(slapDTO);
        }

        return slapDTOs;
    }

    public SlapDTO findSlapById(Long id) {
        Slap slap = slapRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Slap not found"));
        SlapDTO slapDTO = new SlapDTO();
        slapDTO.setDateTime(slap.getDateTime());
        slapDTO.setMood(slap.getMood());
        slapDTO.setMessage(slap.getMessage());
        slapDTO.setUserId(slap.getUser().getId());
        return slapDTO;
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
        List<Slap> slaps = slapRepository.findByUser(user);
        return slaps.stream().map(this::convertToSlapDTO).collect(Collectors.toList());
    }

    public SlapDTO createSlap(SlapDTO slapDTO) {
        User user = userRepository.findById(slapDTO.getUserId()).orElseThrow(() -> new NoSuchElementException("User not found"));

        Slap slap = new Slap();
        slap.setDateTime(LocalDateTime.now());
        slap.setMood(slapDTO.getMood());
        slap.setMessage(slapDTO.getMessage());
        slap.setUser(user);
        slapRepository.save(slap);

        SlapDTO createdSlapDTO = new SlapDTO();
        createdSlapDTO.setDateTime(slap.getDateTime());
        createdSlapDTO.setMood(slap.getMood());
        createdSlapDTO.setMessage(slap.getMessage());
        createdSlapDTO.setUserId(slap.getUser().getId());
        return createdSlapDTO;
    }

    public SlapDTO updateSlap(SlapDTO slapDTO, Long slapId) {
        Slap slap = slapRepository.findById(slapId).orElseThrow(() -> new NoSuchElementException("Slap not found"));
        slap.setMood(slapDTO.getMood());
        slap.setMessage(slapDTO.getMessage());
        slapRepository.save(slap);

        SlapDTO updatedSlapDTO = new SlapDTO();
        updatedSlapDTO.setDateTime(slap.getDateTime());
        updatedSlapDTO.setMood(slap.getMood());
        updatedSlapDTO.setMessage(slap.getMessage());
        updatedSlapDTO.setUserId(slap.getUser().getId());
        return updatedSlapDTO;
    }

    public void deleteSlap(Long slapId) {
        slapRepository.deleteById(slapId);
    }
}
