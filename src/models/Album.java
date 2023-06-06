package models;

import database.DatabaseConfiguration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Album
{
    private static Connection connection = DatabaseConfiguration.getDatabaseConnection();
    private String title, artist,genre;
    private List<Song> songs;
    private String releaseDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return Objects.equals(title, album.title) && Objects.equals(artist, album.artist) && Objects.equals(genre, album.genre) && Objects.equals(songs, album.songs) && Objects.equals(releaseDate, album.releaseDate) && Objects.equals(albumID, album.albumID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, artist, genre, songs, releaseDate, albumID);
    }

    Integer albumID;
    private static Integer numAlbumsAdded ;

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



    public Album()
    {
        this.title = "";
        this.artist = "";
        this.songs = new ArrayList<>();
        this.releaseDate = "";
        this.genre = " ";
    }
    public Album(String title, String artist, String releaseDate,String genre,Integer albumID) {
        this.title = title;
        this.artist = artist;
        this.songs = new ArrayList<>();
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.albumID = albumID;
    }
    public Album(String title, String artist, List<Song> songs, String releaseDate,String genre)
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
    public static void countNumberAlbums()
    {
        String q="Select * from album order by albumID desc";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(q);
            ResultSet resultSet=preparedStatement.executeQuery();

            int nr = 0;
            if (resultSet.next())
            {
                nr= resultSet.getInt("albumID");
            }
            numAlbumsAdded = nr;

        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
    public static Integer getLastAlbumID(){
        numAlbumsAdded=numAlbumsAdded+1;
        return numAlbumsAdded;
    }

}
