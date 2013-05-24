/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.back.hibernate.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ÓscarJavier
 */
@Entity
@Table(name = "estante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estante.findAll", query = "SELECT e FROM Estante e"),
    @NamedQuery(name = "Estante.findByIdestante", query = "SELECT e FROM Estante e WHERE e.idestante = :idestante"),
    @NamedQuery(name = "Estante.findByNumeroSecciones", query = "SELECT e FROM Estante e WHERE e.numeroSecciones = :numeroSecciones")})
public class Estante implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idestante")
    private Integer idestante;
    @Basic(optional = false)
    @Column(name = "numero_secciones")
    private int numeroSecciones;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estante")
    private Collection<UbicacionProducto> ubicacionProductoCollection;

    public Estante() {
    }

    public Estante(Integer idestante) {
        this.idestante = idestante;
    }

    public Estante(Integer idestante, int numeroSecciones) {
        this.idestante = idestante;
        this.numeroSecciones = numeroSecciones;
    }

    public Integer getIdestante() {
        return idestante;
    }

    public void setIdestante(Integer idestante) {
        this.idestante = idestante;
    }

    public int getNumeroSecciones() {
        return numeroSecciones;
    }

    public void setNumeroSecciones(int numeroSecciones) {
        this.numeroSecciones = numeroSecciones;
    }

    @XmlTransient
    public Collection<UbicacionProducto> getUbicacionProductoCollection() {
        return ubicacionProductoCollection;
    }

    public void setUbicacionProductoCollection(Collection<UbicacionProducto> ubicacionProductoCollection) {
        this.ubicacionProductoCollection = ubicacionProductoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idestante != null ? idestante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estante)) {
            return false;
        }
        Estante other = (Estante) object;
        if ((this.idestante == null && other.idestante != null) || (this.idestante != null && !this.idestante.equals(other.idestante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.back.hibernate.model.Estante[ idestante=" + idestante + " ]";
    }
    
}
