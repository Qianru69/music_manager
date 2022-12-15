package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaylistTest {

    @Test
    void toXML() {
        Library l = new Library();
        Album al1 = new Album("1989");
        Artist ar1 = new Artist("Taylor Swift");
        Song s1 = new Song("Bad Blood");
        Song s2 = new Song("Bad Blood");
        Song s3 = new Song("Blank Space");
        Song s4 = new Song("blank space");
        l.addSong(s1);
        l.addSong(s2);
        l.addSong(s3);
        l.addSong(s4);

        for (Song s : l.getSongs()) {
            System.out.println("Song name: " + s.name);
            System.out.println("Artist name: " + s.getPerformer().getName());
            System.out.println("Album name: " + s.getAlbum().getName());
            System.out.println();
        }
        System.out.println(l.toXML());
    }

    @Test
    void toJSON() {
        Library l = new Library();
        Album al1 = new Album("1989");
        Artist ar1 = new Artist("Taylor Swift");
        Song s1 = new Song("Bad Blood");
        Song s2 = new Song("Bad Blood");
        Song s3 = new Song("Blank Space");
        Song s4 = new Song("blank space");
        l.addSong(s1);
        l.addSong(s2);
        l.addSong(s3);
        l.addSong(s4);
        for (Song s : l.getSongs()) {
            System.out.println("Song name: " + s.name);
            System.out.println("Artist name: " + s.getPerformer().getName());
            System.out.println("Album name: " + s.getAlbum().getName());
            System.out.println();
        }
        System.out.println(l.toJSON());
    }
}