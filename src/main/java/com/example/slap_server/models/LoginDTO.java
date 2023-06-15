package com.example.slap_server.models;

public class LoginDTO {

    // Properties
    private String email;

    private String password;


    // Constructor
    public LoginDTO(String email, String password){
        this.email = email;
        this.password = password;
    }


    // Default Constructor
    public LoginDTO(){

    }


    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
