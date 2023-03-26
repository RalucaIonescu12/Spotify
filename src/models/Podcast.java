package models;

public class Podcast
{
    private String podcastName, creator;
    private int duration;
    private String topic;

    public Podcast(String podcastName, String creator, int duration, String topic) {
        this.podcastName = podcastName;
        this.creator = creator;
        this.duration = duration;
        this.topic = topic;
    }

    public String getPodcastName() {
        return podcastName;
    }

    public void setPodcastName(String podcastName) {
        this.podcastName = podcastName;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
