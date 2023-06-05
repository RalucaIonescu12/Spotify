package models;

import java.util.HashSet;
import java.util.Set;

public class Song
{
    private static Integer numSongsAdded = 16;
    private String title,genre,artist;
    private Set<String> features=new HashSet<>();
    private String duration;
    private Integer songId;
    private String album;
    private Integer albumID;

    public Integer getAlbumID() {
        return albumID;
    }

    public void setAlbumID(Integer albumID) {
        this.albumID = albumID;
    }

    public Set<String> getFeatures() {
        return features;
    }

    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
    }




    public Song()
    {
        this.title = "";
        this.genre = null;
        this.artist = null;
        this.duration = null;
        this.album= null;
        numSongsAdded++;
        this.songId=numSongsAdded;
        this.albumID=-1;
    }
    public Song(Song song)
    {
        this.title = song.title;
        this.genre = song.genre;
        this.artist =  song.artist;
        this.duration =  song.duration;
        this.album=  song.album;
        this.features=song.features;
        this.songId=song.songId;
        this.albumID=song.albumID;
    }
//
//    public Song(String title, String genre, String artist,String duration, String album) {
//        this.title = title;
//        this.genre = genre;
//        this.artist = artist;
//        this.duration = duration;
//        this.album = album;
//        this.songId = numSongsAdded;
//        this.albumID=null;
//    }
    public Song(String title, String genre, String artist,String duration, String album, Set<String> features,Integer songId,Integer albumID) {
        this.title = title;
        this.genre = genre;
        this.artist = artist;
        this.duration = duration;
        this.album= album;
        this.features= new HashSet<>(features);
        this.songId=songId;
        this.albumID=albumID;
    }

    public void setFeatures(Set<String> features) {
        this.features = new HashSet<>(features);
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
