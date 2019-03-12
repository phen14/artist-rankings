// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.artistRankings.model;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Patrick W. Henstebeck
 * @since 2017-12-30 Sa
 */
public class ArtistMonth {

    public static ArtistMonth ZERO = new ArtistMonth("zero", null);

    private String artist;
    private List<Track> tracks;
    private YearMonth month;


    // =================================================================================================================
    // -----| Constructors |-----
    // ==========================

    public ArtistMonth(String artist, YearMonth month) {
        this.artist = artist;
        this.month = month;

        this.tracks = new ArrayList<>();
    }


    // =================================================================================================================
    // -----| Getters & Setters |-----
    // ===============================

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public YearMonth getMonth() {
        return month;
    }

    public void setMonth(YearMonth month) {
        this.month = month;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public void addTrack(Track track) {
        if (tracks == null) {
            tracks = new ArrayList<>();
        }
        tracks.add(track);
    }

    public int getCount() {
        return (tracks == null) ? 0 : tracks.size();
    }

    public void merge(ArtistMonth other) {
        this.tracks.addAll(other.getTracks());
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

        ArtistMonth that = (ArtistMonth) o;

        if (month != that.month) {
            return false;
        }
        if (artist != null ? !artist.equals(that.artist) : that.artist != null) {
            return false;
        }
        return tracks != null ? tracks.equals(that.tracks) : that.tracks == null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = artist != null ? artist.hashCode() : 0;
        result = 31 * result + (month != null ? month.hashCode() : 0);
        result = 31 * result + (tracks != null ? tracks.hashCode() : 0);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(month).append(": ").append(getCount());
        return sb.toString();
    }
}
