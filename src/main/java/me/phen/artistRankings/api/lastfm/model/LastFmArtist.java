// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.artistRankings.api.lastfm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
 * last.fm representation of an artist.
 *
 * @author Patrick W. Henstebeck
 * @since 2017-12-23 Sa
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LastFmArtist {

    @JsonProperty("playcount")
    private Integer count;

    @JsonProperty("mbid")
    private UUID id;

    private String name;


    // =================================================================================================================
    // -----| Getters & Setters |-----
    // ===============================

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

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

        LastFmArtist that = (LastFmArtist) o;

        if (count != null ? !count.equals(that.count) : that.count != null) {
            return false;
        }
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
        int result = count != null ? count.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LastFmArtist{");
        sb.append("count=").append(count);
        sb.append(", id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
