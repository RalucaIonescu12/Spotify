package models;

import java.util.HashSet;
import java.util.Set;

public class Song
{
    private String title,genre,artist;
    private Set<Song> features= new HashSet<>();
    private String duration;
    private String album;

    public Set<Song> getFeatures() {
        return features;
    }

    public Song(String title, String genre, String artist,String  duration,String album) {
        this.title = title;
        this.genre = genre;
        this.artist = artist;
        this.duration = duration;
        this.album= album;
    }

    public void setFeatures(Set<Song> features) {
        this.features = features;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setDuration(String  duration) {
        this.duration = duration;
    }

    public String getGenre() {
        return genre;
    }

    public String getArtist() {
        return artist;
    }

    public String  getDuration() {
        return duration;
    }
}
