package com.example.slap_server.models;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {
    //    Properties
    private long id;
    private String username;
    private String bio;
    private String email;
    private List<User> friends;
    private List<Slap> slaps;

    // Constructor
    public UserDTO(String username, String bio, String email){
        this.username = username;
        this.bio = bio;
        this.email = email;
        this.friends = new ArrayList<>();
        this.slaps = new ArrayList<>();
    }


    // Default Constructor
    public UserDTO(){

    }


    // Getters & Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public List<Slap> getSlaps() {
        return slaps;
    }

    public void setSlaps(List<Slap> slaps) {
        this.slaps = slaps;
    }
}
