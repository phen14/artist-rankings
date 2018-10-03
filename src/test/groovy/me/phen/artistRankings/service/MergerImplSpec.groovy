package me.phen.artistRankings.service

import me.phen.artistRankings.model.Artist
import me.phen.artistRankings.model.Track
import spock.lang.Specification

import java.time.LocalDateTime

/**
 * Test of the MergerImpl class.
 *
 * @author Patrick W. Henstebeck
 * @since 2018-07-29 Su
 */
class MergerImplSpec extends Specification {

    MergerImpl merger;

    def setup() {
        merger = new MergerImpl();
    }

    def cleanup() {
    }

    def "Test merging with and without leading 'The'"() {
        setup:
        Artist the = new Artist("The Beatles");
        Artist noThe = new Artist("Beatles");

        when:
        Collection<Artist> merged = merger.merge([the, noThe] as Set);

        then:
        merged != null;
        merged.size() == 1;
    }

    def "Test merging artist with multiple names."() {
        setup:
        Artist the = new Artist("The Rascals");
        Artist noThe = new Artist("Rascals");
        Artist otherNameThe = new Artist("The Young Rascals");
        Artist otherNameNoThe = new Artist("Young Rascals");

        when:
        Collection<Artist> merged = merger.merge([the, noThe, otherNameThe, otherNameNoThe] as Set);

        then:
        merged != null;
        merged.size() == 1;
    }

    def "Test merging duplicate names." () {
        Artist one = new Artist("Young Rascals");
        Artist two = new Artist("Young Rascals");
        Artist three = new Artist("Young Rascals");

        when:
        Collection<Artist> merged = merger.merge([one, two, three] as Set);

        then:
        merged != null;
        merged.size() == 1;
    }

    def "Test merging artist with multiple names, populated month maps."() {
        setup:
        Artist the = new Artist("The Rascals");
        Artist noThe = new Artist("Rascals");
        Artist otherNameThe = new Artist("The Young Rascals");
        Artist otherNameNoThe = new Artist("Young Rascals");

        the.addTrack(new Track("", "", LocalDateTime.now()));

        noThe.addTrack(new Track("", "", LocalDateTime.now().minusDays(1L)));
        noThe.addTrack(new Track("", "", LocalDateTime.now().minusDays(2L)));

        otherNameThe.addTrack(new Track("", "", LocalDateTime.now().minusDays(3L)));
        otherNameThe.addTrack(new Track("", "", LocalDateTime.now().minusDays(4L)));
        otherNameThe.addTrack(new Track("", "", LocalDateTime.now().minusDays(5L)));

        otherNameNoThe.addTrack(new Track("", "", LocalDateTime.now().minusDays(6L)));
        otherNameNoThe.addTrack(new Track("", "", LocalDateTime.now().minusDays(7L)));
        otherNameNoThe.addTrack(new Track("", "", LocalDateTime.now().minusDays(8L)));
        otherNameNoThe.addTrack(new Track("", "", LocalDateTime.now().minusDays(9L)));

        when:
        Collection<Artist> merged = merger.merge([the, noThe, otherNameThe, otherNameNoThe] as Set);

        then:
        merged != null;
        merged.size() == 1;
    }
}
