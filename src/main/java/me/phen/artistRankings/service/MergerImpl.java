// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.artistRankings.service;

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
 * Merge Last.fm artists together into a single Artist.
 *
 * @author Patrick W. Henstebeck
 * @since 2018-02-11 Su
 */
public class MergerImpl implements Merger {
    private static final String MERGERS_FILE = "mergers.txt";

    private final Map<String, String> mergers;

    /**
     * Initialize the class and prepare the merger list from the default file.
     */
    public MergerImpl() {
        this(MERGERS_FILE);
    }

    /**
     * Initialize the class and prepare the merger list from a file.
     */
    public MergerImpl(String fileName) {
        mergers = new HashMap<>();
        this.loadMergers(fileName);
    }

    /**
     * Load the list of artists that should be merged into other artist records.
     */
    protected void loadMergers(String fileName) {
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream stream = loader.getResourceAsStream(fileName);

            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            String inputLine;

            while ((inputLine = br.readLine()) != null) {
                if (inputLine.startsWith("#") || inputLine.trim().length() == 0) {
                    continue;
                }

                String[] splits = inputLine.split("=");

                if (splits.length == 1) {
                    continue;
                }

                String source = splits[0].trim();
                String destination = splits[1].trim();

                mergers.put(source, destination);
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Strip the leading "The " off artist names.
     *
     * @param artists List of artists. List is altered in place.
     */
    protected void removeLeadingThe(Collection<Artist> artists) {
        artists.forEach(artist -> {
            if (artist.getArtist().startsWith("The ")) {
                artist.setArtist(artist.getArtist().substring(4));
            }
        });
    }

    /**
     * Convert all instances of "the" and "and" to lowercase.
     *
     * @param artists List of artists. List is altered in place.
     */
    protected void lowercaseArticles(Collection<Artist> artists) {
        artists.forEach(artist ->
            artist.setArtist(artist.getArtist().replaceAll(" The ", " the ").replaceAll(" And ", " and "))
        );
    }

    /**
     * Remove any leading or trailing whitespace around the artist names.
     *
     * @param artists List of artists. List is altered in place.
     */
    protected void trim(Collection<Artist> artists) {
        artists.forEach(artist -> {
                artist.setArtist(artist.getArtist().trim());
                artist.setArtist(artist.getArtist().replaceAll("\\s", " "));
            }
        );
    }

    /**
     * Combine entries that have the same name after our modifications.
     *
     * @param artists List of artists. List is altered in place.
     */
    protected void mergeMultipleArtistsWithSameName(Collection<Artist> artists) {
        Map<String, List<Artist>> artistMap = new HashMap<>();

        artists.forEach(artist -> {
            String name = artist.getArtist().toLowerCase();
            artistMap.computeIfAbsent(name, it -> new ArrayList<>()).add(artist);
        });

        artistMap.forEach((name, artistsWithThisName) -> {
            if (artistsWithThisName.size() > 1) {
                Artist firstArtist = artistsWithThisName.get(0);
                artistsWithThisName.remove(0);
                for (Artist artist : artistsWithThisName) {
                    firstArtist.merge(artist);
                    artists.remove(artist);
                }
            }
        });
    }

    /**
     * Merge artists that have multiple artist names into a single record.
     *
     * @param artists List of artists. List is altered in place.
     */
    protected void mergeMultipleNameArtists(Collection<Artist> artists) {
        Map<String, Artist> artistMap = new HashMap<>();
        artists.forEach(artist -> artistMap.put(artist.getArtist(), artist));

        mergers.forEach((key, value) -> {
            Artist source = artistMap.get(key);
            if (source == null) {
                return;
            }
            Artist destination = artistMap.get(value);
            if (destination == null) {
                destination = new Artist(value);
                artistMap.put(value, destination);
                artists.add(destination);
            }

            destination.merge(source);
            artistMap.remove(source.getArtist());
            artists.remove(source);
        });
    }

    /**
     * {@inheritDoc}
     */
    public Collection<Artist> merge(Collection<Artist> artists) {
        trim(artists);
        removeLeadingThe(artists);
        lowercaseArticles(artists);
        mergeMultipleArtistsWithSameName(artists);
        mergeMultipleNameArtists(artists);
        return artists;
    }
}