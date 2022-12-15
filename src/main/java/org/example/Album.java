/* CS 514 Assignment7
 * My name is Qianru Wei
 * My Github is Qianru69
 *  */
package org.example;
import java.util.ArrayList;

/**
 * Represents an album that can set artist and songs.
 */
public class Album extends Entity {
    /**
     * The songs on this album.
     */
    protected ArrayList<Song> songs;
    /**
     * The artist of this album.
     */
    protected Artist artist;

    /**
     * Create an Album with unknown title.
     */
    public Album() {
        super("");
    }
    /**
     * Create an Album with known title.
     */
    public Album(String name) {
        super(name);
    }
    /**
     * Get the name of the artist of the album.
     */
    public Artist getArtist() {
        return artist;
    }
    /**
     * Set the name of the artist of the album.
     */
    public void setArtist(Artist artist) {
        this.artist = artist;
    }
    /**
     * Add a song into this album.
     */
    public void addSong(Song s){
        songs.add(s);
    }
    /**
     * Check if this album is the same as another one.
     */
    public boolean equals(Album other) {
        String thisName = this.getName();
        String otherName = other.getName();
        Artist thisArtist = this.getArtist();
        Artist otherArtist = other.getArtist();
        return (thisArtist.equals(otherArtist) && thisName.equals(otherName));
    }
    /**
     * Return information of this album as a string.
     */
    public String toString() {
        return "Album name: " + this.name;
    }
}
