// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.artistRankings.model;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Patrick W. Henstebeck
 * @since 2017-12-30
 */
public class Artist {

    private String artist;
    private final Map<YearMonth, ArtistMonth> months;

    /**
     * Initialize the class.
     *
     * @param artist Name of the artist.
     */
    public Artist (String artist) {
        this.artist = artist;
        this.months = new HashMap<>();
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

    public Map<YearMonth, ArtistMonth> getMonths() {
        return months;
    }

    public void addTrack(Track track) {
        LocalDateTime time = track.getTime();
        YearMonth month = YearMonth.from(time);

        months.computeIfAbsent(month, it -> new ArtistMonth(artist, month)).addTrack(track);
    }

    public void merge(Artist other) {
        other.months.forEach((key, value) ->
            this.months.computeIfAbsent(key, key2 -> new ArtistMonth(artist, key2)).merge(value)
        );
    }

    public int getCount() {
        int count = 0;
        for (ArtistMonth month : months.values()) {
            count += month.getCount();
        }
        return count;
    }

    public int getCount(YearMonth start, YearMonth end) {
        int count = 0;
        for (ArtistMonth artistMonth : months.values()) {
            YearMonth month = artistMonth.getMonth();
            
            if (!month.isBefore(start) && !month.isAfter(end)) {
                count += artistMonth.getCount();
            }
        }
        return count;
    }


    // =================================================================================================================
    // -----| Object |-----
    // ====================

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Artist{");
        sb.append("artist='").append(artist).append('\'');
        sb.append(", months=").append(months);
        sb.append('}');
        return sb.toString();
    }
}
