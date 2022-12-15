/* CS 514 Assignment 7
 * My name is Qianru Wei
 * My Github is Qianru69
 *  */
package org.example;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Represent a playlist that include a list of songs.
 */
public class Playlist {
    /**
     * The list of songs in this playlist.
     */
    private ArrayList<Song> songList;
    /**
     * Get the list of songs in this playlist.
     */
    public ArrayList<Song> getSongList() {
        return songList;
    }
    /**
     * Set the list of songs in this playlist.
     */
    public void setSongList(ArrayList<Song> songList) {
        this.songList = songList;
    }
    /**
     * Create a new playlist that has an empty list of songs.
     */
    public Playlist() {songList = new ArrayList<Song>();}
    /**
     * Add a song into this playlist.
     */
    public void addSong (Song s) { songList.add(s);}
    /**
     * Delete a song from this playlist
     */
    public void deleteSong(Song s) {
        if (songList.contains(s)){
            songList.remove(s);
        } else {
            System.out.printf("%s is not in the playlist\n", s.toString());
        }
    }
    /**
     * Merge two playlists.
     */
    public void merge(Playlist other) {
        // Merge two playlists.
        Set<Song> songs = new HashSet<Song>(this.songList);
        for (Song s: other.songList) {
            if (songs.contains(s)) {
                continue;
            } else {
                songs.add(s);
            }
        } // remove duplicates
        this.songList = new ArrayList<>(songs);
    }

    /**
     * Sort the songs in this playlist based on how much the users like the songs.
     */
    public void sort() {
        // Sort the playlist so that the songs that are the most liked are in the front
        ArrayList<Song> sortedSongs = new ArrayList<Song>();
        for (Song s : this.songList) {
            if (sortedSongs.size() == 0) {
                sortedSongs.add(s);
            } else {
                double like = s.getLike();
                if (like >= sortedSongs.get(0).getLike()) {
                    sortedSongs.add(0, s);
                } else if (like < sortedSongs.get(sortedSongs.size()-1).getLike()){
                    sortedSongs.add(s);
                } else {
                    for (int i = 0; i < sortedSongs.size()-1; i++) {
                        if (like < sortedSongs.get(i).getLike() && like >= sortedSongs.get(i+1).getLike()){
                            sortedSongs.add(i+1, s);
                        }
                    }
                }
            }
        }
        this.songList = sortedSongs;
    }

    /**
     * Shuffle the songs in this playlist randomly.
     */
    public void shuffle() {
        // Randomly shuffle the playlist.
        Random r = new Random();

        // Start from the last element and swap one by one. We don't
        // need to run for the first element that's why i > 0
        for (int i = this.songList.size()-1; i > 0; i--) {

            // Pick a random index from 0 to i
            int j = r.nextInt(i);
            // Swap arr[i] with the element at random index
            Song s = this.songList.get(i);
            this.songList.set(i, this.songList.get(j));
            this.songList.set(j, s);
        }
    }

    /**
     * Generate a new playlist that include songs that user like >= 8.0
     */

    public void likedMostPlaylist() {
        ArrayList<Song> likedMost = new ArrayList<Song>();
        for (Song s : this.songList) {
            if (s.getLike() >= 8) {
                likedMost.add(s);
            }
        }
        this.songList = likedMost;
        this.shuffle();
    }
    /**
     * Create a XML file based on the songs in this playlist.
     */
    public String toXML(){
        StringBuilder XML = new StringBuilder();
        XML.append("<songs>");
        for (Song s: this.songList) {
            XML.append("<song><title>"+s.getName()+"</title><artist>"+s.getPerformer().getName()
                    +"</artist><album>"+s.getAlbum().getName()+"</album></song>\n");
        }
        XML.append("</songs>");
        try {
            FileWriter fWriter = new FileWriter(
                    "src/music-playlist.xml");
            fWriter.write(XML.toString());
            fWriter.close();
            System.out.println(
                    "File is created successfully with the content.");
        }catch (IOException e) {
            // Print the exception
            System.out.print(e.getMessage());
        }
        return XML.toString();
    }
    /**
     * Create a JSON file based on the songs in this playlist.
     */
    public String toJSON(){
        StringBuilder JSON = new StringBuilder();
        JSON.append("{" + "\"songs\": [");
        for (Song s: this.songList) {
            JSON.append(s.toJSON() + ",");
        }
        JSON.deleteCharAt(JSON.length() - 1);
        JSON.append("]}");
        try {
            FileWriter fWriter = new FileWriter(
                    "src/music-playlist.json");
            fWriter.write(JSON.toString());
            fWriter.close();
            System.out.println(
                    "File is created successfully with the content.");
        }catch (IOException e) {
            // Print the exception
            System.out.print(e.getMessage());
        }
        return JSON.toString();
    }
}

