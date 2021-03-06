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
public class EntradaSupermercadoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "identrada_supermercado")
    private int identradaSupermercado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mapa_supermercado_idmapa_supermercado")
    private int mapaSupermercadoIdmapaSupermercado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mapa_supermercado_idsupermercado")
    private int mapaSupermercadoIdsupermercado;

    public EntradaSupermercadoPK() {
    }

    public EntradaSupermercadoPK(int identradaSupermercado, int mapaSupermercadoIdmapaSupermercado, int mapaSupermercadoIdsupermercado) {
        this.identradaSupermercado = identradaSupermercado;
        this.mapaSupermercadoIdmapaSupermercado = mapaSupermercadoIdmapaSupermercado;
        this.mapaSupermercadoIdsupermercado = mapaSupermercadoIdsupermercado;
    }

    public int getIdentradaSupermercado() {
        return identradaSupermercado;
    }

    public void setIdentradaSupermercado(int identradaSupermercado) {
        this.identradaSupermercado = identradaSupermercado;
    }

    public int getMapaSupermercadoIdmapaSupermercado() {
        return mapaSupermercadoIdmapaSupermercado;
    }

    public void setMapaSupermercadoIdmapaSupermercado(int mapaSupermercadoIdmapaSupermercado) {
        this.mapaSupermercadoIdmapaSupermercado = mapaSupermercadoIdmapaSupermercado;
    }

    public int getMapaSupermercadoIdsupermercado() {
        return mapaSupermercadoIdsupermercado;
    }

    public void setMapaSupermercadoIdsupermercado(int mapaSupermercadoIdsupermercado) {
        this.mapaSupermercadoIdsupermercado = mapaSupermercadoIdsupermercado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) identradaSupermercado;
        hash += (int) mapaSupermercadoIdmapaSupermercado;
        hash += (int) mapaSupermercadoIdsupermercado;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EntradaSupermercadoPK)) {
            return false;
        }
        EntradaSupermercadoPK other = (EntradaSupermercadoPK) object;
        if (this.identradaSupermercado != other.identradaSupermercado) {
            return false;
        }
        if (this.mapaSupermercadoIdmapaSupermercado != other.mapaSupermercadoIdmapaSupermercado) {
            return false;
        }
        if (this.mapaSupermercadoIdsupermercado != other.mapaSupermercadoIdsupermercado) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.back.hibernate.model.EntradaSupermercadoPK[ identradaSupermercado=" + identradaSupermercado + ", mapaSupermercadoIdmapaSupermercado=" + mapaSupermercadoIdmapaSupermercado + ", mapaSupermercadoIdsupermercado=" + mapaSupermercadoIdsupermercado + " ]";
    }
    
}
