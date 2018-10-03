// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.artistRankings.model;

/**
 * @author Patrick W. Henstebeck
 * @since 2017-12-31 Su
 */
public class ArtistSnapshot {

    private String artist;

    private int countLast;
    private int countNow;

    private int rankLast;
    private int rankNow;

    private int added;
    private int defending;


    // =================================================================================================================
    // -----| Constructors |-----
    // ==========================

    public ArtistSnapshot(String artist) {
        this.artist = artist;
    }

    public ArtistSnapshot(String artist, int countNow, int countLast) {
        this.artist = artist;
        this.countNow = countNow;
        this.countLast = countLast;
    }


    // =================================================================================================================
    // -----| Getters & Setters |-----
    // ===============================

    public String getArtist() {
        return artist;
    }

    public int getCountLast() {
        return countLast;
    }

    public void setCountLast(int countLast) {
        this.countLast = countLast;
    }

    public int getCountNow() {
        return countNow;
    }

    public void setCountNow(int countNow) {
        this.countNow = countNow;
    }

    public int getRankLast() {
        return rankLast;
    }

    public void setRankLast(int rankLast) {
        this.rankLast = rankLast;
    }

    public int getRankNow() {
        return rankNow;
    }

    public void setRankNow(int rankNow) {
        this.rankNow = rankNow;
    }

    public int getAdded() {
        return added;
    }

    public void setAdded(int added) {
        this.added = added;
    }

    public int getDefending() {
        return defending;
    }

    public void setDefending(int defending) {
        this.defending = defending;
    }


    // =================================================================================================================
    // -----| Object |-----
    // ====================

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ArtistSnapshot that = (ArtistSnapshot) o;

        if (countLast != that.countLast) {
            return false;
        }
        if (countNow != that.countNow) {
            return false;
        }
        if (rankLast != that.rankLast) {
            return false;
        }
        if (rankNow != that.rankNow) {
            return false;
        }
        if (added != that.added) {
            return false;
        }
        if (defending != that.defending) {
            return false;
        }
        return artist != null ? artist.equals(that.artist) : that.artist == null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = artist != null ? artist.hashCode() : 0;
        result = 31 * result + countLast;
        result = 31 * result + countNow;
        result = 31 * result + rankLast;
        result = 31 * result + rankNow;
        result = 31 * result + added;
        result = 31 * result + defending;
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ArtistSnapshot{");
        sb.append("artist='").append(artist).append('\'');
        sb.append(", countLast=").append(countLast);
        sb.append(", countNow=").append(countNow);
        sb.append(", rankLast=").append(rankLast);
        sb.append(", rankNow=").append(rankNow);
        sb.append(", added=").append(added);
        sb.append(", defending=").append(defending);
        sb.append('}');
        return sb.toString();
    }
}
