package models;

import java.util.ArrayList;
import java.util.Queue;

public class SongQueue extends Playlist
{

    public SongQueue()
    {
        super("Song Queue");
    }

    public void playFromSong(Song song)
    {
        for (int i=0;i<this.getSongs().size();i++)
            if(this.getSongs().get(i).equals(song))
            {
                for (int j=0;j<i;j++)
                    this.getSongs().remove(j);
                break;
            }
    }


    public void clearQueue()
    {
        this.getSongs().clear();
    }
}