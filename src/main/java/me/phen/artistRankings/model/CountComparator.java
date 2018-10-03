// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.artistRankings.model;

import java.util.Comparator;

/**
 * @author Patrick W. Henstebeck
 * @since 2017-12-31 Su
 */
public class CountComparator implements Comparator<ArtistSnapshot>{

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(ArtistSnapshot o1, ArtistSnapshot o2) {
        if (o1 == null && o2 == null) {
            return 0;
        }
        if (o1 == null) {
            return 1;
        }
        if (o2 == null) {
            return -1;
        }

        Integer o1count = Integer.valueOf(o1.getCountNow());
        Integer o2count = Integer.valueOf(o2.getCountNow());

        int baseCompare = o2count.compareTo(o1count);
        if (baseCompare != 0) {
            return baseCompare;
        }

        // Worse change gets better ranking - You must beat the king to become the king.
        o1count = Integer.valueOf(o1.getCountLast());
        o2count = Integer.valueOf(o2.getCountLast());

        return o2count.compareTo(o1count);
    }
}
