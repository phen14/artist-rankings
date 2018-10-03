// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.artistRankings.api.lastfm.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * last.fm wraps their list of artists in an extra object.
 *
 * @author Patrick W. Henstebeck
 * @since 2017-12-23 Sa
 */
public class LastFmArtistList {

    @JsonProperty("artist")
    private List<LastFmArtist> artists;


    // =================================================================================================================
    // -----| Getters & Setters |-----
    // ===============================

    public List<LastFmArtist> getArtists() {
        return artists;
    }

    public void setArtists(List<LastFmArtist> artists) {
        this.artists = artists;
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

        LastFmArtistList that = (LastFmArtistList) o;

        return artists != null ? artists.equals(that.artists) : that.artists == null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return artists != null ? artists.hashCode() : 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LastFmArtistList{");
        sb.append("artists=").append(artists);
        sb.append('}');
        return sb.toString();
    }
}
