package database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//TODO:addSong,addAlbum,deletedongfromplaylist

public class RetrieveData {
    public void createTables() {
        String filePath = "C:\\FACULTATE\\ANUL II\\An II sem 2\\PAO\\create_tables.sql";

        try {
            Connection connection = DatabaseConfiguration.getDatabaseConnection();

            Statement statement = connection.createStatement();

            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            StringBuilder sqlStatements = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sqlStatements.append(line);
            }
            reader.close();

            String[] queries = sqlStatements.toString().split(";");

            for (String query : queries) {
                statement.executeUpdate(query.trim());
            }

            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error at creating tables!!");
        }


    }

    public void insertIntoDatabase() {

        ///populate database using the addRows sql file
        String sqlFilePath = "C:\\FACULTATE\\ANUL II\\An II sem 2\\PAO\\addRows.sql";

        try {
            Connection connection = DatabaseConfiguration.getDatabaseConnection();

            Statement statement = connection.createStatement();

            BufferedReader reader = new BufferedReader(new FileReader(sqlFilePath));
            StringBuilder sqlStatements = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sqlStatements.append(line);
            }
            reader.close();

            String[] queries = sqlStatements.toString().split(";");

            for (String query : queries) {
                statement.executeUpdate(query.trim());
            }

            statement.close();
            connection.close(); //?
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error at inserting into tables!!");
        }
    }


}
