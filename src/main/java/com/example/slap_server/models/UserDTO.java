package com.example.slap_server.models;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    // Properties:

    private String username;

    private String bio;

    private String email;

//    private List<User> following;
//
//    private List<User> followers;
//
//    private List<Slap> slaps;


    // Constructor
    public UserDTO(String username, String bio, String email){
        this.username = username;
        this.bio = bio;
        this.email = email;
//        this.following = new ArrayList<>();
//        this.followers = new ArrayList<>();
//        this.slaps = new ArrayList<>();
    }


    // Default Constructor
    public UserDTO(){

    }

    // Getters & Setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public List<User> getFollowing() {
//        return following;
//    }
//
//    public void setFollowing(List<User> following) {
//        this.following = following;
//    }
//
//    public List<User> getFollowers() {
//        return followers;
//    }
//
//    public void setFollowers(List<User> followers) {
//        this.followers = followers;
//    }
//
//    public List<Slap> getSlaps() {
//        return slaps;
//    }
//
//    public void setSlaps(List<Slap> slaps) {
//        this.slaps = slaps;
//    }
}
