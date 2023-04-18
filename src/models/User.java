package models;

import java.util.ArrayList;

public class User
{
    private String username,firstName,lastName,password,email,subscriptionType;
    private ArrayList<Playlist> playlists;
    private SongQueue songQueue;
    public User()
    {
        this.playlists = new ArrayList<>();
        this.songQueue = new SongQueue();

    }
    public User(String username,String firstName, String lastName, String password, String email, String subscriptionType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.subscriptionType = subscriptionType;
        this.playlists = new ArrayList<>();
        this.songQueue = new SongQueue();
        this.username = username;
    }
    public User(User user) {
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.password = user.password;
        this.email = user.email;
        this.subscriptionType = user.subscriptionType;
        this.playlists =new ArrayList<>(user.playlists); ///ArrayList copy constructor
        this.songQueue = new SongQueue(user.songQueue);
        this.username = user.username;
    }
    public User(String firstName, String lastName, String password, String email, String subscriptionType, ArrayList<Playlist> playlists, SongQueue songQueue) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.subscriptionType = subscriptionType;
        this.playlists = playlists;
        this.songQueue = songQueue;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public SongQueue getSongQueue() {
        return songQueue;
    }

    public void setSongQueue(SongQueue songQueue) {
        this.songQueue = new SongQueue(songQueue);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = new ArrayList<>(playlists);
    }

    public Playlist getPlaylistByName(String name)
    {
        Playlist playlist=null;
        for (Playlist p:playlists)
            if (p.getPlaylistName().equals(name))
            {
                playlist=p;
                return playlist;
            }
        playlist=new Playlist();
        return playlist;
    }
    public void addPlaylist(Playlist playlist)
    {
        this.playlists.add(playlist);
    }





}
