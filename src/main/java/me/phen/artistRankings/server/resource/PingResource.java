// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.artistRankings.server.resource;

import com.codahale.metrics.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/ping")
@Api(value = "/ping", description = "Ping")
@Produces(MediaType.TEXT_PLAIN)
public class PingResource {

    @GET
    @Timed
    @Path("/")
    @ApiOperation(
            value = "ping",
            response = String.class
    )
    public String ping() {
        return "pong";
    }

}
