/*
 * Copyright (c)2014
 */

package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Va Y.
 */
@Entity
@Table(name = "CreditCard")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Creditcard.findAll", query = "SELECT c FROM CreditCard c"),
    @NamedQuery(name = "Creditcard.findById", query = "SELECT c FROM CreditCard c WHERE c.id = :id"),
    @NamedQuery(name = "Creditcard.findByCardnumber", query = "SELECT c FROM CreditCard c WHERE c.cardNumber = :cardnumber"),
    @NamedQuery(name = "Creditcard.findByCardholdername", query = "SELECT c FROM CreditCard c WHERE c.cardholderName = :cardholdername")
})
public class CreditCard1 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    
    @Column(name = "CARDNUMBER")
    private String cardNumber;
    @Column(name = "CARDHOLDERNAME")
    private String cardholderName;
    @Column(name = "EXPIRYDATE")
    private String expiryDate;
    @Column(name = "SECURITYCODE")
    private String securityCode;
    

    public CreditCard1() {
    }


    public Integer getId() {
        return id;
    }


    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardnumber) {
        this.cardNumber = cardnumber;
    }

    public String getCardholderName() {
        return cardholderName;
    }

    public void setCardholderName(String cardholdername) {
        this.cardholderName = cardholdername;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expirydate) {
        this.expiryDate = expirydate;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securitycode) {
        this.securityCode = securitycode;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CreditCard1)) {
            return false;
        }
        CreditCard1 other = (CreditCard1) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Creditcard[ id=" + id + ", cardNumber=" + cardNumber + " ]";
    }
    
    
    
}
