package com.example.slap_server.controllers;

import com.example.slap_server.models.Slap;
import com.example.slap_server.repositories.SlapRepository;
import com.example.slap_server.services.SlapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/slaps")
public class SlapController {

}