package com.example.myapplication;

public class InternetUser{
    private String username;
    private int socre;
    private int life;
    private int ID;
    public InternetUser(User user,int ID){
        this.socre = user.getSocre();
        this.life = user.getLife();
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getSocre() {
        return socre;
    }

    public void setSocre(int socre) {
        this.socre = socre;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
