// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.artistRankings;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import me.phen.artistRankings.api.ScrobberApi;
import me.phen.artistRankings.api.lastfm.LastFmApi;
import me.phen.artistRankings.api.lastfm.LastFmConfiguration;
import me.phen.artistRankings.export.Exporter;
import me.phen.artistRankings.export.HtmlExporter;
import me.phen.artistRankings.export.JsonExporter;
import me.phen.artistRankings.export.TextExporter;
import me.phen.artistRankings.server.model.HtmlResponse;
import me.phen.artistRankings.server.model.Rankings;
import me.phen.artistRankings.service.Ranker;
import me.phen.artistRankings.service.RankerImpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Set up dependency injection.
 *
 * @author Patrick W. Henstebeck
 * @since 2019-03-12 Tu
 */
public class ArtistRankingsGuiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Ranker.class).to(RankerImpl.class);
        bind(ScrobberApi.class).to(LastFmApi.class);

        bind(new TypeLiteral<Exporter<HtmlResponse>>(){}).to(new TypeLiteral<HtmlExporter>(){});
        bind(new TypeLiteral<Exporter<Rankings>>(){}).to(new TypeLiteral<JsonExporter>(){});
        bind(new TypeLiteral<Exporter<String>>(){}).to(new TypeLiteral<TextExporter>(){});
    }

    @Provides
    @Singleton
    LastFmConfiguration provideLastFmConfiguration() throws IOException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream("api/lastfm.properties");

        Properties props = new Properties();
        props.load(stream);
        return new LastFmConfiguration(props);
    }
}
