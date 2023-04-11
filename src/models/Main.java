package models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args)
    {
        Spotify spotify = Spotify.getInstance();
        Scanner scanner = new Scanner(System.in);

        User user =new User("AndreiRadu","Andrei","Radu","123456","andreiradu@yahoo.com","Premium");
        spotify.simpleAddUser(user);
        user =new User("IoanaStoica","Ioana","Stoica","123456","ioanastoica@yahoo.com","Premium");
        spotify.simpleAddUser(user);
        user =new User("CristinaGrigore","Cristina","Grigore","123456","cristinagrigore@yahoo.com","Student");
        spotify.simpleAddUser(user);
        user =new User("MihaiNeagu","Mihai","Neagu","123456","mihaineagu@yahoo.com","Premium");
        spotify.simpleAddUser(user);
        user =new User("BiancaRadulescu","Bianca","Radulescu","123456","biancaradulescu@yahoo.com","Student");
        spotify.simpleAddUser(user);
        user= new User("RalucaIonescu","Raluca","Ionescu","123456","ralucaionescu@yahoo.com","student");
        spotify.simpleAddUser(user);
        Spotify.setLoggedUser(user);

        Song song= new Song("Rockin", "r&b", "The Weeknd","3:06","Starboy");
        spotify.simpleAddSong(song);

        //album
        ArrayList<Song> albumSongs= new ArrayList<>();
        song= new Song("Softcore", "alternative", "The Neighbourhood","3:18","Hard to Imagine the Neighbourhood ever Changing");
        albumSongs.add(song);
        song= new Song("Scary Love", "alternative", "The Neighbourhood","4:14","Hard to Imagine the Neighbourhood ever Changing");
        albumSongs.add(song);
        song= new Song("Nervous", "alternative", "The Neighbourhood","3:48","Hard to Imagine the Neighbourhood ever Changing");
        albumSongs.add(song);
        song= new Song("Blue", "alternative", "The Neighbourhood","2:48","Hard to Imagine the Neighbourhood ever Changing");
        albumSongs.add(song);
        song= new Song("Sadderdaze", "alternative", "The Neighbourhood","3:48","Hard to Imagine the Neighbourhood ever Changing");
        albumSongs.add(song);
        song= new Song("Compass", "alternative", "The Neighbourhood","3:48","Hard to Imagine the Neighbourhood ever Changing");
        albumSongs.add(song);
        Album album=new Album("Hard to Imagine the Neighbourhood ever Changing","The Neighbourhood",albumSongs,"12.03.2018","alternative");
        spotify.simpleAddAlbum(album);
        spotify.simpleAddRangeSongs(albumSongs);


        //album
        albumSongs=new ArrayList<>();
        Set<String> features=new HashSet<>();
        features.add("Karnaval Blues");
        song= new Song("Still want you", "electronic", "ZHU","3:18","Ringos desert", features);
        albumSongs.add(song);
        features.clear();
        features.add("Herizen");
        song= new Song("Save me", "electronic", "ZHU","4:14","Ringos desert",features);
        albumSongs.add(song);
        song= new Song("Guilty Love", "electronic", "ZHU","3:48","Ringos desert");
        albumSongs.add(song);
        features=new HashSet<>();
        features.add("Tame Impala");
        song= new Song("My life", "electronic", "ZHU","2:48","Ringos desert",features);
        albumSongs.add(song);
        song= new Song("Drowning", "electronic", "ZHU","3:48","Ringos desert");
        albumSongs.add(song);
        album=new Album("Ringos desert","ZHU",albumSongs,"10.02.2017","electronic");
        spotify.simpleAddAlbum(album);
        spotify.simpleAddRangeSongs(albumSongs);

        Spotify.setLoggedUser(user);

        song = new Song("Self Care", "Hip-Hop/Rap", "Mac Miller","3:06","Swimming");
        spotify.simpleAddSong(song);
        features=new HashSet<>();
        features.add("Frank Ocean");
        song = new Song("She", "Hip-Hop/Rap", "Tyler, The Creator","3:23","Goblin",features);
        spotify.simpleAddSong(song);
        song = new Song("Seigfried", "r&b/Soul", "Frank Ocean","3:12","Blonde");
        spotify.simpleAddSong(song);


        Podcast podcast = new Podcast("GreekMythology","Nathan","4:09","Hystory");
        Spotify.getPodcasts().add(podcast);
        podcast = new Podcast("Motivation","Nathan","31:02","Motivation and confidence");
        Spotify.getPodcasts().add(podcast);
        podcast = new Podcast("SpanishPodcast1","Duolingo","60:00","Stories in spanish for learning.");
        Spotify.getPodcasts().add(podcast);

        MyFrame myFrame=new MyFrame(Spotify.getInstance());

    }

}
