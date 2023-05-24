package models;

import java.util.Objects;

public class Podcast
{
    private String podcastName, creator;
    private String duration;
    private String topic;
    public Podcast()
    {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Podcast podcast = (Podcast) o;
        return Objects.equals(podcastName, podcast.podcastName) && Objects.equals(creator, podcast.creator) && Objects.equals(duration, podcast.duration) && Objects.equals(topic, podcast.topic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(podcastName, creator, duration, topic);
    }

    public Podcast(String podcastName, String creator, String duration, String topic) {
        this.podcastName = podcastName;
        this.creator = creator;
        this.duration = duration;
        this.topic = topic;
    }
    public Podcast(Podcast p) {
        this.podcastName = p.podcastName;
        this.creator = p.creator;
        this.duration =p.duration;
        this.topic = p.topic;
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
