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
import org.back.hibernate.model.Supermercado;
import org.back.test.EntityFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author alejandrogarcia
 */
@Path( "/test" )
public class TestRestService {
    
   @GET
   @Produces(MediaType.TEXT_HTML)
   public Response login()  {
      
       Session session = null;
       
       try{
       
        session = EntityFactory.getSession();

        Transaction tx = session.beginTransaction();

        Supermercado supermercado = new Supermercado();
        supermercado.setNombreSupermercado("DIA");
        supermercado.setDireccionSupermercado("C/ Alpujarras 24");
        supermercado.setLocalidadSupermercado("Madrid");
        supermercado.setProvinciaSupermercado("Madrid");
        //Guardando
        session.save(supermercado);

        tx.commit();
       
       }catch(Exception e){
           e.printStackTrace();
            return Response.ok( "Fail!" ).build();
       }finally{
           if(session != null){
            session.close();
           }
           
       } 
      return Response.ok( "OK!" ).build();
   }

}