// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.artistRankings.server.resource;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import me.phen.artistRankings.export.Exporter;
import me.phen.artistRankings.model.ArtistSnapshot;
import me.phen.artistRankings.service.Ranker;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.YearMonth;
import java.util.List;

@Path("/ranks")
@Api(value = "/ranks", description = "Ranks")
@Produces(MediaType.TEXT_PLAIN)
public class RanksResource {

    @Inject
    protected Ranker ranker;

    @Inject
    protected Exporter<String> textExporter;

    @GET
    @Timed
    @Path("/year-ending/year/{year}/month/{month}/text")
    @ApiOperation(
            value = "Get yearly rankings in text format.",
            response = String.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid ID supplied."),
            @ApiResponse(code = 500, message = "Unknown error.")
    })
    public String getYearlyRanksInTextFormat(@PathParam("year") int year, @PathParam("month") int month) {
        YearMonth yearEnding = YearMonth.of(year, month);
        List<ArtistSnapshot> rankings = ranker.getYearlyRankings(yearEnding);
        return textExporter.export(rankings, 30);
    }
}
