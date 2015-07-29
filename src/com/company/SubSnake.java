package com.company;

/**
 * Created by Travis on 2/23/2015.
 */
public class SubSnake {
    //private declarations
    private String name;
    private int channelCount;
    protected static String[] names = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
    "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};


    //constructors
    public SubSnake() {
        this.name = null;
        this.channelCount = 0;
    }

    public SubSnake(String tempName, int tempCount) {
        this.name = tempName;
        this.channelCount = tempCount;
    }

    //setters
    public void setName (String newName) {
        this.name = newName;
    }

    public void setChannelCount (int newCount) {
        this.channelCount = newCount;
    }

    //getters
    public String getName () {
        return name;
    }

    public int getChannelCount () {
        return channelCount;
    }

}
