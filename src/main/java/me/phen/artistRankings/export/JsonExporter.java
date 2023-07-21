// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.artistRankings.export;

import com.google.inject.Inject;
import me.phen.artistRankings.model.ArtistSnapshot;
import me.phen.artistRankings.server.model.RankedArtist;
import me.phen.artistRankings.server.model.Rankings;

import java.util.List;

/**
 * Export the rankings to JSON.
 *
 * @author Patrick W. Henstebeck
 * @since 2018-02-11 Su
 */
public class JsonExporter implements Exporter<Rankings> {

    @Inject
    public JsonExporter() {
    }

    /**
     * {@inheritDoc}
     */
    public Rankings export(List<ArtistSnapshot> snapshots, int max) {
        int count = snapshots.size();
        int i = 0;

        Rankings rankings = new Rankings();

        for (ArtistSnapshot snapshot : snapshots) {
            if (i >= count || i >= max) {
                break;
            }

            RankedArtist artist = formatSnapshot(snapshot, ++i);
            rankings.addArtist(artist);
        }

        return rankings;
    }

    /**
     * Format a single snapshot line.
     *
     * @param snapshot Snapshot to format.
     * @param index Index
     * @return Formatted snapshot.
     */
    protected RankedArtist formatSnapshot(ArtistSnapshot snapshot, int index) {
        RankedArtist artist = new RankedArtist();
        artist.setName(snapshot.getArtist());
        artist.setCount(snapshot.getCountNow());
        artist.setChange(snapshot.getCountNow() - snapshot.getCountLast());

        artist.setAdded(snapshot.getAdded());
        artist.setDefending(snapshot.getDefending());

        artist.setRank(index);

        return artist;
    }
}
