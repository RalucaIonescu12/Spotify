package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Playlist
{
    private String playlistName,description;

    private List<Song> songs;
    public Playlist()
    {
        this.playlistName = "";
        this.songs = new ArrayList<>();
        this.description = null;
    }
    public Playlist(String playlistName)
    {
        this.playlistName = playlistName;
        this.songs = new ArrayList<>();
        this.description = null;
    }
    public Playlist(String playlistName,String description)
    {
        this.playlistName = playlistName;
        this.description = description;
        this.songs = new ArrayList<>();
    }
    public Playlist(Playlist p)
    {
        this.playlistName = p.playlistName;
        this.description = p.description;
        this.songs = new ArrayList<>(p.songs);
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

    public List<Song> getSongs() {
        return songs;
    }
    public void removeSong(Song song)
    {
        this.songs.remove(song);
    }
    public void removeSongByName(String songName)
    {
        int ok=0;
       for(Song song: songs)
       {
           if (song.getTitle().equalsIgnoreCase(songName))
           {
               songs.remove(song);
               ok=1;
               break;
           }
       }
       if(ok==0)System.out.println("The song doesn't exist!");
    }
    public void setSongs(List<Song> songs) {
        this.songs =songs;
    }
    public void addSong(Song song)
    {
        this.songs.add(song);
    }


}
