/* CS 514 Assignment 7
 * My name is Qianru Wei
 * My Github is Qianru69
 *  */
package org.example;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Represent a music library that has songs.
 */
public class Library {
    /**
     * The songs in this music library.
     */
    private ArrayList<Song> songs;
    /**
     * Create a new Library with a list of songs.
     */
    public Library() { songs = new ArrayList<Song>(); }
    /**
     * Find if a song is in this Library or not.
     */
    public boolean findSong(Song s) {
        return songs.contains(s);
    }
    /**
     * Get songs from this music library.
     */
    public ArrayList<Song> getSongs() {
        return songs;
    }
    /**
     * Add a song into this Library.
     */

    public void addSong (Song s) { songs.add(s); }
    /**
     * Read songs from a XML file.
     */
    public void readFromXML(String filename) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(filename));

            Element root = doc.getDocumentElement();
            // System.out.println(root);
            NodeList songList = root.getElementsByTagName("song");
            Node currentNode, subNode;

            Song currentSong;
            for (int i = 0; i < songList.getLength(); i++) {
                currentNode = songList.item(i);
                NodeList children = currentNode.getChildNodes();
                currentSong = new Song();
                String song_name = "";
                String artist_name = "";
                String album_name = "";
                for (int j = 0; j < children.getLength(); j++) {
                    subNode = children.item(j);
                    if (subNode.getNodeType() == Node.ELEMENT_NODE) {
                        if (subNode.getNodeName().equals("title")) {
                            song_name = subNode.getTextContent().strip();
                        } else if (subNode.getNodeName().equals("album")) {
                            album_name = subNode.getTextContent().strip();
                        } else if (subNode.getNodeName().equals("artist")) {
                            artist_name = subNode.getTextContent().strip();
                        }
                    }
                }
                currentSong.setName(song_name);
                currentSong.setAlbum(new Album(album_name));
                currentSong.setPerformer(new Artist(artist_name));
                this.addSong(currentSong);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found " + e);
        } catch (Exception e) {
            System.out.println("Parsing error:" + e);
            //Exception: file does not exist or xml is not well formatted
        }
    }
    /**
     * Read songs from a JSON file.
     */
    public void readFromJSON(String filename) {
        String s;
        try {
            Scanner sc = new Scanner(new File(filename));
            sc.useDelimiter("\\Z");
            s = sc.next();
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(s);
            JSONObject jsonObject = (JSONObject) obj;

            JSONArray songArray = (JSONArray) jsonObject.get("songs");
            for (Object song : songArray) {
                JSONObject jsong = (JSONObject) song;
                this.addSong(extractSong(jsong));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found " + e);
        } catch (ParseException e1) {
            System.out.println("Parser error");
        }
    }

    public Song extractSong(JSONObject song) {
        Song s = new Song();
        JSONObject jsong = (JSONObject) song;
        s.setName(jsong.get("title").toString());
        JSONObject child1 = (JSONObject) jsong.get("artist");
        JSONObject child2 = (JSONObject) jsong.get("album");
        Artist currentArtist = new Artist(child1.get("name").toString());
        Album currentAlbum = new Album(child2.get("name").toString());
        s.setAlbum(currentAlbum);
        s.setPerformer(currentArtist);
        return s;
    }

    /**
     * Remove a song from this Library.
     */
    public void removeSong(Song a) {
        this.songs.remove(a);
    }

    /**
     * Create a XML file based on the information in this music library.
     */
    public String toXML(){
        StringBuilder XML = new StringBuilder();
        XML.append("<library>" + "<songs>");
        for (Song s: this.songs) {
            XML.append(s.toXML());
        }
        XML.append("</songs>" + "<artists>");
        Set artists = new HashSet();
        for (Song s: this.songs) {
            if (artists.contains(s.getPerformer().getName())) {
                continue;
            } else {
                artists.add(s.getPerformer().getName());
                XML.append(s.getPerformer().toXML());
            }
        }
        XML.append("</artists>" + "<albums>");
        Set albums = new HashSet();
        for (Song s: this.songs) {
            if (albums.contains(s.getAlbum().getName())) {
                continue;
            } else{
                albums.add(s.getAlbum().getName());
                XML.append(s.getAlbum().toXML());
            }
        }
        XML.append("</albums>" + "</library>");
        try {
            FileWriter fWriter = new FileWriter(
                    "src/music-playlist1.xml");
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
     * Create a JSON file based on the information in this music library.
     */
    public String toJSON(){
        StringBuilder JSON = new StringBuilder();
        JSON.append("{" + "\"songs\": [");
        for (Song s: this.songs) {
            JSON.append(s.toJSON() + ",");
        }
        JSON.deleteCharAt(JSON.length() - 1);
        JSON.append("], \"artists\": [");
        Set artists = new HashSet();
        for (Song s: this.songs) {
            if (artists.contains(s.getPerformer().getName())) {
                continue;
            } else{
                artists.add(s.getPerformer().getName());
                JSON.append(s.getPerformer().toJSON() + ",");
            }
        }
        JSON.deleteCharAt(JSON.length() - 1);
        JSON.append("], \"albums\": [");
        Set albums = new HashSet();
        for (Song s: this.songs) {
            if (albums.contains(s.getAlbum().getName())) {
                continue;
            } else{
                albums.add(s.getAlbum().getName());
                JSON.append(s.getAlbum().toJSON() + ",");
            }
        }
        JSON.deleteCharAt(JSON.length() - 1);
        JSON.append("]}");

        try {
            FileWriter fWriter = new FileWriter(
                    "src/music-playlist1.json");
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
