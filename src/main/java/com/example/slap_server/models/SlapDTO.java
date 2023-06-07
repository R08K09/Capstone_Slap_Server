package com.example.slap_server.models;

import java.time.LocalDateTime;

public class SlapDTO {

//   Properties
    private long id;
    private LocalDateTime dateTime;
    private String mood;
    private String message;
    private User user;

// Constructor
    public SlapDTO (LocalDateTime dateTime, String mood, String message, User user){
        this.dateTime = dateTime;
        this.mood = mood;
        this.message = message;
        this.user = user;
    }


    // Default Constructor
    public SlapDTO(){

    }


//    Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
