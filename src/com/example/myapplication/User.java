package com.example.myapplication;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String password;
    private int socre;
    private int life;

    public User(String username, String password, int score){
        this.username = username;
        this.password = password;
        this.socre = score;
        this.life = 1500;
    }

    public int getLife() {
        return life;
    }

    public int getSocre(){return socre;}
    public String getUsername(){return username;}
    public String getPassword(){return password;}

    public void setSocre(int socre) {
        this.socre = socre;
    }

    public void setLife(int life) {
        this.life = life;
    }
}
