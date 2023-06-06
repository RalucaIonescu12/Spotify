package repositories;

import database.DatabaseConfiguration;
import models.Playlist;
import models.Song;
import models.SongQueue;
import models.User;
import services.AuditService;

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
                List<Playlist> playlists=new ArrayList<>();
                playlists=PlaylistsRepo.getPlaylistsByUser(username);
                User user = new User(username, firstname, lastname,password, email, subscriptionType);
                SongQueue songQueue =SongQueueRepo.getSongQueueByUsername(username);
                user.setPlaylists(playlists);
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
        try {
            String insertUser = "INSERT INTO Userr (username, firstname , lastname,password,email,subscriptionType) " +
                    "VALUES (?, ?, ?, ?, ? , ?)";
            PreparedStatement playlistStatement = connection.prepareStatement(insertUser);
            playlistStatement.setString(1, user.getUsername());
            playlistStatement.setString(2, user.getFirstName());
            playlistStatement.setString(3,  user.getLastName());
            playlistStatement.setString(4,  user.getPassword());
            playlistStatement.setString(5,  user.getEmail());
            playlistStatement.setString(6,  user.getSubscriptionType());

            playlistStatement.executeUpdate();

            ///create the user songqueue

            String nume="SongQueue".concat(user.getLastName().substring(0,1)).concat(user.getFirstName().substring(0,1));
            String description=user.getLastName().concat(" ").concat(user.getFirstName()).concat( "'s personal SongQueue!");

            String insertSQ = "INSERT INTO Playlist (playlistName, username, description) " +
                    "VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(insertSQ);
            statement.setString(1, nume);
            statement.setString(2, user.getUsername());
            statement.setString(3, description);

            statement.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Error at addUser");
        }

        //addPlaylisttouser
    }
}
