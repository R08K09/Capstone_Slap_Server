package com.example.slap_server.models;

import java.time.LocalDateTime;

public class SlapDTO {

//   Properties
    private LocalDateTime dateTime;
    private String mood;
    private String message;
    private Long userId;

// Constructor
    public SlapDTO (String mood, String message, Long userId){
        this.dateTime = LocalDateTime.now();
        this.mood = mood;
        this.message = message;
        this.userId = userId;
    }


    // Default Constructor
    public SlapDTO(){

    }


//    Getters and setters

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
