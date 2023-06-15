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

    @Column(name = "password")
    private String password;

    @Column(name = "bio")
    private String bio;

    @Column(name = "email")
    private String email;

    @Column(name = "profilePicture")
    private String profilePicture;

    @JsonIgnoreProperties({"followers", "following"})
    @ManyToMany
    @JoinTable(
            name = "followings",
            joinColumns={@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns={@JoinColumn(name = "following_id", referencedColumnName = "id")}
    )
    private List<User> following;

    @JsonIgnoreProperties({"following", "followers"})
    @ManyToMany(mappedBy = "following")
    private List<User> followers;

    @JsonIgnoreProperties({"user"})
    @OneToMany(mappedBy = "user")
    @Column(name = "slaps")
    private List<Slap> slaps;


    // Constructor
    public User(String username, String bio, String email, String profilePicture){
        this.username = username;
        this.bio = bio;
        this.email = email;
        this.following = new ArrayList<>();
        this.followers = new ArrayList<>();
        this.slaps = new ArrayList<>();
        this.profilePicture = profilePicture;
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

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public List<Slap> getSlaps() {
        return slaps;
    }

    public void setSlaps(List<Slap> slaps) {
        this.slaps = slaps;
    }


    // Methods
    public void addUserToFollow(User user){
        this.following.add(user);
    }

    public void unfollow(User user){
        this.following.remove(user);
    }

    public void removeFollower(User user){
        this.followers.remove(user);
    }

    public void addSlap(Slap slap){
        this.slaps.add(slap);
    }

    public void removeSlap(Slap slap){
        this.slaps.remove(slap);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
