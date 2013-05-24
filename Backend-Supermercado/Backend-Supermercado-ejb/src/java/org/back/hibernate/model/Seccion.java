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
@Table(name = "seccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Seccion.findAll", query = "SELECT s FROM Seccion s"),
    @NamedQuery(name = "Seccion.findByIdseccion", query = "SELECT s FROM Seccion s WHERE s.idseccion = :idseccion")})
public class Seccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idseccion")
    private Integer idseccion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seccion")
    private Collection<UbicacionProducto> ubicacionProductoCollection;

    public Seccion() {
    }

    public Seccion(Integer idseccion) {
        this.idseccion = idseccion;
    }

    public Integer getIdseccion() {
        return idseccion;
    }

    public void setIdseccion(Integer idseccion) {
        this.idseccion = idseccion;
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
        hash += (idseccion != null ? idseccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seccion)) {
            return false;
        }
        Seccion other = (Seccion) object;
        if ((this.idseccion == null && other.idseccion != null) || (this.idseccion != null && !this.idseccion.equals(other.idseccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.back.hibernate.model.Seccion[ idseccion=" + idseccion + " ]";
    }
    
}
