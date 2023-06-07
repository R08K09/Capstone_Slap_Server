package com.example.slap_server.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "slaps")
public class Slap {

    // Properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "mood")
    private String mood;

    @Column(name = "message")
    private String message;

    @JsonIgnoreProperties({"slaps"})
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    // Constructor
    public Slap(String mood, String message, User user){
        this.dateTime = LocalDateTime.now();
        this.mood = mood;
        this.message = message;
        this.user = user;
    }


    // Default Constructor
    public Slap(){

    }


    // Getters & Setters
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
