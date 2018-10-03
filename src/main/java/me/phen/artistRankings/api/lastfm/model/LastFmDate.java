// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.artistRankings.api.lastfm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * last.fm representation of a timestamp.
 *
 * @author Patrick W. Henstebeck
 * @since 2017-12-30 Sa
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LastFmDate {

    @JsonProperty("uts")
    private int seconds;


    // =================================================================================================================
    // -----| Getters & Setters |-----
    // ===============================

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public LocalDateTime getTime() {
        Instant instant = Instant.ofEpochSecond(seconds);
        return instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
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

        LastFmDate that = (LastFmDate) o;

        return seconds == that.seconds;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return seconds;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LastFmDate{");
        sb.append("seconds=").append(seconds);
        sb.append("time=").append(getTime());
        sb.append('}');
        return sb.toString();
    }
}
