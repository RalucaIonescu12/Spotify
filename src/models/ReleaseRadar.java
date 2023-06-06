package models;
/**
 * a song remains in Release Radar as long as another 15 songs
 * haven't been released after it
 * */

//TODO:pastrez sau nu releaseradar
public class ReleaseRadar extends Playlist
{
    private static int numSongsAdded;
    public ReleaseRadar()
    {
        super("Release Radar");
    }
    @Override
    public void addSong(Song song) {
        if (numSongsAdded < 15) {
            this.getSongs().add(song);
            numSongsAdded++;
        }
        else
        {
            this.getSongs().remove(0);
            this.getSongs().add(song);
        }
    }

    public static int getNumberSongsAdded() {
        return numSongsAdded;
    }

    public static int getNumSongsAdded() {
        return numSongsAdded;
    }

    public static void setNumSongsAdded(int numSongsAdded) {
        ReleaseRadar.numSongsAdded = numSongsAdded;
    }
}
