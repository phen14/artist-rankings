// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.artistRankings.model;

import java.time.LocalDateTime;

/**
 * Instant of a played track.
 *
 * @author Patrick W. Henstebeck
 * @since 2018-02-11 Su
 */
public class Track {

    private String artist;
    private String name;
    private LocalDateTime time;


    // =================================================================================================================
    // -----| Constructor |-----
    // =========================

    /**
     * Build a track.
     *
     * @param artist Artist performing the track.
     * @param name Title of the track.
     * @param time Local time and time the track was played.
     */
    public Track(String artist, String name, LocalDateTime time) {
        this.artist = artist;
        this.name = name;
        this.time = time;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
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

        Track that = (Track) o;

        if (artist != null ? !artist.equals(that.artist) : that.artist != null) {
            return false;
        }
        if (time != null ? !time.equals(that.time) : that.time != null) {
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
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("\n");
        sb.append(time).append(" | " );
        sb.append(artist).append(" - ");
        sb.append(name);
        return sb.toString();
    }
}
