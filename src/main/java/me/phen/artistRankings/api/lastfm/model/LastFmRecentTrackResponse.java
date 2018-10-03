// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.artistRankings.api.lastfm.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Wrapper for the last.fm response when querying for a list of tracks.
 *
 * @author Patrick W. Henstebeck
 * @since 2017-12-30 Sa
 */
public class LastFmRecentTrackResponse {

    @JsonProperty("recenttracks")
    private LastFmTrackList tracks;


    // =================================================================================================================
    // -----| Getters & Setters |-----
    // ===============================

    public LastFmTrackList getTracks() {
        return tracks;
    }

    public void setTracks(LastFmTrackList tracks) {
        this.tracks = tracks;
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

        LastFmRecentTrackResponse that = (LastFmRecentTrackResponse) o;

        return tracks != null ? tracks.equals(that.tracks) : that.tracks == null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return tracks != null ? tracks.hashCode() : 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LastFmRecentTrackResponse{");
        sb.append("tracks=").append(tracks);
        sb.append('}');
        return sb.toString();
    }
}
