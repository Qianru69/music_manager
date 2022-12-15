/* CS 514 Assignment 7
 * My name is Qianru Wei
 * My Github is Qianru69
 *  */

package org.example;
import java.util.ArrayList;
/**
 * Represents an artist who published songs and albums.
 */
public class Artist extends Entity {
    /**
     * Songs published by this artist.
     */
    protected ArrayList<Song> songs;
    /**
     * Albums published by this artist.
     */
    protected ArrayList<Album> albums;
    /**
     * Create a new Artist with unknown name.
     */
    public Artist() {
        super("");
    }
    /**
     * Create a new Artist with known name.
     */
    public Artist(String name) {
        super(name);
    }
    /**
     * Get all songs that published by this artist.
     */
    protected ArrayList<Song> getSongs() {
        return songs;
    }
    /**
     * Set all songs that published by this artist.
     */
    protected void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }
    /**
     * Get all albums that published by this artist.
     */
    protected ArrayList<Album> getAlbums() {
        return albums;
    }
    /**
     * Set all albums that published by this artist.
     */
    protected void setAlbums(ArrayList<Album> albums) {
        this.albums = albums;
    }
    /**
     * Add a song into this artist's songs.
     */
    public void addSong(Song s) {
        songs.add(s);
    }
    /**
     * Check if this artist is the same as another artist.
     */
    public boolean equals(Artist other) {
        String thisName = this.getName();
        String otherName = other.getName();
        return thisName.equals(otherName);
    }
    /**
     * Return information of this album as a string.
     */
    public String toString() {
        return "Artist name: " + this.name;
    }
    /**
     * Return information of this album as a JSON object.
     */
    public String toJSON () {
        return ("{" +"\"id\": \"" + this.entityID + "\"," +"\"name\": \"" + this.getName() + "\"}");
    }
}

