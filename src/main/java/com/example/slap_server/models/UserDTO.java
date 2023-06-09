package com.example.slap_server.models;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    // Properties
    private String username;
    private String bio;
    private String email;
    private String password;
    private String profilePicture;
    private List<Long> followingIds;
    private List<Long> followerIds;
    private List<Long> slapIds;


    // Constructor
    public UserDTO(String username, String bio, String email, String password, String profilePicture) {
        this.username = username;
        this.bio = bio;
        this.email = email;
        this.password = password;
        this.profilePicture = profilePicture;
//        this.followerIds = followerIds;
//        this.followingIds = followingIds;
//        this.slapIds = slapIds;
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

    public List<Long> getFollowerIds() {
        return followerIds;
    }

    public void setFollowerIds(List<Long> followerIds) {
        this.followerIds = followerIds;
    }

    public List<Long> getFollowingIds() {
        return followingIds;
    }

    public void setFollowingIds(List<Long> followingIds) {
        this.followingIds = followingIds;
    }

    public List<Long> getSlapIds() {
        return slapIds;
    }

    public void setSlapIds(List<Long> slapIds) {
        this.slapIds = slapIds;
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