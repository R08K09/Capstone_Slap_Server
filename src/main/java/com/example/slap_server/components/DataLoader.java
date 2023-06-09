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

//        Creating users

        User user1 = new User ("natasha_xo", "Someone slap me!", "natasha@bnta.com");
        userRepository.save(user1);
        User user2 = new User("Zaynah99", "Slap me and i'll slap you back", "zaynah@bnta.com");
        userRepository.save(user2);
        User user3 = new User("Radaaa", "Hi guys!!", "rada@bnta.com");
        userRepository.save(user3);
        User user4 = new User("Anna", "Based in Scotland, slap me if you're local!", "anna@bnta.com");
        userRepository.save(user4);

//        Establishing relationships

        user1.addUserToFollow(user2);
        user1.addUserToFollow(user3);
        user1.addUserToFollow(user4);
        userRepository.save(user1);

        user2.addUserToFollow(user4);
        user2.addUserToFollow(user3);
        userRepository.save(user2);

        user3.addUserToFollow(user1);
        user3.addUserToFollow(user2);
        userRepository.save(user3);

        user4.addUserToFollow(user2);
        user4.addUserToFollow(user1);
        userRepository.save(user4);



//        Creating slaps

        Slap slap1 = new Slap (":wave:", "Hey, I'm new to Slap. Follow me!!", user1);
        user1.addSlap(slap1);
        slapRepository.save(slap1);

        Slap slap2 = new Slap (":wave:", "It's just hayfever", user2);
        Slap slap3 = new Slap ("U+1F600", "Nvm, false alarm!", user2);
        user2.addSlap(slap2);
        slapRepository.save(slap2);

        user2.addSlap(slap3);
        slapRepository.save(slap3);

        Slap slap4 = new Slap("U+1F600", "My mind's gone blank", user3);
        user3.addSlap(slap4);
        slapRepository.save(slap4);

    }

}
