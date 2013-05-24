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
public class UbicacionProductoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idproducto")
    private int idproducto;
    @Basic(optional = false)
    @Column(name = "idseccion")
    private int idseccion;
    @Basic(optional = false)
    @Column(name = "idestante")
    private int idestante;
    @Basic(optional = false)
    @Column(name = "idestanteria")
    private int idestanteria;

    public UbicacionProductoPK() {
    }

    public UbicacionProductoPK(int idproducto, int idseccion, int idestante, int idestanteria) {
        this.idproducto = idproducto;
        this.idseccion = idseccion;
        this.idestante = idestante;
        this.idestanteria = idestanteria;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public int getIdseccion() {
        return idseccion;
    }

    public void setIdseccion(int idseccion) {
        this.idseccion = idseccion;
    }

    public int getIdestante() {
        return idestante;
    }

    public void setIdestante(int idestante) {
        this.idestante = idestante;
    }

    public int getIdestanteria() {
        return idestanteria;
    }

    public void setIdestanteria(int idestanteria) {
        this.idestanteria = idestanteria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idproducto;
        hash += (int) idseccion;
        hash += (int) idestante;
        hash += (int) idestanteria;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UbicacionProductoPK)) {
            return false;
        }
        UbicacionProductoPK other = (UbicacionProductoPK) object;
        if (this.idproducto != other.idproducto) {
            return false;
        }
        if (this.idseccion != other.idseccion) {
            return false;
        }
        if (this.idestante != other.idestante) {
            return false;
        }
        if (this.idestanteria != other.idestanteria) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.back.hibernate.model.UbicacionProductoPK[ idproducto=" + idproducto + ", idseccion=" + idseccion + ", idestante=" + idestante + ", idestanteria=" + idestanteria + " ]";
    }
    
}
