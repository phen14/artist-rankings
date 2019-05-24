// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.artistRankings.server.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Representation of an artist in the rankings.
 *
 * @author Patrick W. Henstebeck
 * @since 2019-05-24 (Fr)
 */
@ApiModel(value = "RankedArtist", description = "Representation of an artist in the rankings.")
@JsonIgnoreProperties({"metaClass"})
public class RankedArtist {

    @ApiModelProperty(value = "Count in latest sub-period.")
    private long added;

    @ApiModelProperty(value = "Change in plays since last ranking.")
    private long change;

    @ApiModelProperty(value = "Total Play Count", required = true)
    private long count;

    @ApiModelProperty(value = "Count in first sub-period")
    private long defending;

    @ApiModelProperty(value = "Name", required = true)
    private String name;

    @ApiModelProperty(value = "Rank", required = true)
    private int rank;

    /**
     *
     */
    public RankedArtist() {
    }

    /**
     *
     * @param name Artist name
     * @param rank Rank in the rankings.
     * @param count Total number of plays.
     * @param change Change in plays since the last ranking period.
     * @param defending Plays in the first sub-period of the ranking period.
     * @param added Plays in the last sub-period of the ranking period.
     */
    public RankedArtist(String name, int rank, long count, long change, long defending, long added) {
        this.name = name;
        this.rank = rank;
        this.count = count;
        this.change = change;
        this.defending = defending;
        this.added = added;
    }

    /**
     * @return Returns the added.
     */
    public long getAdded() {
        return added;
    }

    /**
     * @param added The added to set.
     */
    public void setAdded(long added) {
        this.added = added;
    }

    /**
     * @return Returns the change.
     */
    public long getChange() {
        return change;
    }

    /**
     * @param change The change to set.
     */
    public void setChange(long change) {
        this.change = change;
    }

    /**
     * @return Returns the count.
     */
    public long getCount() {
        return count;
    }

    /**
     * @param count The count to set.
     */
    public void setCount(long count) {
        this.count = count;
    }

    /**
     * @return Returns the defending.
     */
    public long getDefending() {
        return defending;
    }

    /**
     * @param defending The defending to set.
     */
    public void setDefending(long defending) {
        this.defending = defending;
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Returns the rank.
     */
    public int getRank() {
        return rank;
    }

    /**
     * @param rank The rank to set.
     */
    public void setRank(int rank) {
        this.rank = rank;
    }
}
