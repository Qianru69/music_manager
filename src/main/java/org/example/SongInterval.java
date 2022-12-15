package org.example;

/* CS 514 Assignment 7
 * My name is Qianru Wei
 * My Github is Qianru69
 *  */

public class SongInterval {
    private  int length;
    public SongInterval(){
        this.length = 0;
    }
    public SongInterval(int length){
        this.length = length;
    }

    public String toString(){
        //in Java, two integer divition will return a integer?
        int minutes = length / 60;
        int seconds = length % 60;
        return String.format("%d:%d", minutes, seconds);
    }

}

