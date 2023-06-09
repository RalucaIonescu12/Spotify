package repositories;

import database.DatabaseConfiguration;
import models.Album;
import models.Playlist;
import models.Song;
import models.User;
import services.AuditService;

import java.io.IOException;
import java.sql.*;
import java.util.*;

public class AlbumRepo {
    private static final Connection connection = DatabaseConfiguration.getDatabaseConnection();

    public AlbumRepo(){}

    public static Set<Album> addData()
    {
        String selectSql = "SELECT * FROM ALBUM;";

        Set<Album> albums=new HashSet<>();

        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(selectSql);

            while (resultSet.next())
            {

                Integer albumID = resultSet.getInt("albumID");
                String title = resultSet.getString("title");
                String artist = resultSet.getString("artist");
                String genre = resultSet.getString("genre");
                String releaseDate = resultSet.getString("releaseDate");
                Album album =new Album(title,artist,releaseDate,genre,albumID);
                List<Song> songs = SongRepo.getSongsByAlbum(albumID);
                album.setSongs(songs);
                albums.add(album);

            }

            return albums;

        } catch (SQLException e) {
            e.printStackTrace();
            return null ;
        }
    }
    public static void addAlbum(Album album) {
        try
        {
            String query = "INSERT INTO Album (albumID, title, artist, genre, releaseDate) " +
                    "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, album.getAlbumID());
            statement.setString(2, album.getTitle());
            statement.setString(3, album.getArtist());
            statement.setString(4, album.getGenre());
            statement.setString(5, album.getReleaseDate());
            statement.executeUpdate();
            System.out.println("Album added successfully!");

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

}
