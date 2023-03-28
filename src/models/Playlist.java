package models;

import java.util.ArrayList;

public class Playlist
{
    private String playlistName,description;
    private ArrayList<Song> songs;
    public Playlist(String playlistName)
    {
        this.playlistName = playlistName;
        this.songs = new ArrayList<>();
        this.description = null;
    }
    public Playlist(String playlistName,String description)
    {
        this.playlistName = playlistName;
        this.description=description;
        this.songs = new ArrayList<>();
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlaylistName() {
        return playlistName;
    }



    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }
    public void removeSong(Song song)
    {
        this.songs.remove(song);
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }
    public void addSong(Song song)
    {
        this.songs.add(song);
    }


}
