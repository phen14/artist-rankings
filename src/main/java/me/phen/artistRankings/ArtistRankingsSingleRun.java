// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.artistRankings;

import com.google.inject.Guice;
import com.google.inject.Injector;
import me.phen.artistRankings.export.Exporter;
import me.phen.artistRankings.export.TextExporter;
import me.phen.artistRankings.model.ArtistSnapshot;
import me.phen.artistRankings.service.Ranker;

import java.time.YearMonth;
import java.util.List;

/**
 * Run the damn thing.
 *
 * @author Patrick W. Henstebeck
 * @since 2018-02-11 (Su)
 */
public class ArtistRankingsSingleRun {

    public static void main(String... args) {
        Injector injector = Guice.createInjector(new ArtistRankingsGuiceModule());

        Ranker service = injector.getInstance(Ranker.class);
        Exporter exporter = injector.getInstance(TextExporter.class);

        List<ArtistSnapshot> rankings = service.getYearlyRankings(YearMonth.of(2019, 4));// Last of the 12 months
        exporter.export(rankings, 30);
    }
}
