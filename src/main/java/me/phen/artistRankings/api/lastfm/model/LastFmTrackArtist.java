// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.artistRankings.api.lastfm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
 * last.fm representation of the artist for a track.
 *
 * @author Patrick W. Henstebeck
 * @since 2017-12-30 Sa
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LastFmTrackArtist {

    @JsonProperty("mbid")
    private UUID id;

    @JsonProperty("#text")
    private String name;


    // =================================================================================================================
    // -----| Getters & Setters |-----
    // ===============================

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

        LastFmTrackArtist that = (LastFmTrackArtist) o;

        if (id != null ? !id.equals(that.id) : that.id != null) {
            return false;
        }
        return (name != null ? !name.equals(that.name) : that.name != null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public static String fixedLengthString(String string, int length) {
        return String.format("%1$"+length+ "s", string);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LastFmTrackArtist{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
