package com.example.slap_server.controllers;

import com.example.slap_server.models.Slap;
import com.example.slap_server.models.SlapDTO;
import com.example.slap_server.repositories.SlapRepository;
import com.example.slap_server.services.SlapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/slaps")
public class SlapController {

    @Autowired
    SlapService slapService;

    @Autowired
    SlapRepository slapRepository;

    @GetMapping
    public ResponseEntity<List<Slap>> getAllSlaps(){
        try{return new ResponseEntity<>(slapService.findAllSlaps(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Slap> getSlapById(@PathVariable Long id){
        try { return new ResponseEntity<>(slapService.findSlapById(id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<List<Slap>> getSlapByUser(@PathVariable Long userId){
        try { return new ResponseEntity<>(slapService.findSlapByUser(userId), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> deleteSlap(@PathVariable Long id){
        try{ slapService.deleteSlapById(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/createSlap")
    public ResponseEntity<List<Slap>> createSlap(@RequestBody SlapDTO slapDTO) {
        try {
            slapService.saveSlap(slapDTO);
            return new ResponseEntity<>(slapService.findAllSlaps(), HttpStatus.CREATED);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}