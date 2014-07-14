/*
 * Copyright (c)2014
 */

package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
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
    @NamedQuery(name = "Creditcard.findByDtype", query = "SELECT c FROM CreditCard c WHERE c.dtype = :dtype"),
    @NamedQuery(name = "Creditcard.findByCardnumber", query = "SELECT c FROM CreditCard c WHERE c.cardnumber = :cardnumber"),
    @NamedQuery(name = "Creditcard.findByCardholdername", query = "SELECT c FROM CreditCard c WHERE c.cardholdername = :cardholdername"),
    @NamedQuery(name = "Creditcard.findByExpirydate", query = "SELECT c FROM CreditCard c WHERE c.expirydate = :expirydate"),
    @NamedQuery(name = "Creditcard.findBySecuritycode", query = "SELECT c FROM CreditCard c WHERE c.securitycode = :securitycode")})
public class CreditCard implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 31)
    @Column(name = "DTYPE")
    private String dtype;
    @Size(max = 255)
    @Column(name = "CARDNUMBER")
    private String cardnumber;
    @Size(max = 255)
    @Column(name = "CARDHOLDERNAME")
    private String cardholdername;
    @Size(max = 255)
    @Column(name = "EXPIRYDATE")
    private String expirydate;
    @Size(max = 255)
    @Column(name = "SECURITYCODE")
    private String securitycode;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "card")
    private CreditAccount creditAccount;

    public CreditCard() {
    }

    public CreditCard(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDtype() {
        return dtype;
    }

    public void setDtype(String dtype) {
        this.dtype = dtype;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public String getCardholdername() {
        return cardholdername;
    }

    public void setCardholdername(String cardholdername) {
        this.cardholdername = cardholdername;
    }

    public String getExpirydate() {
        return expirydate;
    }

    public void setExpirydate(String expirydate) {
        this.expirydate = expirydate;
    }

    public String getSecuritycode() {
        return securitycode;
    }

    public void setSecuritycode(String securitycode) {
        this.securitycode = securitycode;
    }
    
    public CreditAccount getCreditAccount() {
        return creditAccount;
    }

    public void setCreditAccount(CreditAccount creditAccount) {
        this.creditAccount = creditAccount;
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
        if (!(object instanceof CreditCard)) {
            return false;
        }
        CreditCard other = (CreditCard) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Creditcard[ id=" + id + " ]";
    }
    
}
