package models;

import java.util.ArrayList;

public class Album
{
    private String Title, artist;
    private ArrayList<Song> songs;
    private String releaseDate;

    public String getTitle() {
        return Title;
    }

    public String getArtist() {
        return artist;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
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

    public Album(String title, String artist, ArrayList<Song> songs, String releaseDate) {
        Title = title;
        this.artist = artist;
        this.songs = songs;
        this.releaseDate = releaseDate;
    }
}
