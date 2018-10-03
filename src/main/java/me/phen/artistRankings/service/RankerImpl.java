// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.artistRankings.service;

import me.phen.artistRankings.api.ScrobberApi;
import me.phen.artistRankings.api.lastfm.LastFmApi;
import me.phen.artistRankings.model.Artist;
import me.phen.artistRankings.model.ArtistMonth;
import me.phen.artistRankings.model.ArtistSnapshot;
import me.phen.artistRankings.model.CountComparator;
import me.phen.artistRankings.model.Track;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Patrick W. Henstebeck
 * @since 2017-12-30 Sa
 */
public class RankerImpl implements Ranker {
    protected Merger merger;
    protected ScrobberApi api;

    /**
     * Initialize the class.
     */
    public RankerImpl() {
        api = new LastFmApi();
        merger = new MergerImpl();
    }

    /**
     * Load the artist data from the repo and apply any modifications.
     *
     * @param start Start date of the range to load data for.
     * @param end End date of the range to load data for.
     * @return List of artist data.
     */
    protected Collection<Artist> getArtistData(YearMonth start, YearMonth end) {
        LocalDate startDate = start.atDay(1);
        LocalDate endDate = end.atEndOfMonth();
        List<Track> tracks = api.getTracks(startDate, endDate);
        Map<String, Artist> artistsToTracks = new HashMap<>();

        System.out.print("Sorting");
        tracks.forEach(track -> {
            System.out.print(".");
            String artist = track.getArtist();
            artistsToTracks
                    .computeIfAbsent(artist, it ->new Artist(artist))
                    .addTrack(track);
        });
        System.out.println("");

        Set<Artist> artists = new HashSet<>();
        artists.addAll(artistsToTracks.values());
        return merger.merge(artists);
    }

    /**
     * {@inheritDoc}
     */
    public List<ArtistSnapshot> getYearlyRankings(YearMonth yearEnding) {
        YearMonth yearStarting = yearEnding.minus(11, ChronoUnit.MONTHS);

        YearMonth priorYearStarting = yearStarting.minus(1, ChronoUnit.MONTHS);
        YearMonth priorYearEnding = yearEnding.minus(1, ChronoUnit.MONTHS);

        Collection<Artist> artistData = getArtistData(priorYearStarting, yearEnding);

        List<ArtistSnapshot> snapshots = new ArrayList<>();
        artistData.forEach(artist -> {
            int current = artist.getCount(yearStarting, yearEnding);
            int last = artist.getCount(priorYearStarting, priorYearEnding);
            ArtistSnapshot snapshot = new ArtistSnapshot(artist.getArtist(), current, last);
            snapshot.setAdded(artist.getMonths().getOrDefault(yearEnding, ArtistMonth.ZERO).getCount());
            snapshot.setDefending(artist.getMonths().getOrDefault(yearStarting, ArtistMonth.ZERO).getCount());
            snapshots.add(snapshot);
        });

        snapshots.sort(new CountComparator());
        return snapshots;
    }
}