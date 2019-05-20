// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.artistRankings;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import me.phen.artistRankings.api.ScrobberApi;
import me.phen.artistRankings.api.lastfm.LastFmApi;
import me.phen.artistRankings.api.lastfm.LastFmConfiguration;
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
