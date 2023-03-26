package models;

import java.util.HashSet;
import java.util.Set;

public class Song
{
    private String title,genre,artist;
    private Set<Song> features= new HashSet<>();
    private Integer duration;

    public Set<Song> getFeatures() {
        return features;
    }

    public Song(String title, String genre, String artist, Integer duration) {
        this.title = title;
        this.genre = genre;
        this.artist = artist;
        this.duration = duration;
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

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getGenre() {
        return genre;
    }

    public String getArtist() {
        return artist;
    }

    public Integer getDuration() {
        return duration;
    }
}
