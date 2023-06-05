package repositories;

import database.DatabaseConfiguration;
import models.Song;
import models.SongQueue;

import java.sql.*;
import java.util.*;

public class SongQueueRepo extends PlaylistsRepo {
    private static final Connection connection = DatabaseConfiguration.getDatabaseConnection();
    public SongQueueRepo() {}

    public static SongQueue getSongQueueByUsername(String username) throws SQLException {
        String query = "SELECT * FROM Playlist WHERE playlistName like ? AND username = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "SongQueue%");
            statement.setString(2, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                SongQueue songQueue = new SongQueue();
                songQueue.setPlaylistName(resultSet.getString("playlistName"));
                songQueue.setDescription(resultSet.getString("description"));

                List<Song> songs = SongRepo.getSongsByPlaylist(resultSet.getString("playlistName"));
                songQueue.setSongs(songs);

                return songQueue;
            }
        }
        return null;
    }
    public static void addSongInQueueForUser(String username,Song song)
    {
        String q="select * from playlist where username=? and playlistName like ?;";

        try (PreparedStatement statement = connection.prepareStatement(q)) {
            statement.setString(1, username);
            statement.setString(2, "SongQueue%");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                String songQueueName=resultSet.getString("playlistName");
                PlaylistsRepo.addSongInPlaylistByPlaylistName(songQueueName,song);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



}
