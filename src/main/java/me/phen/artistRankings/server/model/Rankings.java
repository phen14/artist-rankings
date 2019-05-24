// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.artistRankings.server.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/**
 * Artist Rankings for a specified period.
 *
 * @author Patrick W. Henstebeck
 * @since 2019-05-24 (Fr)
 */
@ApiModel(value = "Rankings", description = "Artist Rankings for a specified period.")
@JsonIgnoreProperties({"metaClass"})
public class Rankings {

    @ApiModelProperty(value = "Name", required = true)
    private LocalDate end;

    @ApiModelProperty(value = "Name", required = true)
    private LocalDate start;

    @ApiModelProperty(value = "Rank", required = true)
    private List<RankedArtist> rankings;

    /**
     *
     */
    public Rankings() {
    }

    /**
     * @return Returns the end.
     */
    public LocalDate getEnd() {
        return end;
    }

    /**
     * @param end The end to set.
     */
    public void setEnd(LocalDate end) {
        this.end = end;
    }

    /**
     * @return Returns the start.
     */
    public LocalDate getStart() {
        return start;
    }

    /**
     * @param start The start to set.
     */
    public void setStart(LocalDate start) {
        this.start = start;
    }

    /**
     * @return Returns the rankings.
     */
    public List<RankedArtist> getRankings() {
        return rankings;
    }

    /**
     * @param rankings The rankings to set.
     */
    public void setRankings(List<RankedArtist> rankings) {
        this.rankings = rankings;
    }

    /**
     * Add an artist to the next spot in the rankings.
     *
     * @param artist Artist to add.
     */
    public void addArtist(RankedArtist artist) {
        if (this.rankings == null) {
            this.rankings = new LinkedList<>();
        }
        this.rankings.add(artist);
    }
}
