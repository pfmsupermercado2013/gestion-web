package org.back.rest.interceptor;

import java.util.List;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;
import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.core.ResourceMethod;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;

/**
 *
 * @author Alejandro Garcia
 */
@Provider
@ServerInterceptor
public class SubastasAuthenticator implements PreProcessInterceptor {

    @Override
    public ServerResponse preProcess(HttpRequest request, ResourceMethod rm) throws Failure, WebApplicationException {

        ServerResponse response = null;
        //Autenticacion para los servicion de subastas
        if (request.getPreprocessedPath().startsWith("/subastas")) {
            System.out.println("Peticion a subasta");
            List<String> authorization = request.getHttpHeaders().getRequestHeader("Authorization");
            System.out.println("AUTHORIZATION SUBASTAS: "+authorization);
        }
        return response;
    }
}
