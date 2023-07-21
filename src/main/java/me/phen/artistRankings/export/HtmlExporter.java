// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.artistRankings.export;

import com.google.inject.Inject;
import me.phen.artistRankings.model.ArtistSnapshot;
import me.phen.artistRankings.server.model.HtmlResponse;

import java.util.List;

/**
 * Export the rankings to HTML.
 *
 * @author Patrick W. Henstebeck
 * @since 2019-07-05 Fr
 */
public class HtmlExporter implements Exporter<HtmlResponse> {

    @Inject
    public HtmlExporter() {
    }

    /**
     * {@inheritDoc}
     */
    public HtmlResponse export(List<ArtistSnapshot> snapshots, int max) {
        int count = snapshots.size();
        int i = 0;

        StringBuilder sb = new StringBuilder();

        sb.append("<div class='artist-rankings'>\n");
        for (ArtistSnapshot snapshot : snapshots) {
            if (i >= count || i >= max) {
                break;
            }
            sb.append(formatSnapshot(snapshot, ++i));
        }

        sb.append("</div>\n");
        return new HtmlResponse(sb.toString());
    }

    /**
     * Format a single snapshot line.
     *
     * @param snapshot Snapshot to format.
     * @param index Index
     * @return Formatted snapshot.
     */
    protected String formatSnapshot(ArtistSnapshot snapshot,int index) {
        int change = snapshot.getCountNow() - snapshot.getCountLast();
        String countChangeClass = "";
        if (change > 0) {
            countChangeClass = "positive";
        } else if (change < 0) {
            countChangeClass = "negative";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\t<div class='rankings-row'>\n");

        sb.append("\t\t<div class='rank-change'>").append("TODO").append("</div>\n");
        sb.append("\t\t<div class='rank'>").append(index).append("</div>\n");
        sb.append("\t\t<div class='artist'>").append(snapshot.getArtist()).append("</div>\n");
        sb.append("\t\t<div class='count'>").append(snapshot.getCountNow()).append("</div>\n");
        sb.append("\t\t<div class='count-change ").append(countChangeClass).append("'>")
                .append(snapshot.getCountNow() - snapshot.getCountLast()).append("</div>\n");
        sb.append("\t\t<div class='added'>").append(snapshot.getAdded()).append("</div>\n");
        sb.append("\t\t<div class='defending'>").append(snapshot.getDefending()).append("</div>\n");

        sb.append("\t</div>\n");

        return sb.toString();
    }
}
