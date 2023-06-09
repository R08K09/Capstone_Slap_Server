package com.example.slap_server.models;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    // Properties:

    private String username;

    private String bio;

    private String email;

    private List<Long> followingIds;

    private List<Long> followersIds;

    private List<Long> slapIds;


    // Constructor
//    public UserDTO(String username, String bio, String email){
//        this.username = username;
//        this.bio = bio;
//        this.email = email;
//        this.followingIds = new ArrayList<>();
//        this.followersIds = new ArrayList<>();
//        this.slapIds = new ArrayList<>();
//    }


    // Default Constructor
    public UserDTO(){

    }

    public UserDTO(String username, String bio, String email, List<Long> followingIds, List<Long> followersIds, List<Long> slapIds) {
        this.username = username;
        this.bio = bio;
        this.email = email;
        this.followingIds = followingIds;
        this.followersIds = followersIds;
        this.slapIds = slapIds;
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

    public List<Long> getFollowingIds() {
        return followingIds;
    }

    public void setFollowingIds(List<Long> followingIds) {
        this.followingIds = followingIds;
    }

    public List<Long> getFollowersIds() {
        return followersIds;
    }

    public void setFollowersIds(List<Long> followersIds) {
        this.followersIds = followersIds;
    }

    public List<Long> getSlapIds() {
        return slapIds;
    }

    public void setSlapIds(List<Long> slapIds) {
        this.slapIds = slapIds;
    }
}
