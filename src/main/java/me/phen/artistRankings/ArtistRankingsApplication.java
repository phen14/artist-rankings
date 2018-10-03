// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.artistRankings;

import me.phen.artistRankings.export.Exporter;
import me.phen.artistRankings.export.TextExporter;
import me.phen.artistRankings.model.ArtistSnapshot;
import me.phen.artistRankings.service.RankerImpl;

import java.time.YearMonth;
import java.util.List;

/**
 * Run the damn thing.
 *
 * @author Patrick W. Henstebeck
 * @since 2018-02-11 Su
 */
public class ArtistRankingsApplication {

    public static void main(String... args) throws Exception {
        RankerImpl service = new RankerImpl();
        Exporter exporter = new TextExporter();

        List<ArtistSnapshot> rankings = service.getYearlyRankings(YearMonth.of(2018, 9));
        exporter.export(rankings, 30);
    }
}
