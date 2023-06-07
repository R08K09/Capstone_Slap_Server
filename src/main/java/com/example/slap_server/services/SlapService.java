package com.example.slap_server.services;

import com.example.slap_server.models.Slap;
import com.example.slap_server.repositories.SlapRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SlapService {

    @Autowired
    SlapRepository slapRepository;

    public List<Slap> findAllSlaps(){
        return slapRepository.findAll();
    }

    public Slap findSlapById(Long id){
        return slapRepository.findById(id).get();
    }

    public List<Slap> findSlapByUser(Long user_id){
        return slapRepository.findSlapByUser(user_id);
    }

    public List<Slap> findSlapByTimeDesc(Long id){
        return slapRepository.findSlapByDateTimeDesc(id);
    }

    public void deleteSlapById(Long id){
        slapRepository.deleteById(id);
    }
}
