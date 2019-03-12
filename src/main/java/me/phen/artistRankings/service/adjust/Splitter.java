// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.artistRankings.service.adjust;

import me.phen.artistRankings.model.Artist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Split Last.fm artists together into multiple entries.
 *
 * @author Patrick W. Henstebeck
 * @since 2018-02-11 Su
 */
public class Splitter implements Adjuster {
    private static final String DEFAULT_FILE_NAME = "adjustments/splits.txt";

    private final Map<String, List<String>> splits;

    /**
     * Initialize the class and prepare the merger list from the default file.
     */
    public Splitter() {
        this(DEFAULT_FILE_NAME);
    }

    /**
     * Initialize the class and prepare the merger list from a file.
     */
    public Splitter(String fileName) {
        splits = new HashMap<>();
        this.loadSplits(fileName);
    }

    /**
     * Load the list of artists that should be merged into other artist records.
     */
    protected void loadSplits(String fileName) {
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream stream = loader.getResourceAsStream(fileName);

            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            String inputLine;

            while ((inputLine = br.readLine()) != null) {
                if (inputLine.startsWith("#") || inputLine.trim().length() == 0) {
                    continue;
                }

                String[] propertySplits = inputLine.split("=");

                if (propertySplits.length == 1) {
                    continue;
                }

                String source = propertySplits[0].trim();
                List<String> destinations = Arrays.asList(propertySplits[1].trim().split("~"));
                destinations = destinations.stream().map(String::trim).collect(Collectors.toList());

                splits.put(source, destinations);
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Combine entries that have the same name after our modifications.
     *
     * @param artists List of artists. List is altered in place.
     */
    protected void splitArtists(Collection<Artist> artists) {
        Map<String, Artist> artistMap = new HashMap<>();
        artists.forEach(artist -> artistMap.put(artist.getArtist(), artist));

        splits.forEach((key, values) -> {
            Artist source = artistMap.get(key);
            if (source == null) {
                return;
            }

            values.forEach(value -> {
                        Artist destination = artistMap.get(value);
                        if (destination == null) {
                            destination = new Artist(value);
                            artistMap.put(value, destination);
                            artists.add(destination);
                        }

                        destination.merge(source);
                    });

            artistMap.remove(source.getArtist());
            artists.remove(source);
        });

    }

    /**
     * {@inheritDoc}
     */
    public Collection<Artist> adjust(Collection<Artist> artists) {
        splitArtists(artists);
        return artists;
    }
}
