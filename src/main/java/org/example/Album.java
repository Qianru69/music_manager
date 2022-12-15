/* CS 514 Assignment7
 * My name is Qianru Wei
 * My Github is Qianru69
 *  */
package org.example;
import java.util.ArrayList;

public class Album extends Entity {
    protected ArrayList<Song> songs;
    protected Artist artist;

    public Album() {
        super("");
    }
    public Album(String name) {
        super(name);
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public void addSong(Song s){
        songs.add(s);
    }

    public boolean equals(Album other) {
        String thisName = this.getName();
        String otherName = other.getName();
        Artist thisArtist = this.getArtist();
        Artist otherArtist = other.getArtist();
        return (thisArtist.equals(otherArtist) && thisName.equals(otherName));
    }
    public String toString() {
        return "Album name: " + this.name;
    }
}
