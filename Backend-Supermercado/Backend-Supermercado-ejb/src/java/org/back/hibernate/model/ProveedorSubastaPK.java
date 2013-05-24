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
public class ProveedorSubastaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idproveedor")
    private int idproveedor;
    @Basic(optional = false)
    @Column(name = "idsubasta")
    private int idsubasta;

    public ProveedorSubastaPK() {
    }

    public ProveedorSubastaPK(int idproveedor, int idsubasta) {
        this.idproveedor = idproveedor;
        this.idsubasta = idsubasta;
    }

    public int getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(int idproveedor) {
        this.idproveedor = idproveedor;
    }

    public int getIdsubasta() {
        return idsubasta;
    }

    public void setIdsubasta(int idsubasta) {
        this.idsubasta = idsubasta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idproveedor;
        hash += (int) idsubasta;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProveedorSubastaPK)) {
            return false;
        }
        ProveedorSubastaPK other = (ProveedorSubastaPK) object;
        if (this.idproveedor != other.idproveedor) {
            return false;
        }
        if (this.idsubasta != other.idsubasta) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.back.hibernate.model.ProveedorSubastaPK[ idproveedor=" + idproveedor + ", idsubasta=" + idsubasta + " ]";
    }
    
}
