// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.artistRankings.service.adjust;

import me.phen.artistRankings.model.Artist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Remove ignored artists from the entry list.
 *
 * @author Patrick W. Henstebeck
 * @since 2019-03-02 (Sa)
 */
public class Ignorer implements Adjuster {
    private static final String DEFAULT_FILE_NAME = "adjustments/ignores.txt";

    private List<String> ignores;

    /**
     * Initialize the class and prepare the merger list from the default file.
     */
    public Ignorer() {
        this(DEFAULT_FILE_NAME);
    }

    /**
     * Initialize the class and prepare the ignore list from a file.
     */
    public Ignorer(String fileName) {
        ignores = new ArrayList<>();
        fileName = (fileName == null) ? DEFAULT_FILE_NAME : fileName;
        this.loadIgnores(fileName);
    }

    /**
     * Load the list of artists that should be ignored.
     *
     * @param fileName Path to the file for ignores.  Expected to not be null.
     */
    protected void loadIgnores(String fileName) {
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream stream = loader.getResourceAsStream(fileName);

            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            String inputLine;

            while ((inputLine = br.readLine()) != null) {
                if (inputLine.startsWith("#") || inputLine.trim().length() == 0) {
                    continue;
                }

                inputLine = inputLine.trim();
                ignores.add(inputLine);
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Remove entries that are marked to be ignored.
     *
     * @param artists List of artists. List is altered in place.  Expected to not be null.
     */
    protected void ignoreArtists(Collection<Artist> artists) {
        Map<String, Artist> artistMap = new HashMap<>();
        artists.forEach(artist -> artistMap.put(artist.getArtist(), artist));

        ignores.forEach(ignore -> {
            Artist artist = artistMap.get(ignore);
            artists.remove(artist);
        });
    }

    /**
     * {@inheritDoc}
     */
    public Collection<Artist> adjust(Collection<Artist> artists) {
        if (artists != null) {
            ignoreArtists(artists);
        }
        return artists;
    }

    /**
     * Overwrite the ignores list.  For testing only.
     *
     * @param ignores The ignores to set.
     */
    protected void setIgnores(List<String> ignores) {
        this.ignores = ignores;
    }
}
