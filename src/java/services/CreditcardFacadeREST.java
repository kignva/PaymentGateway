/*
 * Copyright (c)2014
 */

package services;

import entities.CreditAccount;
import entities.CreditCard;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
public class CreditcardFacadeREST extends AbstractFacade<CreditCard> {
    @PersistenceContext(unitName = "PaymentGatewayWebPU")
    private EntityManager em;

    public CreditcardFacadeREST() {
        super(CreditCard.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(CreditCard entity) {
        
        if (entity == null) return;
        
        System.out.println("Create CreditCard: " + entity);
        
        TypedQuery<CreditCard> query = em.createNamedQuery("Creditcard.findByCardnumber", CreditCard.class);
        query.setParameter("cardnumber", entity.getCardNumber());
        List<CreditCard> cards = query.getResultList();
        if (cards.size()>0) return;
        
        //Adding into CreditAccount
        CreditAccount account = new CreditAccount(entity.getCardNumber(), 500);
        em.persist(account);
        
        System.out.println("CreditAccount added.");
        
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, CreditCard entity) {
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
    public CreditCard find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<CreditCard> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<CreditCard> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    @Path("{cardnumber}/{holdername}/{expireddate}/{securecode}/{amount}")
    @Produces("text/plain")
    public boolean validate(
            @PathParam("cardnumber") String cardnumber,
            @PathParam("holdername") String holdername,
            @PathParam("expireddate") String expiredate,
            @PathParam("securecode") String securecode,
            @PathParam("amount") double amount
            ) {
        
        TypedQuery<CreditCard> query = em.createNamedQuery("Creditcard.findByCardnumber", CreditCard.class);
        query.setParameter("cardnumber", cardnumber);
        List<CreditCard> cards = query.getResultList();
        
        if (cards.size()<=0) return false;
        if (cards.get(0).getCardholderName() != null &&
                cards.get(0).getCardholderName().toUpperCase().compareTo(holdername.toUpperCase()) != 0) return false;
        if (cards.get(0).getExpiryDate() != null &&
                cards.get(0).getExpiryDate().compareTo(expiredate) != 0) return false;
        if (cards.get(0).getSecurityCode() != null &&
                cards.get(0).getSecurityCode().compareTo(securecode) != 0) return false;
        
        //check credit limit
        CreditAccount account = em.find(CreditAccount.class, cardnumber);
        if (account == null) return false;
        if (account.getCreditLimit()< amount) return false;
        
        return true;
    }
    
    @GET
    @Path("{cardnumber}/{holdername}/{expireddate}/{securecode}/{amount}/{withdraw}")
    @Produces("text/plain")
    public double withdraw(
            @PathParam("cardnumber") String cardnumber,
            @PathParam("holdername") String holdername,
            @PathParam("expireddate") String expiredate,
            @PathParam("securecode") String securecode,
            @PathParam("amount") double amount,
            @PathParam("withdraw") boolean withdraw
            ) {
        
        if (!withdraw) return 0;
        
        if (!validate(cardnumber, holdername, expiredate, securecode, amount)) return 0;
        
        CreditAccount account = em.find(CreditAccount.class, cardnumber);
        if (account == null) return 0;
        account.setCreditLimit(account.getCreditLimit() - amount);
        em.persist(account);
    
        return amount;
    }
    
}
