package repositories;

import database.DatabaseConfiguration;
import models.Album;
import models.Song;
import models.SongQueue;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class SongRepo {
    private static Connection connection = DatabaseConfiguration.getDatabaseConnection();
    public SongRepo (){}


    public static List<Song> addData() {
        String selectSql = "SELECT * FROM Song;";
        List<Song> songs = new ArrayList<>();

        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(selectSql);

            while (resultSet.next()) {

                Song song=new Song();
                Integer songID= resultSet.getInt("songID");
                String title = resultSet.getString("title");
                String artist = resultSet.getString("artist");
                String genre = resultSet.getString("genre");
                String albumTitle = resultSet.getString("albumTitle");
                String duration = resultSet.getString("duration");
                if (resultSet.getString("features") != null)
                {
                    String featuress = resultSet.getString("features");
                    Set<String> features = new HashSet<>();
                    features.addAll(Arrays.asList(featuress.split(", ")));

                    song.setSongId(songID);
                    song.setDuration(duration);
                    song.setTitle(title);
                    song.setGenre(genre);
                    song.setArtist(artist);
                    song.setAlbum(albumTitle);
                    song.setFeatures(features);
                }
                else
                {
                    song.setSongId(songID);
                    song.setDuration(duration);
                    song.setTitle(title);
                    song.setGenre(genre);
                    song.setArtist(artist);
                    song.setAlbum(albumTitle);
                }
                songs.add(song);
            }
            return songs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
    public static List<Song> getSongsByPlaylist(String playlistName) {
        String selectSql = "SELECT s.* FROM PlaylistsSongs ps, Song s where ps.songID=s.songID and ps.playlistName = ?;";
        List<Song> songs = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(selectSql)) {
            stmt.setString(1, playlistName);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next())
            {
                Song song=new Song();
                Integer songID=resultSet.getInt("songID");
                String title = resultSet.getString("title");
                String artist = resultSet.getString("artist");
                String genre = resultSet.getString("genre");
                String duration = resultSet.getString("duration");
                String albumTitle = resultSet.getString("albumTitle");
                if(resultSet.getString("features")!=null)
                {
                    String featuress =resultSet.getString("features");
                    Set<String> features=new HashSet<>();
                    features.addAll(Arrays.asList(featuress.split(", ")));
                    song.setDuration(duration);
                    song.setSongId(songID);
                    song.setTitle(title);
                    song.setGenre(genre);
                    song.setArtist(artist);
                    song.setAlbum(albumTitle);
                    song.setFeatures(features);
                }
                else {
                    song.setSongId(songID);
                    song.setDuration(duration);
                    song.setTitle(title);
                    song.setGenre(genre);
                    song.setArtist(artist);
                    song.setAlbum(albumTitle);
                }
                songs.add(song);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return songs;
    }

    public static List<Song> getSongsByAlbum(Integer albumID) {
        String selectSql = "SELECT * FROM Song WHERE albumID = ?;";
        List<Song> songs = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(selectSql)) {
            stmt.setInt(1, albumID);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next())
            {
                Song song=new Song();
                Integer songID=resultSet.getInt("songID");
                String title = resultSet.getString("title");
                String artist = resultSet.getString("artist");
                String genre = resultSet.getString("genre");
                String albumTitle = resultSet.getString("albumTitle");
                String duration = resultSet.getString("duration");
                if(resultSet.getString("features")!=null)
                {
                    String featuress =resultSet.getString("features");
                    Set<String> features=new HashSet<>();
                    features.addAll(Arrays.asList(featuress.split(", ")));
                    song.setDuration(duration);
                    song.setSongId(songID);
                    song.setTitle(title);
                    song.setGenre(genre);
                    song.setArtist(artist);
                    song.setAlbum(albumTitle);
                    song.setFeatures(features);
                    song.setAlbumID(albumID);
                }
                else {
                    song.setSongId(songID);
                    song.setDuration(duration);
                    song.setTitle(title);
                    song.setGenre(genre);
                    song.setArtist(artist);
                    song.setAlbum(albumTitle);
                    song.setAlbumID(albumID);

                }
                songs.add(song);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return songs;
    }
    public static Integer getSongIdByTitle(String songName)
    {
        String q="Select * from song where LOWER( title ) = ? ; ";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(q);
            preparedStatement.setString(1,songName.toLowerCase());
            ResultSet resultSet=preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("songID");
            } else {
                return null; // or handle the case when no matching song is found
            }


        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
    public static void addSong(Song song) {
        try
        {
            String query = "INSERT INTO Song (songID, title, genre, artist, features, albumTitle, duration, albumID) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, song.getSongId());
            statement.setString(2, song.getTitle());
            statement.setString(3, song.getGenre());
            statement.setString(4, song.getArtist());
            if(!song.getFeatures().isEmpty())
            {
                statement.setString(5, String.join(", ", song.getFeatures()));
            }
            else {
                statement.setNull(5,java.sql.Types.VARCHAR);
            }
            statement.setString(6, song.getAlbum());
            statement.setString(7, song.getDuration());

            statement.setInt(8, song.getAlbumID());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error when adding Song!!");
        }
    }
    public static void addSongIntoPlaylistsSongs(Song song,String playlistName)
    {
        try
        {
            String query = "INSERT INTO PlaylistsSongs (playlistName,songID) " +
                    "VALUES (?, ?)";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,playlistName);
            statement.setInt(2, song.getSongId());

            statement.executeUpdate();

        } catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Error when adding Song in PlaylistsSongs!!");
        }

    }

}
