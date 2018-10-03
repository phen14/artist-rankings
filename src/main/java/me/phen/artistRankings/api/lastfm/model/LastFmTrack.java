// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.artistRankings.api.lastfm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import me.phen.artistRankings.model.Track;

/**
 * last.fm representation of a track.
 *
 * @author Patrick W. Henstebeck
 * @since 2017-12-30 Sa
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LastFmTrack {

    private LastFmTrackArtist artist;

    private LastFmDate date;

    private String name;


    // =================================================================================================================
    // -----| Getters & Setters |-----
    // ===============================

    public LastFmTrackArtist getArtist() {
        return artist;
    }

    public void setArtist(LastFmTrackArtist artist) {
        this.artist = artist;
    }

    public LastFmDate getDate() {
        return date;
    }

    public void setDate(LastFmDate date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    // =================================================================================================================
    // -----| Conversion |-----
    // ========================

    /**
     * Convert this to a base Track.
     *
     * @return Track.
     */
    public Track toTrack() {
        return new Track(artist.getName(), name, date.getTime());
    }


    // =================================================================================================================
    // -----| Object |-----
    // ====================

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LastFmTrack that = (LastFmTrack) o;

        if (artist != null ? !artist.equals(that.artist) : that.artist != null) {
            return false;
        }
        if (date != null ? !date.equals(that.date) : that.date != null) {
            return false;
        }
        return name != null ? name.equals(that.name) : that.name == null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = artist != null ? artist.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LastFmTrack{");
        sb.append("artist=").append(artist);
        sb.append(", date=").append(date);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
