/* CS 514 Assignment 7
 * My name is Qianru Wei
 * My Github is Qianru69
 *  */
package org.example;

/**
 * Represent a song that has a title, an artist,  an album it belongs to, a genre and duration.
 */
public class Song extends Entity {
    /**
     * The album that this song belongs to.
     */
    protected Album album;
    /**
     * The artist of this song.
     */
    protected Artist performer;
    /**
     * The duration of this song.
     */
    protected SongInterval duration;
    /**
     * The genre of this song.
     */
    protected String genre;
    /**
     * The level that the user like this song (0-10).
     */
    protected double like;
    /**
     * Get how much the user like this song (0-10).
     */
    public double getLike() {
        return like;
    }
    /**
     * Set how much the user like this song (0-10).
     */
    public void setLike(double like) {this.like = like;}

    /**
     * Create a new song with unknown name.
     */
    public Song() {
        super("");
        album = new Album();
        performer = new Artist();
        duration = new SongInterval();
        genre = "";
    }
    /**
     * Create a new song with known name.
     */
    public Song(String name) {
        super(name);
        album = new Album();
        performer = new Artist();
        duration = new SongInterval();
        genre = "";
    }
    /**
     * Get the album that this song belongs to.
     */
    public Album getAlbum() {
        return album;
    }
    /**
     * Set the album that this song belongs to.
     */
    public void setAlbum(Album album) {
        this.album = album;
    }
    /**
     * Get the artist of this song.
     */
    public Artist getPerformer() {
        return performer;
    }
    /**
     * Set the artist of this song.
     */
    public void setPerformer(Artist performer) {
        this.performer = performer;
    }
    /**
     * Get the genre of this song.
     */
    public String getGenre() {
        return genre;
    }
    /**
     * Set the genre of this song.
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }
    /**
     * Set the length of this song.
     */
    public void setLength(int length) {
        duration = new SongInterval(length);
    }
    /**
     * Show the length of this song.
     */
    public String showLength() {
        return duration.toString();
    }

    /**
     * Check if this song is the same as another one.
     */
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

    /**
     * Return information of this song as a String.
     */
    public String toString() {
        return "Song name: " + this.name;
    }
    /**
     * Return information of this song as HTML.
     */
    public String toHTML() {
        return "<b>" + this.name + "</b><i>" + this.entityID + "</i>";
    }
    /**
     * Return information of this song as XML.
     */
    public String toXML(){
        return ("<song><title>" + name + "</title><artist>" + performer.getName() + "</artist><album>" + album.getName() + "</album></song>");
    }
    /**
     * Return information of this song as a JSON object.
     */
    public String toJSON(){
        return ("{" + "\"id\":\"" + this.entityID + "\"," + "\"title\":\"" + this.getName() + "\"," +
                "\"artist\":" + this.getPerformer().toJSON() + ", \"album\":" + this.getAlbum().toJSON() + "}");
    }
}
