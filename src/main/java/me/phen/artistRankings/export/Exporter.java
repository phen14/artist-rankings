package me.phen.artistRankings.export;

import me.phen.artistRankings.model.ArtistSnapshot;

import java.util.List;

/**
 * Export the data.
 *
 * @author Patrick W. Henstebeck
 * @since 2018-02-11 Su
 */
public interface Exporter<T> {

    /**
     * Export the data.
     *
     * @param snapshots List of artist snapshots in the order they will be exported.
     * @param max The highest number ranking to include.
     */
    T export(List<ArtistSnapshot> snapshots, int max);
}
