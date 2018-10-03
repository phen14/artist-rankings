// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.artistRankings.api.lastfm.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Wrapper for the last.fm response when querying for a list of artists.
 *
 * @author Patrick W. Henstebeck
 * @since 2017-12-23 Sa
 */
public class LastFmTopArtistResponse {

    @JsonProperty("topartists")
    private LastFmArtistList topArtists;


    // =================================================================================================================
    // -----| Getters & Setters |-----
    // ===============================

    public LastFmArtistList getTopArtists() {
        return topArtists;
    }

    public void setTopArtists(LastFmArtistList topArtists) {
        this.topArtists = topArtists;
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

        LastFmTopArtistResponse that = (LastFmTopArtistResponse) o;

        return topArtists != null ? topArtists.equals(that.topArtists) : that.topArtists == null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return topArtists != null ? topArtists.hashCode() : 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LastFmTopArtistResponse{");
        sb.append("topArtists=").append(topArtists);
        sb.append('}');
        return sb.toString();
    }
}
