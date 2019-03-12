package me.phen.artistRankings.service.adjust;

import me.phen.artistRankings.model.Artist;

import java.util.Collection;

/**
 * @author Patrick W. Henstebeck
 * @since 2018-10-04 Th
 */
public class ArticleFixer implements Adjuster {

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
                artist.setArtist(
                        artist.getArtist()
                                .replaceAll(" The ", " the ")
                                .replaceAll(" And ", " & ")
                                .replaceAll(" and ", " & ")
                )
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
     * {@inheritDoc}
     */
    public Collection<Artist> adjust(Collection<Artist> artists) {
        trim(artists);
        removeLeadingThe(artists);
        lowercaseArticles(artists);
        return artists;
    }
}
