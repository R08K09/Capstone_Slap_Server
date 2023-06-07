package com.example.slap_server.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.*;

@Entity(name = "users")
public class User {

    // Properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "bio")
    private String bio;

    @Column(name = "email")
    private String email;

    @Column(name = "friends")
    private List<User> friends;

    @JsonIgnoreProperties({"users"})
    @OneToMany(mappedBy = "user")
    @Column(name = "slaps")
    private List<Slap> slaps;


    // Constructor
    public User(String username, String bio, String email){
        this.username = username;
        this.bio = bio;
        this.email = email;
        this.friends = new ArrayList<>();
        this.slaps = new ArrayList<>();
    }


    // Default Constructor
    public User(){

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
