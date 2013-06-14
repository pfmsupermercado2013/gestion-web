/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.back.hibernate.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ÓscarJavier
 */
@Embeddable
public class MapaSupermercadoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idmapa_supermercado")
    private int idmapaSupermercado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "supermercado_idsupermercado")
    private int supermercadoIdsupermercado;

    public MapaSupermercadoPK() {
    }

    public MapaSupermercadoPK(int idmapaSupermercado, int supermercadoIdsupermercado) {
        this.idmapaSupermercado = idmapaSupermercado;
        this.supermercadoIdsupermercado = supermercadoIdsupermercado;
    }

    public int getIdmapaSupermercado() {
        return idmapaSupermercado;
    }

    public void setIdmapaSupermercado(int idmapaSupermercado) {
        this.idmapaSupermercado = idmapaSupermercado;
    }

    public int getSupermercadoIdsupermercado() {
        return supermercadoIdsupermercado;
    }

    public void setSupermercadoIdsupermercado(int supermercadoIdsupermercado) {
        this.supermercadoIdsupermercado = supermercadoIdsupermercado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idmapaSupermercado;
        hash += (int) supermercadoIdsupermercado;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MapaSupermercadoPK)) {
            return false;
        }
        MapaSupermercadoPK other = (MapaSupermercadoPK) object;
        if (this.idmapaSupermercado != other.idmapaSupermercado) {
            return false;
        }
        if (this.supermercadoIdsupermercado != other.supermercadoIdsupermercado) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.back.hibernate.model.MapaSupermercadoPK[ idmapaSupermercado=" + idmapaSupermercado + ", supermercadoIdsupermercado=" + supermercadoIdsupermercado + " ]";
    }
    
}
