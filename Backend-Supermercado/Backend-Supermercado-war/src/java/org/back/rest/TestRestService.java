package org.back.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Alejandro Garcia
 */
@Path("/test")
public class TestRestService {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response login() {
        return Response.ok("Teting rest").build();
    }
    
}