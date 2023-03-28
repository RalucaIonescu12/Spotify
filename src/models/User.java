package models;

import java.util.ArrayList;
/**
1.sa poti afisa melodiile unui playlist al unui user
2.afisare detaliile unui user
3.creeare playlist nou pt user
4.lansare album nou
5.modificare playlist
6.creeare user nou
7.cautare melodie
 */

public class User
{
    private String username,firstName,lastName,password,email,subscriptionType;
    private ArrayList<Playlist> playlists;
    private SongQueue songQueue;
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
    public User(String firstName, String lastName, String password, String email, String subscriptionType, ArrayList<Playlist> playlists, SongQueue songQueue) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.subscriptionType = subscriptionType;
        this.playlists = playlists;
        this.songQueue = songQueue;
    }
    //////////////////methods/////////////////////////////



    //////////////getters & setters//////////////////////
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
        this.songQueue = songQueue;
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
        this.playlists = playlists;
    }
    ///////////////////////////////////////////////////////////////






}
