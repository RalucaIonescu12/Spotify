package services;

import database.RetrieveData;
import models.*;
import repositories.AlbumRepo;
import repositories.SongRepo;
import repositories.UserRepo;

import java.util.*;

public class Spotify
{
    private static List<Song> songs;
    private static List<User> users;
    private static List<Podcast> podcasts;
    private static Set<Album> albums ;
    private static User loggedUser;
    private static ReleaseRadar releaseRadar;
    private static RetrieveData retrieveData = new RetrieveData();

    public void newSongReleased(Song song)
    {
        releaseRadar.addSong(song);
    }
    private Spotify()
    {
        retrieveData.createTables();
        retrieveData.insertIntoDatabase();

        songs =new ArrayList<>();
        users = new ArrayList<>();
        albums= new HashSet<>();
        podcasts=new ArrayList<>();
        loggedUser = new User();
        releaseRadar=new ReleaseRadar();
        retrieveData = new RetrieveData();
        loadData();
    }
    public void loadData(){
        songs = SongRepo.addData();
        users = UserRepo.addData();
        albums= AlbumRepo.addData();
    }
    private Spotify(List<Song> songs,List<User> users,List<Podcast> podcasts,Set<Album> albums)
    {
        Spotify.songs = songs;
        Spotify.users = users;
        Spotify.albums = albums;
        Spotify.podcasts = podcasts;
        Spotify.loggedUser = new User();
        Spotify.releaseRadar=new ReleaseRadar();
        Spotify.retrieveData=new RetrieveData();
        retrieveData.createTables();
        retrieveData.insertIntoDatabase();
    }
    private static Spotify instance;
    public static Spotify getInstance()
    {
        if (instance == null) {
            instance = new Spotify();
        }
        return instance;
    }



    public static void setAlbums(Set<Album> albums) {
        Spotify.albums = albums;
    }

    public static User getLoggedUser() {
        return UserRepo.getUserByUsername(loggedUser.getUsername());
    }

    public static void setLoggedUser(User loggedUser) {
        Spotify.loggedUser = new User(loggedUser);
    }

    public void playSongFromQueue()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter song name.");
        System.out.print(">");
        String songName = scanner.nextLine();
        System.out.println("Enter artist name.");
        System.out.print(">");
        String artist = scanner.nextLine();
        Song song;
        song = getSongByNameAndArtist(songName,artist);

        if(song == null)
            System.out.println("This song does not exist!");
        else {
            loggedUser.getSongQueue().playFromSong(song);
            System.out.println("**** Playing " + song.getTitle()+ " by "+ song.getArtist() + " ***\n");
        }

    }

    public Song getSongByNameAndArtist (String songName, String artist)
    {
        for(Song s : songs)
            if(s.getTitle().equalsIgnoreCase(songName) && s.getArtist().equalsIgnoreCase(artist))
                return s;

        System.out.println("Song not found!\n");
        return null;
    }
    public void simpleAddUser(User user)
    {
        users.add(user);
    }
    public void simpleAddSong(Song song)
    {
        SongRepo.addSong(song);
        releaseRadar.addSong(song);
        loadData();

    }
