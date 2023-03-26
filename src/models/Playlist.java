package models;

import java.util.ArrayList;

public class Playlist
{
    private String playlistName;
    private ArrayList<Song> songs;

    public String getPlaylistName() {
        return playlistName;
    }

    public Playlist(String playlistName)
    {
        this.playlistName = playlistName;
        this.songs = new ArrayList<>();
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
