package com.example.slap_server.controllers;
import com.example.slap_server.models.SlapDTO;
import com.example.slap_server.services.SlapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/slaps")
public class SlapController {
    @Autowired
    private SlapService slapService;

    @GetMapping
    public ResponseEntity<List<SlapDTO>> getAllSlaps() {
        List<SlapDTO> slaps = slapService.findAllSlaps();
        return new ResponseEntity<>(slaps, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SlapDTO> getSlapById(@PathVariable Long id) {
        SlapDTO slap = slapService.findSlapById(id);
        return new ResponseEntity<>(slap, HttpStatus.OK);
    }

//    @GetMapping("/user/{userId}")
//    public ResponseEntity<List<SlapDTO>> getSlapsByUserId(@PathVariable Long userId) {
//        List<SlapDTO> slaps = slapService.findSlapsByUserId(userId);
//        return new ResponseEntity<>(slaps, HttpStatus.OK);
//    }

    @GetMapping(value = "/fromUser/{userId}")
    public ResponseEntity<List<SlapDTO>> getSlapByUser(@PathVariable Long userId) {
        try {
            List<SlapDTO> slapDTOs = slapService.findSlapsByUserId(userId);
            return new ResponseEntity<>(slapDTOs, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<SlapDTO> createSlap(@RequestBody SlapDTO slapDTO) {
        SlapDTO createdSlap = slapService.createSlap(slapDTO);
        return new ResponseEntity<>(createdSlap, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SlapDTO> updateSlap(@PathVariable Long id, @RequestBody SlapDTO slapDTO) {
        SlapDTO updatedSlap = slapService.updateSlap(slapDTO, id);
        return new ResponseEntity<>(updatedSlap, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSlap(@PathVariable Long id) {
        slapService.deleteSlap(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}