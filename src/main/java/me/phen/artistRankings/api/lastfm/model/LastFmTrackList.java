// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.artistRankings.api.lastfm.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * last.fm wraps their list of tracks in an extra object.
 *
 * @author Patrick W. Henstebeck
 * @since 2017-12-23 Sa
 */
public class LastFmTrackList {

    @JsonProperty("@attr")
    private LastFmResponseAttributes attributes;

    @JsonProperty("track")
    private List<LastFmTrack> tracks;


    // =================================================================================================================
    // -----| Getters & Setters |-----
    // ===============================

    public LastFmResponseAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(LastFmResponseAttributes attributes) {
        this.attributes = attributes;
    }

    public List<LastFmTrack> getTracks() {
        return tracks;
    }

    public void setTracks(List<LastFmTrack> tracks) {
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

        LastFmTrackList that = (LastFmTrackList) o;

        if (attributes != null ? !attributes.equals(that.attributes) : that.attributes != null) {
            return false;
        }
        return tracks != null ? tracks.equals(that.tracks) : that.tracks == null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = attributes != null ? attributes.hashCode() : 0;
        result = 31 * result + (tracks != null ? tracks.hashCode() : 0);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LastFmTrackList{");
        sb.append("attributes=").append(attributes);
        sb.append(", tracks=").append(tracks);
        sb.append('}');
        return sb.toString();
    }
}
