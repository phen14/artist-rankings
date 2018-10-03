// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.artistRankings.api.lastfm.model;

/**
 * Available period values for a last.fm query.
 *
 * @author Patrick W. Henstebeck
 * @since 2017-12-23 Sa
 */
public enum LastFmPeriod {
    ALL_TIME("overall"),
    LAST_YEAR("12month");

    String key;

    private LastFmPeriod(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
