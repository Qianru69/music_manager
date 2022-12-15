package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @Test
    void searchSongWithArtist() {
        App a = new App();
        ArrayList<Song> search = new ArrayList<>();
        search = a.searchSongWithArtist("coldplay");
        for (Song s:search){
            System.out.println("Song title: " + s.getName() + ", " + s.getPerformer() + ", " + s.getAlbum());
        }
    }

    @Test
    void searchSongWithArtistAndTitle() {
        App a = new App();
        ArrayList<Song> search = new ArrayList<>();
        search = a.searchSongWithArtistAndTitle("coldplay", "yellow");
        for (Song s:search) {
            System.out.println("Song title: " + s.getName() + ", " + s.getPerformer() + ", " + s.getAlbum());
        }
    }


    @Test
    void generatePlaylist() {
        App a1 = new App();

        a1.generatePlaylist("all");
        a1.generatePlaylist("rock");
        a1.generatePlaylist("pop");
    }

    @Test
    void addSong() {
        App a1 = new App();
        Song s1 = new Song("Love Story");
        a1.addSong(s1);
    }
}