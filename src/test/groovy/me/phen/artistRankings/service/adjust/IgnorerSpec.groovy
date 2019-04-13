package me.phen.artistRankings.service.adjust

import me.phen.artistRankings.model.Artist
import spock.lang.Specification

/**
 * Test of the Ignorer class.
 *
 * @author Patrick W. Henstebeck
 * @since 2019-04-13 (Sa)
 */
class IgnorerSpec extends Specification {
    private static final Artist MARIT = new Artist("Marit Larsen");
    private static final Artist KYLIE = new Artist("Kylie Minogue");
    private static final Artist BEATLES = new Artist("Beatles");

    private static final String RPI_NAME = "RPI Pep Band";
    private static final Artist RPI = new Artist(RPI_NAME);

    Ignorer ignorer;

    def setup() {
        ignorer = new Ignorer();
        ignorer.setIgnores([RPI_NAME]);
    }

    def cleanup() {
    }


    def "No artist to ignore."() {
        setup:
        List<Artist> artists = [MARIT, KYLIE, BEATLES];

        when:
        ignorer.adjust(artists);

        then:
        artists != null;
        artists.size() == 3
        artists.containsAll([MARIT, KYLIE, BEATLES]);
    }

    def "Artist to ignore."() {
        setup:
        List<Artist> artists = [MARIT, KYLIE, RPI, BEATLES];

        when:
        ignorer.adjust(artists);

        then:
        artists != null;
        artists.size() == 3
        artists.containsAll([MARIT, KYLIE, BEATLES]);
    }

}
