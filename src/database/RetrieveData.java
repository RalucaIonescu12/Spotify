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
        String filePath = "C:\\FACULTATE\\ANUL II\\An II sem 2\\PAO\\create_tables.sql"; // Replace with the actual path to your SQL file

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
            connection.close(); //?
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error at creating tables!!");
        }


    }

    public void insertIntoDatabase() {
        ///iau din baza de date toate
        ///pt fiecare repo fac cate un addData care ia cu selecgt * tot din baza de date si le pun in repo

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

    public void loadData()
    {

    }
//
//    public void addRows() {
//
//        File file = new File("Files/QuerysAddRows.txt");
//        try {
//
//            BufferedReader br = new BufferedReader(new FileReader(file));
//            String insertRowSql;
//
//            while ((insertRowSql = br.readLine()) != null){
//
//                Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
//                RepositoryHelper repositoryHelper = RepositoryHelper.getRepositoryHelper();
//
//                try {
//                    repositoryHelper.executeUpdateSql(databaseConnection, insertRowSql);
//                }  catch (SQLException e) {
//                    e.printStackTrace();
//                    System.out.println("Error when I try insert rows into the tables !");
//                }
//            }
//        }
//        catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public void deleteAllRows(){
//        File file = new File("Files/QuerysDeleteAllRows.txt");
//        try {
//            BufferedReader br = new BufferedReader(new FileReader(file));
//            String deleteRowsSql;
//
//            while ((deleteRowsSql = br.readLine()) != null){
//                Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
//                RepositoryHelper repositoryHelper = RepositoryHelper.getRepositoryHelper();
//                repositoryHelper.executeUpdateSql(databaseConnection, deleteRowsSql);
//            }
//            System.out.println("Delete information successfully!");
//        }
//        catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        catch (SQLException e) {
//            //e.printStackTrace();
//            System.out.println("Error when I try to delete all rows from tables !");
//        }
//    }
//
//    public void dropAllTables() {
//        File file = new File("Files/QuerysDropAllTables.txt");
//        try {
//            BufferedReader br = new BufferedReader(new FileReader(file));
//            String dropTableSql;
//
//            while ((dropTableSql = br.readLine()) != null) {
//                Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
//                RepositoryHelper repositoryHelper = RepositoryHelper.getRepositoryHelper();
//
//                repositoryHelper.executeSql(databaseConnection, dropTableSql);
//            }
//        }
//        catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println("Error when I try create the tables for the database !");
//        }
//
//    }
//
//    public void displayTable() {
//        String selectSql="";
//        System.out.print("Give the name of the table do you want to show:");
//        Scanner var= new Scanner(System.in);
//        String tableName= var.nextLine();
//
//        if (tableName.equalsIgnoreCase("burgers"))
//            selectSql ="SELECT * FROM burgers";
//        else if (tableName.equalsIgnoreCase("sweets"))
//            selectSql ="SELECT * FROM sweets";
//        else if (tableName.equalsIgnoreCase("drinks"))
//            selectSql ="SELECT * FROM drinks";
//        else if (tableName.equalsIgnoreCase("rfoods"))
//            selectSql ="SELECT * FROM rfoods";
//
//
//        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
//        RepositoryHelper repositoryHelper = RepositoryHelper.getRepositoryHelper();
//
//        try {
//            ResultSet resultSet = repositoryHelper.executeQuerySql(databaseConnection, selectSql);
//
//            while (resultSet.next()) {
//                ResultSetMetaData rsmd = resultSet.getMetaData();
//                int columnsNumber = rsmd.getColumnCount();
//
//                for(int i=2; i<=columnsNumber;i++) //coloana 1 este id-ul
//                    System.out.print(resultSet.getString(i)+" ");
//                System.out.println();
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

}
