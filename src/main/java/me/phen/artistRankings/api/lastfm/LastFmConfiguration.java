// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.artistRankings.api.lastfm;

/**
 * @author Patrick W. Henstebeck
 * @since 2017-12-23 Sa
 */
public class LastFmConfiguration {
    private static final String API_KEY = "1bbe422e8508a18ec31661242f22ec28";
    private static final String BASE_URL = "http://ws.audioscrobbler.com/2.0/";
    private static final String SHARED_SECRET = "47a75b332e1503e5ff61f9ca4684b959";
    private static final String USER = "nynascar";

    public String getApiKey() {
        return API_KEY;
    }

    public String getBaseUrl() {
        return BASE_URL;
    }

    public String getSharedSecret() {
        return SHARED_SECRET;
    }

    public String getUser() {
        return USER;
    }
}
