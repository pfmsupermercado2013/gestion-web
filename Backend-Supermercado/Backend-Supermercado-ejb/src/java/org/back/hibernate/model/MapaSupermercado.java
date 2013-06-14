/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.back.hibernate.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author ÓscarJavier
 */
@Entity
@Table(name = "mapa_supermercado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MapaSupermercado.findAll", query = "SELECT m FROM MapaSupermercado m"),
    @NamedQuery(name = "MapaSupermercado.findByIdmapaSupermercado", query = "SELECT m FROM MapaSupermercado m WHERE m.mapaSupermercadoPK.idmapaSupermercado = :idmapaSupermercado"),
    @NamedQuery(name = "MapaSupermercado.findByAncho", query = "SELECT m FROM MapaSupermercado m WHERE m.ancho = :ancho"),
    @NamedQuery(name = "MapaSupermercado.findByLargo", query = "SELECT m FROM MapaSupermercado m WHERE m.largo = :largo"),
    @NamedQuery(name = "MapaSupermercado.findBySupermercadoIdsupermercado", query = "SELECT m FROM MapaSupermercado m WHERE m.mapaSupermercadoPK.supermercadoIdsupermercado = :supermercadoIdsupermercado")})
public class MapaSupermercado implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MapaSupermercadoPK mapaSupermercadoPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ancho")
    private Float ancho;
    @Column(name = "largo")
    private Float largo;
    @JoinColumn(name = "supermercado_idsupermercado", referencedColumnName = "idsupermercado", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Supermercado supermercado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mapaSupermercado")
    private Collection<EntradaSupermercado> entradaSupermercadoCollection;

    public MapaSupermercado() {
    }

    public MapaSupermercado(MapaSupermercadoPK mapaSupermercadoPK) {
        this.mapaSupermercadoPK = mapaSupermercadoPK;
    }

    public MapaSupermercado(int idmapaSupermercado, int supermercadoIdsupermercado) {
        this.mapaSupermercadoPK = new MapaSupermercadoPK(idmapaSupermercado, supermercadoIdsupermercado);
    }

    public MapaSupermercadoPK getMapaSupermercadoPK() {
        return mapaSupermercadoPK;
    }

    public void setMapaSupermercadoPK(MapaSupermercadoPK mapaSupermercadoPK) {
        this.mapaSupermercadoPK = mapaSupermercadoPK;
    }

    public Float getAncho() {
        return ancho;
    }

    public void setAncho(Float ancho) {
        this.ancho = ancho;
    }

    public Float getLargo() {
        return largo;
    }

    public void setLargo(Float largo) {
        this.largo = largo;
    }

    public Supermercado getSupermercado() {
        return supermercado;
    }

    public void setSupermercado(Supermercado supermercado) {
        this.supermercado = supermercado;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<EntradaSupermercado> getEntradaSupermercadoCollection() {
        return entradaSupermercadoCollection;
    }

    public void setEntradaSupermercadoCollection(Collection<EntradaSupermercado> entradaSupermercadoCollection) {
        this.entradaSupermercadoCollection = entradaSupermercadoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mapaSupermercadoPK != null ? mapaSupermercadoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MapaSupermercado)) {
            return false;
        }
        MapaSupermercado other = (MapaSupermercado) object;
        if ((this.mapaSupermercadoPK == null && other.mapaSupermercadoPK != null) || (this.mapaSupermercadoPK != null && !this.mapaSupermercadoPK.equals(other.mapaSupermercadoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.back.hibernate.model.MapaSupermercado[ mapaSupermercadoPK=" + mapaSupermercadoPK + " ]";
    }
    
}
