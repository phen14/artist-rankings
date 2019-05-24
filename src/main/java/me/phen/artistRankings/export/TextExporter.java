// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.artistRankings.export;

import com.google.inject.Inject;
import me.phen.artistRankings.model.ArtistSnapshot;

import java.util.List;

/**
 * Export the rankings to plain text.
 *
 * @author Patrick W. Henstebeck
 * @since 2018-02-11 Su
 */
public class TextExporter implements Exporter<String> {

    @Inject
    public TextExporter() {
    }

    /**
     * {@inheritDoc}
     */
    public String export(List<ArtistSnapshot> snapshots, int max) {
        int count = snapshots.size();
        int countWidth = String.valueOf(count).length();

        int i = 0;

        StringBuilder sb = new StringBuilder();

        sb.append("\n\n\nResults\n-------\n");
        for (ArtistSnapshot snapshot : snapshots) {
            if (i >= count || i >= max) {
                break;
            }
            sb.append(formatSnapshot(snapshot, countWidth, ++i));
        }

        return sb.toString();
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

        return String.format("%" + indexWidth + "s | %s: %d (%d), New: (%d), Defending: (%d)\n",
                index, artist, count, change, added, defending);
    }
}
