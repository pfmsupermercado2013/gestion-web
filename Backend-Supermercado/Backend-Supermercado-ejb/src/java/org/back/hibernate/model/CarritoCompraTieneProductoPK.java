/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.back.hibernate.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author ÓscarJavier
 */
@Embeddable
public class CarritoCompraTieneProductoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idCarritoCompra")
    private int idCarritoCompra;
    @Basic(optional = false)
    @Column(name = "idproducto")
    private int idproducto;

    public CarritoCompraTieneProductoPK() {
    }

    public CarritoCompraTieneProductoPK(int idCarritoCompra, int idproducto) {
        this.idCarritoCompra = idCarritoCompra;
        this.idproducto = idproducto;
    }

    public int getIdCarritoCompra() {
        return idCarritoCompra;
    }

    public void setIdCarritoCompra(int idCarritoCompra) {
        this.idCarritoCompra = idCarritoCompra;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idCarritoCompra;
        hash += (int) idproducto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CarritoCompraTieneProductoPK)) {
            return false;
        }
        CarritoCompraTieneProductoPK other = (CarritoCompraTieneProductoPK) object;
        if (this.idCarritoCompra != other.idCarritoCompra) {
            return false;
        }
        if (this.idproducto != other.idproducto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.back.hibernate.model.CarritoCompraTieneProductoPK[ idCarritoCompra=" + idCarritoCompra + ", idproducto=" + idproducto + " ]";
    }
    
}
