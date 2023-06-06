package models;

import database.DatabaseConfiguration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Song
{
    private static Integer numSongsAdded ;
    private String title,genre,artist;
    private static Connection connection = DatabaseConfiguration.getDatabaseConnection();
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return Objects.equals(title, song.title) && Objects.equals(genre, song.genre) && Objects.equals(artist, song.artist) && Objects.equals(features, song.features) && Objects.equals(duration, song.duration) && Objects.equals(songId, song.songId) && Objects.equals(album, song.album) && Objects.equals(albumID, song.albumID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, genre, artist, features, duration, songId, album, albumID);
    }

    private Set<String> features=new HashSet<>();
    private String duration;
    private Integer songId;
    private String album;
    private Integer albumID;
//    static{
//        numSongsAdded++;
//    }
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
    public static Integer setNumSongsAdded(){
        numSongsAdded=numSongsAdded+1;
        return numSongsAdded;
    }
    public static void countNumberSongs()
    {
        String q="Select * from song order by songID desc";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(q);
            ResultSet resultSet=preparedStatement.executeQuery();

            int nr = 0;
            if (resultSet.next()) 
            {
                nr= resultSet.getInt("songID");
            }
            numSongsAdded = nr;

        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }


}
