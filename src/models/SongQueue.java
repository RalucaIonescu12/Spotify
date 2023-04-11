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
        for (int i = 0; i < this.getSongs().size();i++)
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
