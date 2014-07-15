/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vyim
 */
@Entity
@Table(name = "CreditAccount")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CreditAccount.findAll", query = "SELECT c FROM CreditAccount c"),
    @NamedQuery(name = "CreditAccount.findById", query = "SELECT c FROM CreditAccount c WHERE c.id = :id")
 })
public class CreditAccount implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "CardNumber")
    private String id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CreditLimit")
    private double CreditLimit;
    
    public CreditAccount() {
    }
    
    public CreditAccount(String cardNumber, double creditLimit) {
        
        this.id = cardNumber;
        this.CreditLimit = creditLimit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getCreditLimit() {
        return CreditLimit;
    }

    public void setCreditLimit(double amount) {
        this.CreditLimit = amount;
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
        if (!(object instanceof CreditAccount)) {
            return false;
        }
        CreditAccount other = (CreditAccount) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CreditAccount[ id=" + id + " ]";
    }
    
}
