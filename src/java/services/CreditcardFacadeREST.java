/*
 * Copyright (c)2014
 */

package services;

import entities.Creditcard;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Va Y.
 */
@Stateless
@Path("entities.creditcard")
public class CreditcardFacadeREST extends AbstractFacade<Creditcard> {
    @PersistenceContext(unitName = "PaymentGatewayWebPU")
    private EntityManager em;

    public CreditcardFacadeREST() {
        super(Creditcard.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Creditcard entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Creditcard entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Creditcard find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Creditcard> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Creditcard> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @GET
    @Path("{amount}")
    @Consumes({"application/xml", "application/json"})
    public boolean validate(@PathParam("amount") double amount, Creditcard entity) {
        
        
        return amount>0;
    }
    
}
