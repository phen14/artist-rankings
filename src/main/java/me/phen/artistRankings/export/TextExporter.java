package me.phen.artistRankings.export;

import me.phen.artistRankings.model.ArtistSnapshot;

import java.util.List;

/**
 * Export the rankings to plain text.
 *
 * @author Patrick W. Henstebeck
 * @since 2018-02-11 Su
 */
public class TextExporter implements Exporter {

    /**
     * {@inheritDoc}
     */
    public void export(List<ArtistSnapshot> snapshots, int max) {
        int count = snapshots.size();
        int countWidth = String.valueOf(count).length();

        int i = 0;
        for (ArtistSnapshot snapshot : snapshots) {
            if (i >= count || i >= max) {
                return;
            }
            System.out.println(formatSnapshot(snapshot, countWidth, ++i));
        };
    }

    /**
     * Format a single snapshot line.
     *
     * @param snapshot Snapshot to format.
     * @param indexWidth Numebr of characters to reserve for the index.
     * @param index Index
     * @return Formatted snapshot.
     */
    protected String formatSnapshot(ArtistSnapshot snapshot, int indexWidth, int index) {
        String artist = snapshot.getArtist();
        int count = snapshot.getCountNow();
        int change = snapshot.getCountNow() - snapshot.getCountLast();
        int added = snapshot.getAdded();
        int defending = snapshot.getDefending();

        return String.format("%" + indexWidth + "s | %s: %d (%d), New: (%d), Defending: (%d)",
                index, artist, count, change, added, defending);
    }
}
