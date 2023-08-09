// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.artistRankings.server.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionHandler implements ExceptionMapper<Exception> {

    private static final Logger log = LoggerFactory.getLogger(ExceptionHandler.class);

    @Override
    public Response toResponse(Exception e) {
        log.error(e.getMessage(), e);
        return Response.serverError().status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).type("text/plain").build();
    }
}
