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

        // Creating users
        User user1 = new User("KimKardashh_xo", "If I was funny, I probably would have a better Slap bio.", "kimmyk@bnta.com", "https://i.imgur.com/mRiJb2O.jpg");
        user1.setPassword("kimmyk123");
        userRepository.save(user1);

        User user2 = new User("NatashaTheGOAT", "I'm not scared of the capstone, the capstone is scared of me", "tasha@bnta.com", "https://i.imgur.com/ukufO5H.png");
        user2.setPassword("tasha123");
        userRepository.save(user2);

        User user3 = new User("Zayn_Malik", "I’ll get over it, I just need to Slap about it first!", "zayn@bnta.com", "https://i.imgur.com/jxwiIOx.jpg");
        user3.setPassword("zayn123");
        userRepository.save(user3);

        User user4 = new User("TheSirAttenborough", "Be conscious of your carbon footprint, save the world.", "david@bnta.com", "https://i.imgur.com/A6i3G7J.jpeg");
        user4.setPassword("david123");
        userRepository.save(user4);

        User user5 = new User("AnnaTheUnicorn", "Life is short. Make each second count. And enjoy iced lattes while you’re at it", "anna@bnta.com", "https://i.imgur.com/ukufO5H.png");
        user5.setPassword("anna123");
        userRepository.save(user5);

        User user6 = new User("ZsoltTheCoder", "Oh boy!! That looks JANKY", "zsolt@bnta.com", "https://i.imgur.com/ukufO5H.png");
        user6.setPassword("zsolt123");
        userRepository.save(user6);

        User user7 = new User("Tori_Vega", "You don't have to be afraid to put your dreams in action", "tori@bnta.com", "https://i.imgur.com/HdlolS9.jpg");
        user7.setPassword("tori123");
        userRepository.save(user7);

        User user8 = new User("Cat.ValentineXOX", "Ahahahah", "cat@bnta.com", "https://i.imgur.com/VivxeP9.jpg");
        user8.setPassword("cat123");
        userRepository.save(user8);

        User user9 = new User("MegaMindSujan", "I have the biggest brain in c9", "b.brainSujan@bnta.com", "https://i.imgur.com/ukufO5H.png");
        user9.setPassword("b.brainSujan123");
        userRepository.save(user9);


        // Establishing relationships
        user1.addUserToFollow(user3);
        user1.addUserToFollow(user4);

        user2.addUserToFollow(user1);
        user2.addUserToFollow(user3);

        user3.addUserToFollow(user1);
        user3.addUserToFollow(user2);

        user4.addUserToFollow(user2);
        user4.addUserToFollow(user1);


        // Creating slaps
        Slap slap1 = new Slap(":wave:", "anyone on my Slap feed watching Love Island right now? ");
        slap1.setUser(user1);
        user1.addSlap(slap1);
        slapRepository.save(slap1);

        Slap slap2 = new Slap(":wave:", "Hayfever szn went from 0-100 real quick");
        slap2.setUser(user3);
        user3.addSlap(slap2);
        slapRepository.save(slap2);

        Slap slap3 = new Slap("U+1F600", "Wait.. Charlotte Tilbury are doing 25% all makeup today? Only just found out lol");
        slap3.setUser(user2);
        user2.addSlap(slap3);
        slapRepository.save(slap3);

        Slap slap4 = new Slap("U+1F600", "watching endangered animals repopulate is an amazing thing");
        slap4.setUser(user4);
        user4.addSlap(slap4);
        slapRepository.save(slap4);

        Slap slap5 = new Slap(":wave:", "that challenge made me cringe so much!");
        slap5.setUser(user2);
        user2.addSlap(slap5);
        slapRepository.save(slap5);

        Slap slap6 = new Slap(":excited:", "I can't believe Jade and Beck broke up?!??");
        slap6.setUser(user7);
        user7.addSlap(slap6);
        slapRepository.save(slap6);

        Slap slap7 = new Slap(":crazy:", "ahahahha my brother has a stuffed teddy that he dips in ketchup :)");
        slap7.setUser(user8);
        user8.addSlap(slap7);
        slapRepository.save(slap7);

        Slap slap8 = new Slap(":tired:", "Cohort 9 is my favourite cohort EVER!! #longliveCapybara9");
        slap8.setUser(user6);
        user6.addSlap(slap8);
        slapRepository.save(slap8);

        Slap slap9 = new Slap(":bubbly:", "I love having blathers with my coding kids :) #CapybaraCodingKids");
        slap9.setUser(user5);
        user5.addSlap(slap9);
        slapRepository.save(slap9);

        Slap slap10 = new Slap(":excited", "It's my time to shine #FreakTheFreakOut");
        slap10.setUser(user7);
        user7.addSlap(slap10);
        slapRepository.save(slap10);

        Slap slap11 = new Slap(":confused:", "All the projects look janky?? #OhBoy");
        slap11.setUser(user6);
        user6.addSlap(slap11);
        slapRepository.save(slap11);

        Slap slap12 = new Slap(":confused:", "Some people need to grow up, how are you allergic to grass at your big age #DoBetter");
        slap12.setUser(user9);
        user9.addSlap(slap12);
        slapRepository.save(slap12);

        Slap slap13 = new Slap(":disappointed:", "Who named us the Keyboard Warriors?? SMH");
        slap13.setUser(user2);
        user2.addSlap(slap13);
        slapRepository.save(slap13);

        Slap slap14 = new Slap(":covering eyes:", "I woke up and my eyes were glued together, can eyes make glue??");
        slap14.setUser(user8);
        user8.addSlap(slap14);
        slapRepository.save(slap14);

        Slap slap15 = new Slap(":bored:", "Stop asking me questions ab 1D, it's never happening again.");
        slap15.setUser(user3);
        user3.addSlap(slap15);
        slapRepository.save(slap15);


        // Saving Users
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);
        userRepository.save(user6);
        userRepository.save(user7);
        userRepository.save(user8);
        userRepository.save(user9);

    }

}