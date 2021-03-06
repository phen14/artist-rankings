// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.artistRankings.api.lastfm;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import me.phen.artistRankings.ObjectMapperFactory;
import me.phen.artistRankings.api.ScrobberApi;
import me.phen.artistRankings.api.lastfm.model.LastFmPeriod;
import me.phen.artistRankings.api.lastfm.model.LastFmRecentTrackResponse;
import me.phen.artistRankings.api.lastfm.model.LastFmTopArtistResponse;
import me.phen.artistRankings.api.lastfm.model.LastFmTrack;
import me.phen.artistRankings.model.Track;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Last.fm implementation of the ScrobberApi
 *
 * @author Patrick W. Henstebeck
 * @since 2017-12-23 Sa
 */
public class LastFmApi implements ScrobberApi {
    private static final Logger log = LoggerFactory.getLogger(LastFmApi.class);

    private LastFmConfiguration config;

    private static final String TOP_ARTIST_QUERY = "{0}?method=user.gettopartists" +
            "&user={1}" +
            "&api_key={2}" +
            "&format=json" +
            "&period={3}" +
            "&limit={4}";

    private static final String RECENT_TRACK_QUERY = "{0}?method=user.getrecenttracks" +
            "&user={1}" +
            "&api_key={2}" +
            "&format=json" +
            "&from={3,number,#}" +
            "&to={4,number,#}" +
            "&limit=200&page={5}";

    protected ObjectMapper mapper;

    @Inject
    public LastFmApi(LastFmConfiguration config) {
        this.config = config;
        this.mapper = ObjectMapperFactory.getObjectMapper();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LastFmTopArtistResponse getTopArtists(LastFmPeriod period) throws Exception {
        String query = MessageFormat.format(TOP_ARTIST_QUERY, config.getBaseUrl(), config.getUser(), config.getApiKey(), period.getKey(), "25");
        URL url = new URL(query);

        String content = getContent(url);
        LastFmTopArtistResponse response = mapper.readValue(content, LastFmTopArtistResponse.class);
        log.debug(response.toString());

        return response;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Track> getTracks(LocalDate startDate, LocalDate endDate) {
        Instant startInstant = startDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        long startSeconds = startInstant.toEpochMilli() / 1000;

        Instant endInstant = endDate.plusDays(1).atStartOfDay().minusSeconds(1).atZone(ZoneId.systemDefault()).toInstant();
        long endSeconds = endInstant.toEpochMilli() / 1000;

        List<LastFmTrack> tracks = new ArrayList<>();
        int currentPage = 1;
        int totalPages = Integer.MAX_VALUE;
        while (currentPage <= totalPages) {
            try {
                log.info("Querying page {} of {}", currentPage, (currentPage == 1) ? "--" : totalPages);
                String query = MessageFormat.format(RECENT_TRACK_QUERY, config.getBaseUrl(), config.getUser(), config.getApiKey(),
                        startSeconds, endSeconds, currentPage);
                URL url = new URL(query);

                String content = getContent(url);
                LastFmRecentTrackResponse response = mapper.readValue(content, LastFmRecentTrackResponse.class);
                tracks.addAll(response.getTracks().getTracks());

                ++currentPage;
                totalPages = response.getTracks().getAttributes().getTotalPages();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        }

        return tracks.stream().map(LastFmTrack::toTrack).collect(Collectors.toList());
    }

    /**
     * Make the call to get the data.
     *
     * @param url URL to hit.
     * @return Response from the call.
     */
    protected String getContent(URL url) {
        try {
            BufferedReader in = null;
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            try {
                con.setRequestMethod("GET");
                con.setRequestProperty("Content-Type", "application/json");
                con.setRequestProperty("User-Agent", "phen");

                int status = con.getResponseCode();
                if (status != 200) {
                    throw new RuntimeException("Error.  " + status + ": " + con.getResponseMessage());
                }

                in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }

                return content.toString();
            } finally {
                if (in != null) {
                    in.close();
                }
                con.disconnect();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setConfig(LastFmConfiguration config) {
        this.config = config;
    }
}
