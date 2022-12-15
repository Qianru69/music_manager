package org.example;

/* CS 514 Assignment 7
 * My name is Qianru Wei
 * My Github is Qianru69
 *  */
/**
 * Represent the length of a song.
 */
public class SongInterval {
    /**
     * The length of this song interval
     */
    private int length;
    /**
     * Create a new Song Interval with an unknown length.
     */
    public SongInterval(){
        this.length = 0;
    }/**
     * Create a new Song Interval with a known length.
     */
    public SongInterval(int length){
        this.length = length;
    }
    /**
     * Return information of this songInterval as a string.
     */

    public String toString(){
        //in Java, two integer divition will return a integer?
        int minutes = length / 60;
        int seconds = length % 60;
        return String.format("%d:%d", minutes, seconds);
    }

}

