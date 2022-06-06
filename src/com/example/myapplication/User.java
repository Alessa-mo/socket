package com.example.myapplication;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String password;
    private int credits;

    public User(String username, String password, int credits){
        this.username = username;
        this.password = password;
        this.credits = credits;
    }

    public int getCredits(){return credits;}
    public String getUsername(){return username;}
    public String getPassword(){return password;}
}
