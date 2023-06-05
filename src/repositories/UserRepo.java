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
                List<Playlist> playlists;
                playlists=PlaylistsRepo.getPlaylistsByUser(username);
                User user = new User(username, firstname, lastname,password, email, subscriptionType);
                SongQueue songQueue =SongQueueRepo.getSongQueueByUsername(username);
                user.setPlaylists(playlists);
                System.out.println("Pt userul "+user.getUsername()+" sunt "+ user.getPlaylists().size()+" playlisturi!");
                user.setSongQueue(songQueue);
                users.add(user);
            }
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
    public static void addPlaylistToUser(String username, Playlist playlist)
    {
        try {
            String insertPlaylistSql = "INSERT INTO Playlist (playlistName, username, description) VALUES (?, ?, ?)";
            PreparedStatement playlistStatement = connection.prepareStatement(insertPlaylistSql);
            playlistStatement.setString(1, playlist.getPlaylistName());
            playlistStatement.setString(2, username);
            if(playlist.getDescription()!=null)
                playlistStatement.setString(3, playlist.getDescription());
            else
                playlistStatement.setNull(3,  java.sql.Types.VARCHAR);
            playlistStatement.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Error at addPlaylistToUser");
        }
    }


    public static void addUser(User user)
    {

    }
}
