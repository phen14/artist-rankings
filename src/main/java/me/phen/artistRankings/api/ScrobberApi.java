package me.phen.artistRankings.api;

import me.phen.artistRankings.api.lastfm.model.LastFmPeriod;
import me.phen.artistRankings.api.lastfm.model.LastFmTopArtistResponse;
import me.phen.artistRankings.model.Track;

import java.time.LocalDate;
import java.util.List;

/**
 * Interface for getting data from Scrobbers.
 *
 * @author Patrick W. Henstebeck
 * @since 2018-02-11 Su
 */
public interface ScrobberApi {
    LastFmTopArtistResponse getTopArtists(LastFmPeriod period) throws Exception;

    List<Track> getTracks(LocalDate startDate, LocalDate endDate);
}
