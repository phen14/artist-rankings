// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.artistRankings.server.resource;

import com.codahale.metrics.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/ping")
@Produces(MediaType.TEXT_PLAIN)
public class PingResource {

    @GET
    @Timed
    @Path("/")
    @Operation(
            summary = "ping",
            responses = {@ApiResponse(content = @Content(schema = @Schema(implementation = String.class)))}
    )
    public String ping() {
        return "pong";
    }

}
