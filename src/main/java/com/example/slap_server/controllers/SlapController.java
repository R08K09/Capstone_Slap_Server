package com.example.slap_server.controllers;
import com.example.slap_server.models.Slap;
import com.example.slap_server.models.SlapDTO;
import com.example.slap_server.services.SlapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/slaps")
public class SlapController {

    @Autowired
    SlapService slapService;


    // INDEX
    @GetMapping
    public ResponseEntity<List<Slap>> getAllSlapsAndFilters(@RequestParam(required=false, name="userId") Long userId){
        if (userId != null){
            return new ResponseEntity<>(slapService.findSlapsByUserId(userId), HttpStatus.OK);
        }
        return new ResponseEntity<>(slapService.findAllSlaps(), HttpStatus.OK);
    }


    // SHOW
    @GetMapping("/{id}")
    public ResponseEntity<Slap> getSlapById(@PathVariable Long id) {
        return new ResponseEntity<>(slapService.findSlapById(id), HttpStatus.OK);
    }

//    @GetMapping(value = "/fromUser/{userId}")
//    public ResponseEntity<List<SlapDTO>> getSlapByUser(@PathVariable Long userId) {
//        try {
//            List<SlapDTO> slapDTOs = slapService.findSlapsByUserId(userId);
//            return new ResponseEntity<>(slapDTOs, HttpStatus.OK);
//        } catch (NoSuchElementException e) {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//    }


    // CREATE
    @PostMapping
    public ResponseEntity<SlapDTO> createSlap(@RequestBody SlapDTO slapDTO) {
        slapService.createSlap(slapDTO);
        return new ResponseEntity<>(slapDTO, HttpStatus.CREATED);
    }


    // UPDATE
    @PatchMapping("/{id}")
    public ResponseEntity<Slap> updateSlap(@RequestBody SlapDTO slapDTO, @PathVariable Long id) {
        Slap slap = slapService.updateSlap(slapDTO, id);
        return new ResponseEntity<>(slap, HttpStatus.OK);
    }


    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteSlap(@PathVariable Long id) {
        slapService.deleteSlap(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}