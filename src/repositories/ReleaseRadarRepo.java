package repositories;

import database.DatabaseConfiguration;
import models.Album;
import models.Playlist;
import models.ReleaseRadar;
import models.Song;
import services.AuditService;

import java.io.IOException;
import java.sql.*;
import java.util.*;

public class ReleaseRadarRepo {

    private static Connection connection = DatabaseConfiguration.getDatabaseConnection();

    public ReleaseRadarRepo(){}

    public static void setNumSongsAddedToZero()
    {
        String query2 = "Update ReleaseRadar set numSongsAdded = 0 where playlistname='ReleaseRadar' ";
        PreparedStatement statement2 = null;
        try {
            statement2 = connection.prepareStatement(query2);
            statement2.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void verifySongNumberInRR(Song song)
    {
        int verif=verifyIfSongAlreadyInReleaseRada(song.getSongId());

        if(verif==1) {

            PreparedStatement verificareNumSongs = null;
            try {
                verificareNumSongs = connection.prepareStatement("Select * from ReleaseRadar where playlistName='ReleaseRadar'");

                PreparedStatement countP = connection.prepareStatement("Select count(*) nr from playlistssongs where playlistName='ReleaseRadar'");
                ResultSet resultSet=countP.executeQuery();
                int count;
                if(resultSet.next())
                    count=resultSet.getInt("nr");
                else
                    count=0;


                ResultSet numSongs = verificareNumSongs.executeQuery();
                while (numSongs.next()) {
                    int nr = numSongs.getInt("numSongsAdded");
                    if (count<15) addSongInRR(song, nr);
                    else {
                        deleteOneSongInRR();
                        addSongInRR(song, nr);
                    }

                }


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            incrementNumSongsAddedInRR();
        }
    }

    public static int verifyIfSongAlreadyInReleaseRada(int SongId){

        PreparedStatement verificareNumSongs = null;
        try {
            verificareNumSongs = connection.prepareStatement("Select * from PlaylistsSongs where playlistName='ReleaseRadar' and songID= ? ");
            verificareNumSongs.setInt(1,SongId);
            ResultSet numSongs = verificareNumSongs.executeQuery();
            if (numSongs.next())
            {
              return 0;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ///nu e deja
        return 1;
    }
    public static void deleteOneSongInRR()
    {

        int firstSongID=getIdOfFirstSong();
        String q="Delete from PlaylistsSongs where playlistName = ? and songID= ? ";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(q);
            preparedStatement.setString(1, "ReleaseRadar");
            preparedStatement.setInt(2,firstSongID);

            int d=preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("eroare la delete");
            throw new RuntimeException(e);

        }
    }
    public static void addSongInRR(Song song,int nr)
    {
        try
        {
            String query = "INSERT INTO PlaylistsSongs (playlistName,songID) " +
                    "VALUES (?, ?)";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,"ReleaseRadar");
            statement.setInt(2, song.getSongId());


            int d=  statement.executeUpdate();

            if(nr<15)
            {
                incrementNumSongsAddedInRR();
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Error when adding Song ReleaseRadar!");
        }

    }
    public static int getIdOfFirstSong()
    {
        String selectSql = "SELECT * FROM PlaylistsSongs WHERE playlistname = ?;";

        try (PreparedStatement stmt = connection.prepareStatement(selectSql)) {
            stmt.setString(1, "ReleaseRadar");
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next())
            {

                int id= resultSet.getInt("SongID");
                return id;
            }
            else return -2;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error at getIdOfSongInRR.");
        }
        return -1;
    }

    public static void showReleaseRadar()
    {
        System.out.println("--------RELEASE RADAR----------");
        String selectSql = "SELECT s.title,s.genre,s.artist,s.features,s.albumTitle,s.duration FROM playlistsSongs ps,song s where s.songID=ps.songID and ps.playlistname='ReleaseRadar';";
        List<Song>songs=new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(selectSql)) {
            ResultSet resultSet = stmt.executeQuery();
            int i=0;
            while (resultSet.next())
            {
                String title = resultSet.getString("title");
                String artist=resultSet.getString("artist");
                String genre = resultSet.getString("genre");
                String features = resultSet.getString("features");
                String albumTitle = resultSet.getString("albumTitle");
                String duration = resultSet.getString("duration");
                if (features==null)
                    System.out.println("         " + i + "." + title + " - " + artist + " - " +duration);
                else
                {
                    System.out.print("         " + i +"." + title + " - "+ artist+",  feat. " );


                    for(String f:features.split(", "))
                        System.out.print(f + ", " );

                    System.out.println(" - " + duration);
                }

                i += 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error at showreleaseRadar.");
        }

        System.out.println("\n~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");

    }
    static void incrementNumSongsAddedInRR()
    {

        try {
            PreparedStatement verificareNumSongs = connection.prepareStatement("Select * from ReleaseRadar where playlistName='ReleaseRadar'");
            ResultSet numSongs = verificareNumSongs.executeQuery();
            while (numSongs.next())
            {
                int nr = numSongs.getInt("numSongsAdded");
                if (nr < 15)
                {
                    String query2 = "Update ReleaseRadar set numSongsAdded = numSongsAdded+1 where playlistname='ReleaseRadar' ";
                    PreparedStatement statement2 = null;
                    try {
                        statement2 = connection.prepareStatement(query2);
                        statement2.executeUpdate();

                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
