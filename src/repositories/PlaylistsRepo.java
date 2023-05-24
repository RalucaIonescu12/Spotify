package repositories;

import database.DatabaseConfiguration;
import models.Album;
import models.Playlist;
import models.Song;

import java.sql.*;
import java.util.*;

public class PlaylistsRepo {

    private static Connection connection = DatabaseConfiguration.getDatabaseConnection();
    public PlaylistsRepo(){}
    public static List<Playlist> addData()
    {
        String selectSql = "SELECT * FROM Playlist;";

        List<Playlist> playlists=new ArrayList<Playlist>();

        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(selectSql);

            while (resultSet.next())
            {

                String playlistName = resultSet.getString("playlistName");
                String description = resultSet.getString("description");
                Playlist playlist =new Playlist(playlistName,description);
                List<Song> songs = SongRepo.getSongsByPlaylist(playlistName);
                playlist.setSongs(songs);
                playlists.add(playlist);

            }

            return playlists;
        } catch (SQLException e) {
            e.printStackTrace();
            return null ;
        }
    }

    public static List<Playlist> getPlaylistsByUser(String username) {
        String selectSql = "SELECT * FROM Playlist WHERE username = ?;";
        List<Playlist> playlists = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(selectSql)) {
            stmt.setString(1, username);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next())
            {
                String playlistName = resultSet.getString("playlistName");
                String description = resultSet.getString("description");
                Playlist playlist = new Playlist(playlistName,description);
                List<Song> songs = SongRepo.getSongsByPlaylist(playlistName);
                playlist.setSongs(songs);
                playlists.add(playlist);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error at getPlaylistsByUsername.");
        }

        return playlists;
    }


}
