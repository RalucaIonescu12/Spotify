package models;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Spotify
{
    private static ArrayList<Song> songs;
    private static ArrayList<User> users;
    private static ArrayList<Album> albums ;
    private Spotify(){
        songs = new ArrayList<>();
        users = new ArrayList<>();
        albums= new ArrayList<>();
    }
    private static Spotify instance;
    public static Spotify getInstance()
    {
        if (instance == null) {
            instance = new Spotify();
        }
        return instance;
    }

    public static void setAlbums(ArrayList<Album> albums) {
        Spotify.albums = albums;
    }

    public void addUser(User user)
    {
        System.out.println("User added successfully");
        users.add(user);
    }

    public void releaseSingle()
    {
        System.out.println("\n Release new song !");
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is the name of the song?\n");
        String title = scanner.next();
        System.out.println("Who is the artist?\n");
        String artist = scanner.next();
        System.out.println("What genre does it belong to?\n");
        String genre = scanner.next();
        System.out.println("What is the duration?\n");
        String duration = scanner.next();
        Song song = new Song(title,genre,artist,duration,title);
        addSong(song);
        System.out.println("Song added!");
    }

    private void addSong(Song song)
    {
        songs.add(song);
    }

    public void releaseNewAlbum()
    {
        System.out.println("\n-------Release new album!--------");
        ArrayList<Song> albumSongs= new ArrayList<>();
        Album album= new Album();
        int response = 1;
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is the name of the artist?\n");
        System.out.print(">");
        String artist = scanner.nextLine();
        System.out.println("What is the name of the album?\n");
        System.out.print(">");
        String albumTitle = scanner.nextLine();
        System.out.println("What is the release date?\n");
        System.out.print(">");
        String releaseDate = scanner.nextLine();
        System.out.println("What genre does it belong to?\n");
        System.out.print(">");
        String genre = scanner.nextLine();
        System.out.println("Now specify the songs!");
        while(response==1)
        {
            System.out.println("What is the name of the song?\n");
            System.out.print(">");
            String songTitle = scanner.nextLine();
            System.out.println("What is the duration?\n");
            System.out.print(">");
            String duration = scanner.nextLine();
            Song song = new Song(songTitle, genre, artist, duration,albumTitle);
            addSong(song);
            albumSongs.add(song);
            System.out.println("Done?(1->No / 0->Yes)");
            System.out.print(">");
            response = scanner.nextInt();
            scanner.nextLine();
        }
        album.setTitle(albumTitle);
        album.setArtist(artist);
        album.setReleaseDate(releaseDate);
        album.setSongs(albumSongs);
        album.setGenre(genre);
        addAlbum(album);
        System.out.print("~Album ");
        System.out.print(album.getTitle());
        System.out.print(" added!\n");
    }

    private void addAlbum(Album album) {
        albums.add(album);
    }

    public static ArrayList<Song> getSongs() {
        return songs;
    }

    public static void setSongs(ArrayList<Song> songs) {
        Spotify.songs = songs;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void setUsers(ArrayList<User> users) {
        Spotify.users = users;
    }

    public static ArrayList<Album> getAlbums() {
        return albums;
    }

    public void printInfoForAlbumByArtistAndTitle(String albumName, String artist)
    {
        int gasit=0;
        for(Album album: albums)
        {
            if(album.getTitle().equalsIgnoreCase(albumName) && artist.equalsIgnoreCase(album.getArtist()))
            {
                gasit=1;
                System.out.println("\n---Information about " + albumName + "by "+album.getArtist() + "----");
                System.out.println("     Released on : " + album.getReleaseDate());
                System.out.println("     Genre : " + album.getGenre());
                System.out.println("     Songs:");
                int i=0;

                for(Song song: album.getSongs())
                {
                    System.out.println("         " + i +"." + song.getTitle() + " - " + song.getDuration());
                    i+=1;
                }
                System.out.println("--------------------------------------");
                break;
            }
        }
        if (gasit==0) System.out.println("Album doesn't exist!Sorry..");


    }

}
