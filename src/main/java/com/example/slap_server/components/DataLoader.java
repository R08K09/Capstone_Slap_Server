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

        User user1 = new User ("tasha", "Someone slap me!", "natasha@bnta.com");

        User user2 = new User("Zaynah99", "Slap me and i'll slap you back", "zaynah@bnta.com");
        userRepository.save(user2);

        User user3 = new User("Radaaa", "Hi guys!!", "rada@bnta.com");
        userRepository.save(user3);

        user1.addUserToFollow(user2);
        userRepository.save(user1);

        user3.addUserToFollow(user1);
        userRepository.save(user3);


        Slap slap1 = new Slap (":wave:", "Hey, I'm new to Slap. Follow me!!", user1);
        user1.addSlap(slap1);
        slapRepository.save(slap1);
        userRepository.save(user1);

    }





}
