package repositories;

import database.DatabaseConfiguration;
import models.Playlist;
import models.Song;
import models.SongQueue;
import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepo {

    private static Connection connection = DatabaseConfiguration.getDatabaseConnection();

    public UserRepo(){}

    public static List<User> addData()
    {
        String selectSql = "SELECT * FROM USERR;";

        List<User> users=new ArrayList<User>();


        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(selectSql);

            while (resultSet.next())
            {

                String username = resultSet.getString("username");
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String subscriptionType=resultSet.getString("subscriptionType");
                List<Playlist> playlists = PlaylistsRepo.getPlaylistsByUser(username);
                User user = new User(username, firstname, lastname,password, email, subscriptionType);
                SongQueue songQueue = SongQueueRepo.getSongQueueByUsername(username);
                user.setPlaylists(playlists);
                user.setSongQueue(songQueue);
                users.add(user);
            }
            System.out.println("~User data retrieved.");
            return users;

        } catch (SQLException e) {
            e.printStackTrace();
            return null ;
        }
    }
    public static User getUserByUsername(String username) {
        String selectSql = "SELECT * FROM Userr WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(selectSql))
        {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String subscriptionType = resultSet.getString("subscriptionType");
                List<Playlist> playlists = PlaylistsRepo.getPlaylistsByUser(username);
                User user = new User(username, firstname, lastname, password, email, subscriptionType);
                user.setPlaylists(playlists);
                SongQueue songQueue = SongQueueRepo.getSongQueueByUsername(username);
                user.setSongQueue(songQueue);

                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error at getUSerByUsername.");
        }
        return null;
    }







//    public static boolean addSongToQueueByUsername(String username, Song song) {
//        try {
//            SongQueue songQueue = SongQueueRepo.getSongQueueByUsername(username);
//            if (songQueue != null)
//            {
//                List<Song> songs = songQueue.getSongs();
//                songs.add(song);
//                songQueue.setSongs(songs);
//                updateSongQueue(username, songQueue);
//
//                System.out.println("Song added to the queue successfully for user: " + username);
//                return true;
//            }
//            else {
//                System.out.println("Song queue not found for user: " + username);
//                return false;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println("Error at function addSongToSongQueue.");
//            return false;
//        }
//    }
//    public static void updateSongQueue(String username, SongQueue songQueue) {
//        try {
//            String updateSql = "UPDATE Playlist SET description = ? WHERE playlistName = ? AND username = ?";
//
//            PreparedStatement statement = connection.prepareStatement(updateSql);
//            statement.setString(1, songQueue.getDescription());
//            statement.setString(2, "SongQueue");
//            statement.setString(3, username);
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    public static void addPlaylistToUser(String username, Playlist playlist) {
        try {
            String insertPlaylistSql = "INSERT INTO Playlist (playlistName, username, description) VALUES (?, ?, ?)";
            PreparedStatement playlistStatement = connection.prepareStatement(insertPlaylistSql);
            playlistStatement.setString(1, playlist.getPlaylistName());
            playlistStatement.setString(2, username);
            playlistStatement.setString(3, playlist.getDescription());
            playlistStatement.executeUpdate();

            for(Song song:playlist.getSongs())
            {
                SongRepo.addSongIntoPlaylistsSongs(song,playlist.getPlaylistName());
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error at addPlaylistToUser");
        }
    }


    public static void addUser(User user)
    {

    }
}
