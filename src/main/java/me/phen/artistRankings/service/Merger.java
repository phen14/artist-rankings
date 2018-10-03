// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.artistRankings.service;

import me.phen.artistRankings.model.Artist;

import java.util.Collection;

/**
 * Merge Last.fm artists together into a single Artist.
 *
 * @author Patrick W. Henstebeck
 * @since 2018-02-11 Su
 */
public interface Merger {

    /**
     * Run all the mergers on the list of artists.
     *
     * @param artists List of artists. List is altered in place.
     * @return The altered list.
     */
    Collection<Artist> merge(Collection<Artist> artists);
}
