package com.example.slap_server.models;

import jakarta.persistence.*;

@Entity(name = "friendships")
public class Friendship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "first_user_id", referencedColumnName = "id")
    private User first_user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "second_user_id", referencedColumnName = "id")
    private User second_user;


    public Friendship(User first_user, User second_user){
        this.first_user = first_user;
        this.second_user = second_user;
    }

    public Friendship(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getFirst_user() {
        return first_user;
    }

    public void setFirst_user(User first_user) {
        this.first_user = first_user;
    }

    public User getSecond_user() {
        return second_user;
    }

    public void setSecond_user(User second_user) {
        this.second_user = second_user;
    }
}
