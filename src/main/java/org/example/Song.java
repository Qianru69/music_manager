/* CS 514 Assignment 7
 * My name is Qianru Wei
 * My Github is Qianru69
 *  */
package org.example;
public class Song extends Entity {
    protected Album album;
    protected Artist performer;
    protected SongInterval duration;
    protected String genre;
    protected double like;

    public double getLike() {
        return like;
    }

    public void setLike(double like) {this.like = like;}


    public Song() {
        super("");
        album = new Album();
        performer = new Artist();
        duration = new SongInterval();
        genre = "";
    }
    public Song(String name) {
        super(name);
        album = new Album();
        performer = new Artist();
        duration = new SongInterval();
        genre = "";
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Artist getPerformer() {
        return performer;
    }

    public void setPerformer(Artist performer) {
        this.performer = performer;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setLength(int length) {
        duration = new SongInterval(length);
    }

    public String showLength() {
        return duration.toString();
    }

    public int equals(Song other) {
        String thisName = this.getName();
        String otherName = other.getName();
        String artistName = this.getPerformer().getName();
        String otherArtistName = other.getPerformer().getName();
        String albumName = this.getAlbum().getName();
        String otherAlbumName = other.getAlbum().getName();
        if (thisName.equals(otherName) && artistName.equals(otherArtistName) && albumName.equals(otherAlbumName)) {
            return 1;
        } else if (thisName.toLowerCase().equals(otherName.toLowerCase()) && artistName.toLowerCase().equals(otherArtistName.toLowerCase()) && albumName.toLowerCase().equals(otherAlbumName.toLowerCase())) {
            return 2;
        } else {
            return 0;
        }
    }

    public String toString() {
        return "Song name: " + this.name;
    }
    public String toHTML() {
        return "<b>" + this.name + "</b><i>" + this.entityID + "</i>";
    }
    public String toXML(){
        return ("<song><title>" + name + "</title><artist>" + performer.getName() + "</artist><album>" + album.getName() + "</album></song>");
    }
    public String toJSON(){
        return ("{" + "\"id\":\"" + this.entityID + "\"," + "\"title\":\"" + this.getName() + "\"," +
                "\"artist\":" + this.getPerformer().toJSON() + ", \"album\":" + this.getAlbum().toJSON() + "}");
    }
}
