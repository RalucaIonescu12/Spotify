package models;

public class Main {
    public static void main(String[] args)
    {
        User user1 = new User("raluca12","raluca","Ionescu","2103394","ralucaandreeai@yahoo.com","student");
        System.out.println(user1.getEmail());

        Spotify spotify = Spotify.getInstance();
        spotify.addUser(user1);
        spotify.releaseNewAlbum();
        spotify.printInfoForAlbumByArtistAndTitle("Starboy","The Weeknd");
    }
}