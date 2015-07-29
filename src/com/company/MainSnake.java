package com.company;

import java.util.ArrayList;

public class MainSnake {
    //private declarations
    private String name;
    private int channelCount;
    private boolean artistSupplied;
    private int amountOfSnakes=0;


    //Constructors
    public MainSnake() {
        this.name = null;
        this.channelCount = 0;
        this.artistSupplied = false;
    }

    public MainSnake(String name, int channelCount, boolean artistSupplied) {
        this.name = name;
        this.channelCount = channelCount;
        this.artistSupplied = artistSupplied;
    }

    public MainSnake[] mainSnakesArrayCreator() {
        MainSnake[] mainSnakes = new MainSnake[amountOfSnakes];
        return mainSnakes;
    }

    //setters
    public void setName (String newName) {
        this.name = newName;
    }

    public void setChannelCount (int newCount) {
        this.channelCount = newCount;
    }

    public void setArtistSupplied (boolean artistSupplied) {
        this.artistSupplied = artistSupplied;
    }

    //getters
    public String getName () {
        return name;
    }

    public int getChannelCount () {
        return channelCount;
    }

    public boolean getArtistSupplied () {
        return artistSupplied;
    }
}