//    public void simpleAddRangeSongs(List<Song> rangeSongs)
//    {
//
//        for(Song s:songs)
//            SongRepo.addSongFromAlbum();
//            releaseRadar.addSong(s);
//    }
    public void simpleAddAlbum(Album album)
    {
        AlbumRepo.addAlbum(album);
        for(Song s:album.getSongs())
            releaseRadar.addSong(s);
        loadData();
    }
    public void addUser()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username.\n");
        System.out.print(">");
        String username= scanner.nextLine();
        System.out.println("Enter Lastname.\n");
        System.out.print(">");
        String lastname = scanner.nextLine();
        System.out.println("Enter Firstname.\n");
        System.out.print(">");
        String firstname = scanner.nextLine();
        System.out.println("Enter Email.\n");
        System.out.print(">");
        String email = scanner.nextLine();
        System.out.println("Select subscription type: student/premium/freetrial.\n");
        String subscription;
        while(true)
        {
            System.out.print(">");
            subscription = scanner.nextLine();
            if(!subscription.equalsIgnoreCase("student") && !subscription.equalsIgnoreCase("premium") && !subscription.equalsIgnoreCase("freetrial"))
                System.out.println("Invalid type. Type again!");
            else break;
        }
        System.out.println("Set Password.\n");
        System.out.print(">");
        String password= scanner.nextLine();

        User user =new User(username,firstname,lastname,password,email,subscription);
        UserRepo.addUser(user); ///TODO:adduser
        loadData();
        loggedUser=user;

        System.out.println("User added successfully!\n --You are now logged in.\n\n");
    }

    public void releaseSingle()
    {
        System.out.println("\n Release new song !");
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is the name of the song?\n");
        System.out.print(">");
        String title = scanner.nextLine();
        System.out.println("Who is the artist?\n");
        System.out.print(">");
        String artist = scanner.nextLine();
        System.out.println("What genre does it belong to?\n");
        System.out.print(">");
        String genre = scanner.nextLine();
        System.out.println("Does it have features? (1->No/ 2->Yes)\n");
        System.out.print(">");
        String response = scanner.next();

        Set<String> features=new HashSet<>();
        while(response.equals("2"))
        {
            System.out.println("Specify feature.\n");
            System.out.print(">");
            String feature = scanner.next();
            features.add(feature);
            System.out.println("Done? (1->Yes/ 2->No)\n");
            System.out.print(">");
            response = scanner.next();
        }
        System.out.println("What is the duration?\n");
        System.out.print(">");
        String duration = scanner.next();
        scanner.nextLine();
        Song song=new Song();
        if (features.isEmpty())  {
            song.setTitle(title);
            song.setGenre(genre);
            song.setDuration(duration);
            song.setArtist(artist);
            song.setAlbum(title);
        }
        else {
            song.setTitle(title);
            song.setGenre(genre);
            song.setDuration(duration);
            song.setArtist(artist);
            song.setAlbum(title);
            song.setFeatures(features);

        }
        SongRepo.addSong(song);
        loadData();
        releaseRadar.addSong(song);
        System.out.println("Song "+title+"added! $$$ ");
    }

    private void addSong(Song song)
    {
        SongRepo.addSong(song);
    }

    public void releaseNewAlbum()
    {
        System.out.println("\n-------Release new album!--------");
        List<Song> albumSongs= new ArrayList<>();
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
            Song song = new Song();
            song.setTitle(songTitle);
            song.setGenre(genre);
            song.setDuration(duration);
            song.setArtist(artist);
            song.setAlbum(albumTitle);
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
        AlbumRepo.addAlbum(album);
        for(Song song :albumSongs)
            SongRepo.addSongFromAlbum(song,album.getAlbumID());
        loadData();
        System.out.print("Album ");
        System.out.print(album.getTitle());
        System.out.print(" added!\n");
    }

    private void addAlbum(Album album) {
        AlbumRepo.addAlbum(album);
        loadData();
    }

    public static List<Song> getSongs() {
        return songs;
    }



    public static List<User> getUsers() {
        return users;
    }

    public static void setUsers(List<User> users) {

        for (User user : users) {
            Spotify.users.add(new User(user));
        }
    }

    public static Set<Album> getAlbums() {
        return albums;
    }

    public void printAllSongsAlbumsAndPodcasts()
    {
        System.out.println("ALBUMS:");
        for(Album album: albums)
        {

            System.out.println("\n---Information about " + album.getTitle() + " by "+album.getArtist() + "----");
            System.out.println("     Released on : " + album.getReleaseDate());
            System.out.println("     Genre : " + album.getGenre());
            System.out.println("     Songs:");
            int i=0;

            for(Song song: album.getSongs())
            {
                if(song.getFeatures().isEmpty())
                    System.out.println("         " + i +". ID=" +song.getSongId()+" - " +song.getTitle() + " - " + song.getDuration());
                else {
                    System.out.print("         " + i +"." + song.getTitle() +",  feat. " );


                    for(String f : song.getFeatures())
                        System.out.print(f + ", " );

                    System.out.println(" - " + song.getDuration());
                }
                i+=1;
            }

        }
        System.out.println("-- Songs:");
        int i=0;

        for(Song song: songs)
        {
            if(song.getFeatures().isEmpty())
                System.out.println("         " + i +". ID=" +song.getSongId()+" - " + song.getTitle() + " - " + song.getArtist()+ " - "+ song.getDuration());
            else {
                System.out.print("         " + i +"." + song.getTitle() +",  feat. " );


                for(String f : song.getFeatures())
                    System.out.print(f + ", " );

                System.out.println(" - "+ song.getArtist()+ " - " + song.getDuration());
            }
            i+=1;
        }
        System.out.println("-- Podcasts:");
        i=0;

        for(Podcast podcast: podcasts)
        {
            System.out.println("         " +i+ "."+podcast.getPodcastName() +" - "+podcast.getCreator() +" - "+ podcast.getTopic() +" - "+ podcast.getDuration());
            i+=1;
        }
        System.out.println();
    }
    public void playSong(Song song)
    {

    }
    public void printQueue()
    {
        System.out.println("-- Your Queque:");
        int i=0;
        for(Song song: loggedUser.getSongQueue().getSongs())
        {
            i++;
            System.out.println("   "+i+". " +song.getTitle() +" - "+ song.getArtist());
        }
    }

    public void printInfoForAlbumByArtistAndTitle(String albumName, String artist)
    {
        int gasit=0;
        for(Album album: albums)
        {
            if(album.getTitle().equalsIgnoreCase(albumName) && artist.equalsIgnoreCase(album.getArtist()))
            {

                gasit=1;
                System.out.println("\n---Information about " + albumName + " by "+album.getArtist() + "----");
                System.out.println("     Released on : " + album.getReleaseDate());
                System.out.println("     Genre : " + album.getGenre());
                System.out.println("     Songs:");
                int i=0;

                for(Song song: album.getSongs())
                {
                    if(song.getFeatures().isEmpty())
                    System.out.println("         " + i +"." + song.getTitle() + " - " + song.getDuration());
                    else {
                        System.out.print("         " + i +"." + song.getTitle() +",  feat. " );


                        for(String f : song.getFeatures())
                            System.out.print(f + ", " );

                        System.out.println(" - " + song.getDuration());
                    }
                    i+=1;
                }
                System.out.println("--------------------------------------");
                break;
            }
        }
        if (gasit==0) System.out.println("Album doesn't exist!Sorry..\n\n");


    }
    public void deleteSongFromPlaylist(String playlistName,String song)
    {
        Playlist playlist = loggedUser.getPlaylistByName(playlistName);
        if (playlist.getPlaylistName().equals(""))System.out.println("The playlist doesn't exist.\n");
        else
            loggedUser.getPlaylistByName(playlistName).removeSongByName(song);

    }

    private Song getSongByName(String song) {
        for(Song s : songs)
            if(s.getTitle().equalsIgnoreCase(song))
                return new Song(s);

        System.out.println("Song not found!\n");
        return null;
    }

    public int login()
    {
        Scanner scanner = new Scanner(System.in);
        String pass="";
        User loggedUser=null;
        boolean foundUsername =false;
        while(!foundUsername)
        {
            System.out.println("Enter username.");
            System.out.print(">");
            String username = scanner.nextLine();

            for (User user : users) {
                if (user.getUsername().equals(username)) {
                    pass = user.getPassword();
                    loggedUser = user;
                    foundUsername =true;
                    break;
                }
            }
            if (!foundUsername) {System.out.println("Username not found! Try again.");}
        }


        int i=1;
        int ok=0;
        do{
            System.out.println("Enter password.");
            System.out.print(">");
            String password= scanner.nextLine();
            if(pass.equals(password))
            {
                Spotify.loggedUser =  loggedUser;
                System.out.println("Hello "+ loggedUser.getFirstName()+"! You are now logged in!\n");
                ok=1;
                break;
            }
            else
            {
                System.out.println("Wrong password!\n");
                i+=1;
            }

        }while(i<=3);

        if (ok==0)
        {
            System.out.println("Login failed!");
            return -1;
        }
        else return 0;
    }
    public void createNewPlaylist()
    {
        Playlist playlist = new Playlist();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name of the playlist.");
        System.out.print(">");
        String playlistName= scanner.nextLine();
        playlist.setPlaylistName(playlistName);

        String response = "";
        while (!response.equals("1") && !response.equals("2"))
        {
            System.out.println("Want to have a playlist description?(1->yes / 2->no)");
            System.out.print(">");
            response = scanner.nextLine();
            if(!response.equals("1") && !response.equals("2")) System.out.println("Invalid input.");
        }
        if(response.equals("1"))
        {
            System.out.println("Enter description(less than 100 characters).");
            System.out.print(">");
            String description = scanner.nextLine();
            playlist.setDescription(description);
        }

        while(true)
        {
            System.out.println("Enter song name.");
            System.out.print(">");
            String songName = scanner.nextLine();
            System.out.println("Enter artist name.");
            System.out.print(">");
            String artist = scanner.nextLine();
            Song song = getSongByNameAndArtist(songName,artist);

            if(song==null)
                System.out.println("This song does not exist!");
            else
            {
                playlist.addSong(song);
                System.out.println("Song added");
            }

            response = "";
            while (!response.equals("1") && !response.equals("2"))
            {
                    System.out.println("Done? (1->Yes / 2->No)");
                    System.out.print(">");
                    response = scanner.nextLine();
            }
            if(response.equals("1")) break;
        }

        UserRepo.addPlaylistToUser(loggedUser.getUsername(),playlist);
        loadData();
        for(User user: users)
            if(user.getUsername().equals(loggedUser.getUsername()))
                loggedUser=user;

        System.out.println("Playlist " + playlistName + " added!\n" );
    }


    public void addSongInQueue(String artist, String title)
    {
        Song song = getSongByNameAndArtist(title,artist);
        if (song.getTitle().equals("")) return;
        else {
            loggedUser.getSongQueue().addSong(song);

            System.out.println("Song " + song.getTitle() + " added in Queue.\n");
        }
    }

    public static List<Podcast> getPodcasts() {
        return podcasts;
    }

    public static void setPodcasts(List<Podcast> podcasts) {
        Spotify.podcasts = podcasts;
        for (Podcast podcast : podcasts) {
            Spotify.podcasts.add(new Podcast(podcast));
        }
    }

    public void showPlaylists()
    {
        if(loggedUser.getPlaylists().isEmpty()) System.out.println("No playlists\n");
        else for (Playlist p : loggedUser.getPlaylists())
        {
            System.out.println("\n--- Playlist:  " + p.getPlaylistName()+"----");
            System.out.println("Desc: " + p.getDescription());
            System.out.println("     Songs:");
            int i = 0;

            for (Song song : p.getSongs()) {
                if (song.getFeatures().isEmpty())
                    System.out.println("         " + i + "." + song.getTitle() + " - " + song.getArtist() + " - " +song.getDuration());
                else
                {
                    System.out.print("         " + i +"." + song.getTitle() + " - "+ song.getArtist() +",  feat. " );


                    for(String f : song.getFeatures())
                        System.out.print(f + ", " );

                    System.out.println(" - " + song.getDuration());
                }

                i += 1;
            }
            System.out.println("\n~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
            break;
        }
    }

}
