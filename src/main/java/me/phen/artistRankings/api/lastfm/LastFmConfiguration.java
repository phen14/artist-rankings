// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.artistRankings.api.lastfm;

import java.util.Properties;

/**
 * Configuration for accessing Last.fm.
 *
 * @author Patrick W. Henstebeck
 * @since 2017-12-23 Sa
 */
public class LastFmConfiguration {
    private static final String API_KEY_KEY = "api-key";
    private static final String BASE_URL_KEY = "base-url";
    private static final String USER_KEY = "user";

    private String apiKey;
    private String baseUrl;
    private String user;

    /**
     * Initialize the configuration from generic loaded properties.
     *
     * @param props Properties loaded from the config file.
     */
    public LastFmConfiguration(Properties props) {
        apiKey = getValue(props, API_KEY_KEY);
        baseUrl = getValue(props, BASE_URL_KEY);
        user = getValue(props, USER_KEY);
    }

    /**
     * Get a property value.
     *
     * @param props Generic loaded properties.
     * @param key Property key to load.
     * @return Loaded value.
     * @throws RuntimeException If no value is found for the key.
     */
    private String getValue(Properties props, String key) {
        String value = props.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Missing Last.fm property [" + key + "].");
        }
        return value;
    }

    /**
     * @return Returns the apiKey.
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * @return Returns the baseUrl.
     */
    public String getBaseUrl() {
        return baseUrl;
    }

    /**
     * @return Returns the user.
     */
    public String getUser() {
        return user;
    }
}
