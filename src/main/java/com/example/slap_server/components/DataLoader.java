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
        userRepository.save(user1);

        User user2 = new User("Anna", "hi", "anna@email");
        userRepository.save(user2);

        User user3 = new User("Rada", "hi", "rada@email");
        userRepository.save(user3);

        user1.addFriendship(user2);
        userRepository.save(user1);

        user3.addFriendship(user1);
        userRepository.save(user3);

    }





}
