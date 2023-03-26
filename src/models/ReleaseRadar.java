package models;

import java.util.ArrayList;
import java.util.List;

/**
 * ramane o piesa noua in release radar cat timp
 * nu au fost alte 10 melodii released dupa ea
 * */

public class ReleaseRadar extends Playlist
{
    private static int numSongsAdded = 0;
    public ReleaseRadar()
    {
        super("Release Radar");
    }
    @Override
    public void addSong(Song song) {
        if (numSongsAdded < 10) {
            this.getSongs().add(song);
            numSongsAdded++;
        }
        else
        {
            this.getSongs().remove(0);
            this.getSongs().add(song);
        }
    }
//    public void addMultipleSongs(Song song) {
//        if (numSongsAdded < 10) {
//            songs.add(song);
//            numSongsAdded++;
//        }
//        else
//        {
//            songs.remove(0);
//            songs.add(song);
//        }
//    }
    public static int getNumberSongsAdded() {
        return numSongsAdded;
    }
}
