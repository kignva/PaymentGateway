/*
 * Copyright (c)2014
 */

package services;

import entities.CreditCard;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Va Y.
 */
public class CreditcardFacadeRESTTest {
    
    public CreditcardFacadeRESTTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    
    /**
     * Test of create method, of class CreditcardFacadeREST.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        CreditCard entity = new CreditCard();
        entity.setCardNumber("555");
        entity.setCardholderName("Unit Test");
        entity.setExpiryDate("11/11");
        entity.setSecurityCode("000");
        
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        CreditcardFacadeREST instance = (CreditcardFacadeREST)container.getContext().lookup("java:global/classes/CreditcardFacadeREST");
        instance.create(entity);
        container.close();
        
    }

    

    /**
     * Test of validate method, of class CreditcardFacadeREST.
     */
    @Test
    public void testValidate() throws Exception {
        System.out.println("validate");
        String cardnumber = "1234567891234558";
        String holdername = "wrw";
        String expiredate = "12/14";
        String securecode = "123";
        double amount = 0.0;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        CreditcardFacadeREST instance = (CreditcardFacadeREST)container.getContext().lookup("java:global/classes/CreditcardFacadeREST");
        boolean expResult = false;
        boolean result = instance.validate(cardnumber, holdername, expiredate, securecode, amount);
        assertEquals(expResult, result);
        container.close();
        
    }

    /**
     * Test of withdraw method, of class CreditcardFacadeREST.
     */
    @Test
    public void testWithdraw() throws Exception {
        System.out.println("withdraw");
        String cardnumber = "1234567891234558";
        String holdername = "wrw";
        String expiredate = "12/14";
        String securecode = "123";
        double amount = 110.0;
        boolean withdraw = false;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        CreditcardFacadeREST instance = (CreditcardFacadeREST)container.getContext().lookup("java:global/classes/CreditcardFacadeREST");
        double expResult = 0.0;
        double result = instance.withdraw(cardnumber, holdername, expiredate, securecode, amount, withdraw);
        assertEquals(expResult, result, 0.0);
        container.close();
        
    }
    
}
