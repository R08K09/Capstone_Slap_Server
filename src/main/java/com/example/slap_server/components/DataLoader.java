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
    public void run(ApplicationArguments args) throws Exception {

//        Creating users

        User user1 = new User("KimKardasshhh_xo", "If I was funny, I probably would have a better Slap bio.", "kimmyk@bnta.com");
        user1.setPassword("password7");
        userRepository.save(user1);
        User user2 = new User("Zayn_Malik", "I’ll get over it, I just need to Slap about it first!", "zayn@bnta.com");
        userRepository.save(user2);
        User user3 = new User("TheRealTate", "I don't have hair or brain cells", "tate@bnta.com");
        userRepository.save(user3);
        User user4 = new User("Anna", "Life is short. Make each second count. And enjoy iced lattes while you’re at it", "anna@bnta.com");
        userRepository.save(user4);
        User user5 = new User("ZsoltTheCoder", "Oh boy!! That looks JANKY", "zsolt@bnta.com");
        userRepository.save(user5);
        User user6 = new User("Tori_Vega", "You don't have to be afraid to put your dreams in action", "tori@bnta.com");
        userRepository.save(user6);
        User user7 = new User("Cat.ValentineXOX", "Ahahahah", "cat@bnta.com");
        userRepository.save(user7);


//        Establishing relationships

        user1.addUserToFollow(user3);
        user1.addUserToFollow(user4);

        user2.addUserToFollow(user1);
        user2.addUserToFollow(user3);

        user3.addUserToFollow(user1);
        user3.addUserToFollow(user2);

        user4.addUserToFollow(user2);
        user4.addUserToFollow(user1);


//        Creating slaps

        Slap slap1 = new Slap(":wave:", "anyone on my Slap feed watching Love Island right now? ");
        slap1.setUser(user1);
        user1.addSlap(slap1);
        slapRepository.save(slap1);


        Slap slap2 = new Slap(":wave:", "SMH! Hayfever is getting the best of me today");
        slap2.setUser(user2);
        user2.addSlap(slap2);
        slapRepository.save(slap2);


        Slap slap3 = new Slap("U+1F600", "Wait.. Charlotte Tilbury are doing 25% all makeup today? Only just found out lol");
        slap3.setUser(user6);
        user6.addSlap(slap3);
        slapRepository.save(slap3);


        Slap slap4 = new Slap("U+1F600", "No way!!! They should have made to the the live final!!! #BGT #BGTSemiFinals #IDemandARecount");
        slap4.setUser(user3);
        user3.addSlap(slap4);
        slapRepository.save(slap4);

        Slap slap5 = new Slap(":wave:", "that challenge made me cringe so much!");
        slap5.setUser(user1);
        user1.addSlap(slap5);
        slapRepository.save(slap5);




        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);

    }

}