package com.example.slap_server.components;

import com.example.slap_server.models.Slap;
import com.example.slap_server.models.User;
import com.example.slap_server.repositories.SlapRepository;
import com.example.slap_server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    SlapRepository slapRepository;

    @Autowired
    UserRepository userRepository;


    public DataLoader(){

    }

    @Override
    public void run(ApplicationArguments args) throws Exception{

        User user1 = new User ("Natasha", "Hi", "SubrinaSmells@gmail.com");

        Slap slap1 = new Slap (":wave:","Hi everyone", user1);

    }





}
