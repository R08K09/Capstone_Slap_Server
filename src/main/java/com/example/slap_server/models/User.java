package com.example.slap_server.models;

import com.example.slap_server.repositories.FriendshipRepository;
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
    private List<Friendship> friendships;

    @JsonIgnoreProperties({"users"})
    @OneToMany(mappedBy = "user")
    @Column(name = "slaps")
    private List<Slap> slaps;


    // Constructor
    public User(String username, String bio, String email){
        this.username = username;
        this.bio = bio;
        this.email = email;
        this.friendships = new ArrayList<>();
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

    public List<Friendship> getFriendships() {
        return friendships;
    }

    public void setFriendships(List<Friendship> friendship) {
        this.friendships = friendship;
    }

    public List<Slap> getSlaps() {
        return slaps;
    }

    public void setSlaps(List<Slap> slaps) {
        this.slaps = slaps;
    }


//    Methods

    public void addFriendship(Friendship friendship){
        this.friendships.add(friendship);
    }

    public void removeFriendship(Friendship friendship){
        this.friendships.remove(friendship);
    }

    public void addSlap(Slap slap){
        this.slaps.add(slap);
    }

    public void removeSlap(Slap slap){
        this.slaps.remove(slap);
    }

}
