package models;

public class SongQueue extends Playlist
{

    public SongQueue()
    {
        super("Song Queue");
    }

    public SongQueue(SongQueue sq)
    {
        super(sq);
    }
    public void playFromSong(Song song)
    {
        for (Song s:this.getSongs())
        {
            if(s.getTitle().equals(song.getTitle()) && s.getArtist().equals(song.getArtist()))
            {
                break;
            }
            else this.getSongs().remove(s);
        }
    }

    public void clearQueue()
    {
        this.getSongs().clear();
    }
}
