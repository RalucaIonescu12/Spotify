package repositories;

import database.DatabaseConfiguration;
import models.Album;
import models.Playlist;
import models.Song;
import services.AuditService;

import java.io.IOException;
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
    public static void deleteSongFromPlaylist(String playlistName, String songName)
    {
        String q="Delete from PlaylistsSongs where lower (playlistName) = ? and songID= ? ";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(q);
            preparedStatement.setString(1,playlistName.toLowerCase());

            Integer songID=SongRepo.getSongIdByTitle(songName);

            preparedStatement.setInt(2,songID);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void addSongInPlaylistByPlaylistName(String playlistName, Song song)
    {
        try
        {

            String query = "INSERT INTO PlaylistsSongs (playlistName,songID) " +
                    "VALUES (?, ?)";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,playlistName);
            statement.setInt(2, song.getSongId());

            statement.executeUpdate();


        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Error when adding Song in PlaylistsSongs!!");

        }

    }

}
