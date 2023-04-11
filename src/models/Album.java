package models;

import java.util.ArrayList;

public class Album
{
    private String title, artist,genre;
    private ArrayList<Song> songs;
    private String releaseDate;
    public Album()
    {
        this.title = "";
        this.artist = "";
        this.songs = new ArrayList<>();
        this.releaseDate = "";
        this.genre = " ";
    }
    public Album(String title, String artist, ArrayList<Song> songs, String releaseDate,String genre)
    {
        this.title = title;
        this.artist = artist;
        this.songs = new ArrayList<>(songs);
        this.releaseDate = releaseDate;
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setSongs(ArrayList<Song> songs) {

        this.songs = new ArrayList<>(songs);
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }


}
