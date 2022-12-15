/* CS 514 Assignment 7
 * My name is Qianru Wei
 * My Github is Qianru69
 *  */

package org.example;
import java.util.ArrayList;

public class Artist extends Entity {

    protected ArrayList<Song> songs;
    protected ArrayList<Album> albums;

    public Artist() {
        super("");
    }
    public Artist(String name) {
        super(name);
    }

    protected ArrayList<Song> getSongs() {
        return songs;
    }

    protected void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

    protected ArrayList<Album> getAlbums() {
        return albums;
    }

    protected void setAlbums(ArrayList<Album> albums) {
        this.albums = albums;
    }

    public void addSong(Song s) {
        songs.add(s);
    }

    public boolean equals(Artist other) {
        String thisName = this.getName();
        String otherName = other.getName();
        return thisName.equals(otherName);
    }
    public String toString() {
        return "Artist name: " + this.name;
    }
    public String toJSON () {
        return ("{" +"\"id\": \"" + this.entityID + "\"," +"\"name\": \"" + this.getName() + "\"}");
    }

}

