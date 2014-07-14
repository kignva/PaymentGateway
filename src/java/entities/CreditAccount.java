/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
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
    @NamedQuery(name = "CreditAccount.findById", query = "SELECT c FROM CreditAccount c WHERE c.id = :id"),
    @NamedQuery(name = "CreditAccount.findByAmount", query = "SELECT c FROM CreditAccount c WHERE c.amount = :amount")})
public class CreditAccount implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Amount")
    private Float amount;
    
    @JoinColumn(name = "CardNumber", referencedColumnName = "CARDNUMBER")
    @OneToOne(optional = false)
    private CreditCard card;

    public CreditAccount() {
    }
    
//    public CreditAccount(CreditCard card) {
//        
//        this.cardNumber = card;
//    }

    public CreditAccount(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public CreditCard getCard() {
        return card;
    }

    public void setCard(CreditCard card) {
        this.card = card;
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
