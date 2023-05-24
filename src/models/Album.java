package models;

import java.util.ArrayList;
import java.util.List;

public class Album
{
    private String title, artist,genre;
    private List<Song> songs;

    public Integer getAlbumID() {
        return albumID;
    }

    public void setAlbumID(Integer albumID) {
        this.albumID = albumID;
    }

    public static Integer getNumAlbumsAdded() {
        return numAlbumsAdded;
    }

    public static void setNumAlbumsAdded(Integer numAlbumsAdded) {
        Album.numAlbumsAdded = numAlbumsAdded;
    }

    private String releaseDate;
    Integer albumID;
    private static Integer numAlbumsAdded = 4;

    public Album()
    {
        this.title = "";
        this.artist = "";
        this.songs = new ArrayList<>();
        this.releaseDate = "";
        this.genre = " ";
        numAlbumsAdded++;
    }
    public Album(String title, String artist, String releaseDate,String genre)
    {
        this.title = title;
        this.artist = artist;
        this.songs = new ArrayList<>();
        this.releaseDate = releaseDate;
        this.genre = genre;

        numAlbumsAdded++;
    }
    public Album(String title, String artist, List<Song> songs, String releaseDate,String genre)
    {
        this.title = title;
        this.artist = artist;
        this.songs = new ArrayList<>(songs);
        this.releaseDate = releaseDate;
        this.genre = genre;

        numAlbumsAdded++;
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

    public void setSongs(List<Song> songs) {

        this.songs = new ArrayList<>(songs);
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public List<Song> getSongs() {
        return songs;
    }


}
