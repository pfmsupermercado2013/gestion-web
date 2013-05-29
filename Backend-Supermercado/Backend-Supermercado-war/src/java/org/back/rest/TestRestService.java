/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.back.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.back.hibernate.SupermercadoEntityService;

/**
 *
 * @author alejandrogarcia
 */
@Path("/test")
public class TestRestService {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response login() {

        SupermercadoEntityService supermercadoEntityService = new SupermercadoEntityService();
        try {
            supermercadoEntityService.crearSupermercado("SuperMarket", "C/ inventada 66", "Madrid", "Madrid");
        } catch (Exception e) {
            e.printStackTrace();
            return Response.ok("Fail!").build();
        }

        return Response.ok("OK!").build();
    }
}