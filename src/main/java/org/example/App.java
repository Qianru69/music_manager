/* CS 514 Assignment 7
 * My name is Qianru Wei
 * My Github is Qianru69
 *  */

package org.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class App 
{
    public static void startApp() {
        System.out.println("Welcome to Music Manager! Please login.");
        System.out.println("Please enter User ID and password.(For testing, enter 'test' and 'test')");
        while(true){
            System.out.printf(">> USER ID: ");
            // prompt the user for user ID.
            Scanner ID = new Scanner(System.in);
            String userID = ID.nextLine();
            System.out.printf(">> USER password: ");
            // prompt the user for user password.
            Scanner pw = new Scanner(System.in);
            String userPW = pw.nextLine();
            if (login(userID, userPW) == true){
                break;
            } else {
                System.out.println("Login failed. Please try again.");
            }
        }
        System.out.println("Login successful! Welcome back.");
        System.out.println();
        System.out.println("[1] Generate a playlist.\n[2] Search for a new song.\n[0] to exit Music Manager.");
        System.out.printf(">> ");
        Scanner number = new Scanner(System.in);
        String num = number.nextLine();
        if (num.equals("1")) {
            System.out.printf("[1] Generate a playlist with all songs.\n[2] Generate a playlist based on genre 'rock'.\n" +
                    "[3] Generate a playlist based on genre 'pop'.\n >>   ");
            Scanner list = new Scanner(System.in);
            String list1 = list.nextLine();
            if (list1.equals("1")) {
                generatePlaylist("all");
            } else if (list1.equals("2")) {
                generatePlaylist("rock");
            } else if (list1.equals("3")) {
                generatePlaylist("pop");
            } else {
                System.out.println("Cannot generate playlist.");
            }


        } else if (num.equals("2")){
            System.out.println("[1] Search a new song based on artist name");
            System.out.println("[2] Search a new song based on artist name and song title");
            // System.out.println("[3] Search a new song based on album name");
            System.out.printf(">> ");
            Scanner choice1 = new Scanner(System.in);
            String c1 = choice1.nextLine();
            // create a list to store the search result.
            if (c1.equals("1")){
                System.out.printf(">> Artist name (for test, enter 'coldplay'): ");
                Scanner artist = new Scanner(System.in);
                String a1 = artist.nextLine().strip();
                ArrayList<Song> search = new ArrayList<>();
                search = searchSongWithArtist(a1);
                if (search.size() == 0) {
                    System.out.println("No songs found.");
                } else {
                    for (int i = 0; i < search.size(); i++){
                        System.out.println("[" + (i+1) + "] Artist name: " + search.get(i).getPerformer() +
                                ", Song title: " + search.get(i).getName() + ", Album Name: " + search.get(i).getAlbum());
                    }
                    System.out.println();
                    System.out.printf("Which song do you want to play?   >> ");
                    Scanner songPlay = new Scanner(System.in);
                    String songPlay1 = songPlay.nextLine();
                    int sPlay = Integer.valueOf(songPlay1)-1;
                    System.out.println("Playing song: " + search.get(sPlay).getName());
                    System.out.println();
                    System.out.printf("Do you want to add this song into your song library? Enter Y/N   >> ");
                    Scanner answer = new Scanner(System.in);
                    String as = answer.nextLine();
                    if (as.equals("Y")) {
                        addSong(search.get(sPlay));
                        System.out.println(search.get(sPlay).toXML());
                    } else if (as.equals("N")) {
                        //prompt user what they want to do next
                    }
                }
            } else if (c1.equals("2")) {
                System.out.printf(">> Artist name (for test, enter 'coldplay'): ");
                Scanner artist2 = new Scanner(System.in);
                String a2 = artist2.nextLine().strip();
                System.out.printf(">> Song title (for test, enter 'yellow'): ");
                Scanner song2 = new Scanner(System.in);
                String s2 = song2.nextLine();

                ArrayList<Song> search = new ArrayList<>();
                search = searchSongWithArtistAndTitle(a2, s2);
                if (search.size() == 0) {
                    System.out.println("No songs found.");
                } else {
                    for (int i = 0; i < search.size(); i++) {
                        System.out.println("[" + (i + 1) + "] Artist name: " + search.get(i).getPerformer() +
                                ", Song title: " + search.get(i).getName() + ", Album Name: " + search.get(i).getAlbum());
                    }
                    System.out.println();
                    System.out.printf("Do you want to play this song? Enter Y/N   >> ");
                    Scanner songPlay = new Scanner(System.in);
                    String sp = songPlay.nextLine();
                    if (sp.equals("Y")) {
                        System.out.println("Playing song: " + search.get(0).getName());
                        System.out.println();
                    }

                    System.out.printf("Do you want to add this song into your song library? Enter Y/N   >> ");
                    Scanner answer = new Scanner(System.in);
                    String as = answer.nextLine();
                    if (as.equals("Y")) {
                        addSong(search.get(0));
                        System.out.println(search.get(0).toXML());
                    } else if (as.equals("N")) {
                        //prompt user what they want to do next
                    }
                }
            }
            /*
            else if (c1.equals("3")) {
                System.out.printf(">> Album title: ");
                Scanner album = new Scanner(System.in);
                String a2 = album.nextLine();
                searchSongWithArtist(a2);
            }
            */
            else {
                System.out.println("Cannot recognize instruction. Please try again.");
                // Edit here, prompt the user to try again.
            }

        } else if (num.equals("0")) {
            System.out.println("Music Manager exited");
            return;
        } else {
            System.out.println("Cannot recognize instruction. Please try again.");
            // Edit here, prompt the user to try again.
        }
    }
    private static boolean login(String id, String pw){
        Connection connection = null;
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:music.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            ResultSet rs = statement.executeQuery("select * from users");
            while (rs.next()) {
                // read the result set
                if (id.equals(rs.getString("name"))){
                    if (pw.equals(rs.getString("password"))) {
                        return true;
                    } else {
                        System.out.println("Wrong userID or password");
                        return false;
                    }
                }
            }
            System.out.println("Wrong userID or password");
            return false;
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
        return true;
    }


    protected static ArrayList<Song> searchSongWithArtist(String artist){
        ArrayList<Song> result = new ArrayList<>();
        System.out.println("Searching songs with artist name " + artist);
        String requestURL = "https://theaudiodb.com/api/v1/json/523532/track-top10.php?s=";
        StringBuilder response = new StringBuilder();
        URL u;
        try {
            u = new URL(requestURL + artist);
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL");
            return result;
        }
        try {
            URLConnection connection = u.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            int code = httpConnection.getResponseCode();

            String message = httpConnection.getResponseMessage();
            System.out.println(code + " " + message);
            if (code != HttpURLConnection.HTTP_OK) {
                return result;
            }
            InputStream instream = connection.getInputStream();
            Scanner in = new Scanner(instream);
            while (in.hasNextLine()) {
                response.append(in.nextLine());
            }
        } catch (IOException e) {
            System.out.println("Error reading response");
            return result;
        }
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(response.toString());
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray songs = (JSONArray)jsonObject.get("track"); // get the list of all artists returned.
            if (songs == null){
                return result;
            }
            for (int i = 0; i < 10; i++) {
                JSONObject song =(JSONObject) songs.get(i);  // I happen to know that the beatles is the first entry. Otherwise I'd iterate.
                String songTitle = (String)song.get("strTrack");
                String albumName = (String)song.get("strAlbum");
                String artistName = (String)song.get("strArtist");
                String genre = (String)song.get("strGenre");
                Song s = new Song(songTitle);
                s.setAlbum(new Album(albumName));
                s.setPerformer(new Artist(artistName));
                s.setGenre(genre);
                result.add(s);
            }
            System.out.println("Search completed.");
            return result;
        } catch(ParseException e) {
            System.out.println("Error parsing JSON");
            return result;
        }
    }

    protected static ArrayList<Song> searchSongWithArtistAndTitle(String artist,String song){
        System.out.println("searing song with artist name and song title: " + artist + ", " + song);
        ArrayList<Song> result = new ArrayList<>();
        String requestURL1 = "https://theaudiodb.com/api/v1/json/523532/searchtrack.php?s=";
        String requestURL2 = "&t=";
        StringBuilder response = new StringBuilder();
        URL u;
        try {
            u = new URL(requestURL1 + artist + requestURL2 + song);
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL");
            return result;
        }
        try {
            URLConnection connection = u.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            int code = httpConnection.getResponseCode();

            String message = httpConnection.getResponseMessage();
            System.out.println(code + " " + message);
            if (code != HttpURLConnection.HTTP_OK) {
                return result;
            }
            InputStream instream = connection.getInputStream();
            Scanner in = new Scanner(instream);
            while (in.hasNextLine()) {
                response.append(in.nextLine());
            }
        } catch (IOException e) {
            System.out.println("Error reading response");
            return result;
        }
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(response.toString());
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray songs = (JSONArray)jsonObject.get("track"); // get the list of all artists returned.
            if (songs == null){
                return result;
            }
            for (int i = 0; i < 1; i++) {
                JSONObject song1 =(JSONObject) songs.get(i);  // I happen to know that the beatles is the first entry. Otherwise I'd iterate.
                String songTitle = (String)song1.get("strTrack");
                String albumName = (String)song1.get("strAlbum");
                String artistName = (String)song1.get("strArtist");
                String genre = (String)song1.get("strGenre");
                Song s = new Song(songTitle);
                s.setAlbum(new Album(albumName));
                s.setPerformer(new Artist(artistName));
                s.setGenre(genre);
                result.add(s);
            }
            System.out.println("Search completed.");
            return result;
        } catch(ParseException e) {
            System.out.println("Error parsing JSON");
            return result;
        }
    }

    /*
    protected static ArrayList<Song> searchSongWithAlbum(String album){
        System.out.println("searching song with album name:" + album);
        ArrayList<Song> result = new ArrayList<>();
        return result;
    }
    */

    protected static void generatePlaylist(String s) {
        System.out.println("Generating Playlist " + s + " songs.");
        Connection connection = null;
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:music.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            if (s.equals("all")) {
                Playlist p = new Playlist();
                ResultSet rsSong1 = statement.executeQuery("select * from songs");
                while (rsSong1.next()) {
                    Song song = new Song(rsSong1.getString("name"));
                    p.addSong(song);
                }
                ArrayList<Integer> artists = new ArrayList<>();
                ResultSet rsSong2 = statement.executeQuery("select * from songs");
                while (rsSong2.next()) {
                    int artist = rsSong2.getInt("artist");
                    artists.add(artist);
                }
                ArrayList<Integer> albums = new ArrayList<>();
                ResultSet rsSong3 = statement.executeQuery("select * from songs");
                while (rsSong3.next()) {
                    int album = rsSong3.getInt("album");
                    albums.add(album);
                }
                for(int i = 0; i < p.getSongList().size(); i++){
                    ResultSet rsArtist = statement.executeQuery("select * from artists where id = " + artists.get(i));
                    while (rsArtist.next()) {
                        String artist = rsArtist.getString("name");
                        p.getSongList().get(i).setPerformer(new Artist(artist));
                    }
                    ResultSet rsAlbum = statement.executeQuery("select * from albums where id = " + albums.get(i));
                    while (rsAlbum.next()) {
                        String album = rsAlbum.getString("name");
                        p.getSongList().get(i).setAlbum(new Album(album));
                    }
                }
                System.out.println(p.toXML());
            } else if (s.equals("rock")){
                Playlist p = new Playlist();
                ResultSet rsSong1 = statement.executeQuery("select * from songs where genre = 'rock' ");
                while (rsSong1.next()) {
                    Song song = new Song(rsSong1.getString("name"));
                    p.addSong(song);
                }
                ArrayList<Integer> artists = new ArrayList<>();
                ResultSet rsSong2 = statement.executeQuery("select * from songs where genre = 'rock' ");
                while (rsSong2.next()) {
                    int artist = rsSong2.getInt("artist");
                    artists.add(artist);
                }
                ArrayList<Integer> albums = new ArrayList<>();
                ResultSet rsSong3 = statement.executeQuery("select * from songs where genre = 'rock' ");
                while (rsSong3.next()) {
                    int album = rsSong3.getInt("album");
                    albums.add(album);
                }
                for(int i = 0; i < p.getSongList().size(); i++){
                    ResultSet rsArtist = statement.executeQuery("select * from artists where id = " + artists.get(i));
                    while (rsArtist.next()) {
                        String artist = rsArtist.getString("name");
                        p.getSongList().get(i).setPerformer(new Artist(artist));
                    }
                    ResultSet rsAlbum = statement.executeQuery("select * from albums where id = " + albums.get(i));
                    while (rsAlbum.next()) {
                        String album = rsAlbum.getString("name");
                        p.getSongList().get(i).setAlbum(new Album(album));
                    }
                }
                System.out.println(p.toXML());

            } else if (s.equals("pop")){
                Playlist p = new Playlist();
                ResultSet rsSong1 = statement.executeQuery("select * from songs where genre = 'pop' ");
                while (rsSong1.next()) {
                    Song song = new Song(rsSong1.getString("name"));
                    p.addSong(song);
                }
                ArrayList<Integer> artists = new ArrayList<>();
                ResultSet rsSong2 = statement.executeQuery("select * from songs where genre = 'pop' ");
                while (rsSong2.next()) {
                    int artist = rsSong2.getInt("artist");
                    artists.add(artist);
                }
                ArrayList<Integer> albums = new ArrayList<>();
                ResultSet rsSong3 = statement.executeQuery("select * from songs where genre = 'pop' ");
                while (rsSong3.next()) {
                    int album = rsSong3.getInt("album");
                    albums.add(album);
                }
                for(int i = 0; i < p.getSongList().size(); i++){
                    ResultSet rsArtist = statement.executeQuery("select * from artists where id = " + artists.get(i));
                    while (rsArtist.next()) {
                        String artist = rsArtist.getString("name");
                        p.getSongList().get(i).setPerformer(new Artist(artist));
                    }
                    ResultSet rsAlbum = statement.executeQuery("select * from albums where id = " + albums.get(i));
                    while (rsAlbum.next()) {
                        String album = rsAlbum.getString("name");
                        p.getSongList().get(i).setAlbum(new Album(album));
                    }
                }
                System.out.println(p.toXML());
            }

        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
    }
    protected static void addSong(Song s) {
        Connection connection = null;
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:music.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            int song = -1;
            ResultSet rs = statement.executeQuery("select * from songs where name = '" + s.getName() + "'");
            while (rs.next()) {
                // read the result set
                song = rs.getInt("id");
                System.out.println("The song is already in the database.");
            }

            ResultSet rs1 = statement.executeQuery("select count(*) from songs");
            if (song == -1) {
                song = rs1.getInt(1) + 1;
                statement.executeUpdate("insert into songs (id, name) values (" + song + ", '" + s.getName() + "')");
                System.out.println("The song has been added into the database");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main( String[] args )
    {
        startApp();
    }
}
