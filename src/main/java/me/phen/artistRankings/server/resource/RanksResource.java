// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.artistRankings.server.resource;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import me.phen.artistRankings.export.Exporter;
import me.phen.artistRankings.model.ArtistSnapshot;
import me.phen.artistRankings.server.model.HtmlResponse;
import me.phen.artistRankings.server.model.Rankings;
import me.phen.artistRankings.service.Ranker;

import java.time.YearMonth;
import java.util.List;

@Path("/ranks")
@Produces(MediaType.APPLICATION_JSON)
public class RanksResource {

    @Inject
    protected Ranker ranker;

    @Inject
    protected Exporter<Rankings> jsonExporter;

    @Inject
    protected Exporter<HtmlResponse> htmlExporter;

    @Inject
    protected Exporter<String> textExporter;

    @GET
    @Timed
    @Path("/year-ending/year/{year}/month/{month}/json")
    @Operation(
            summary = "Get yearly rankings in JSON format.",
            responses = {@ApiResponse(content = @Content(schema = @Schema(implementation = Rankings.class)))}
    )
    public Rankings getYearlyRanksInJsonFormat(@PathParam("year") int year, @PathParam("month") int month) {
        YearMonth yearEnding = YearMonth.of(year, month);
        List<ArtistSnapshot> rankings = ranker.getYearlyRankings(yearEnding);
        return jsonExporter.export(rankings, 30);
    }

    @GET
    @Timed
    @Path("/year-ending/year/{year}/month/{month}/text")
    @Operation(
            summary = "Get yearly rankings in text format.",
            responses = {@ApiResponse(content = @Content(schema = @Schema(implementation = String.class)))}
    )
    public String getYearlyRanksInTextFormat(@PathParam("year") int year, @PathParam("month") int month) {
        YearMonth yearEnding = YearMonth.of(year, month);
        List<ArtistSnapshot> rankings = ranker.getYearlyRankings(yearEnding);
        return textExporter.export(rankings, 30);
    }

    @GET
    @Timed
    @Path("/year-ending/year/{year}/month/{month}/html")
    @Operation(
            summary = "Get yearly rankings in HTML format.",
            responses = {@ApiResponse(content = @Content(schema = @Schema(implementation = String.class)))}
    )
    public String getYearlyRanksInHtmlFormat(@PathParam("year") int year, @PathParam("month") int month) {
        YearMonth yearEnding = YearMonth.of(year, month);
        List<ArtistSnapshot> rankings = ranker.getYearlyRankings(yearEnding);
        return htmlExporter.export(rankings, 30).getHtml();
    }
}
