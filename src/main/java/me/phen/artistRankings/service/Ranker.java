// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.artistRankings.service;

import me.phen.artistRankings.model.ArtistSnapshot;

import java.time.YearMonth;
import java.util.List;

/**
 * Rank the artists.
 *
 * @author Patrick W. Henstebeck
 * @since 2018-02-11 Su
 */
public interface Ranker {

    /**
     * Get the rankings over a yearly period.
     *
     * @param yearEnding Month the yearly period ends.
     * @return List of artists in order by play count over the past year.
     */
    public List<ArtistSnapshot> getYearlyRankings(YearMonth yearEnding);
}
