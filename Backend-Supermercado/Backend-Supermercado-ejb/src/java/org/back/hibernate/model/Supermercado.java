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
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author ÓscarJavier
 */
@Entity
@Table(name = "supermercado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Supermercado.findAll", query = "SELECT s FROM Supermercado s"),
    @NamedQuery(name = "Supermercado.findByIdsupermercado", query = "SELECT s FROM Supermercado s WHERE s.idsupermercado = :idsupermercado"),
    @NamedQuery(name = "Supermercado.findByNombreSupermercado", query = "SELECT s FROM Supermercado s WHERE s.nombreSupermercado = :nombreSupermercado"),
    @NamedQuery(name = "Supermercado.findByDireccionSupermercado", query = "SELECT s FROM Supermercado s WHERE s.direccionSupermercado = :direccionSupermercado"),
    @NamedQuery(name = "Supermercado.findByLocalidadSupermercado", query = "SELECT s FROM Supermercado s WHERE s.localidadSupermercado = :localidadSupermercado"),
    @NamedQuery(name = "Supermercado.findByProvinciaSupermercado", query = "SELECT s FROM Supermercado s WHERE s.provinciaSupermercado = :provinciaSupermercado")})
public class Supermercado implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "supermercado")
    private Collection<MapaSupermercado> mapaSupermercadoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "supermercado")
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsupermercado")
    private Integer idsupermercado;
    @Basic(optional = false)
    @Column(name = "nombre_supermercado")
    private String nombreSupermercado;
    @Basic(optional = false)
    @Column(name = "direccion_supermercado")
    private String direccionSupermercado;
    @Basic(optional = false)
    @Column(name = "localidad_supermercado")
    private String localidadSupermercado;
    @Basic(optional = false)
    @Column(name = "provincia_supermercado")
    private String provinciaSupermercado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "supermercado")
    private Collection<Estanteria> estanteriaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "supermercado")
    private Collection<Empleado> empleadoCollection;

    public Supermercado() {
    }

    public Supermercado(Integer idsupermercado) {
        this.idsupermercado = idsupermercado;
    }

    public Supermercado(Integer idsupermercado, String nombreSupermercado, String direccionSupermercado, String localidadSupermercado, String provinciaSupermercado) {
        this.idsupermercado = idsupermercado;
        this.nombreSupermercado = nombreSupermercado;
        this.direccionSupermercado = direccionSupermercado;
        this.localidadSupermercado = localidadSupermercado;
        this.provinciaSupermercado = provinciaSupermercado;
    }
    
    public Supermercado(String nombreSupermercado, String direccionSupermercado, String localidadSupermercado, String provinciaSupermercado) {
        this.nombreSupermercado = nombreSupermercado;
        this.direccionSupermercado = direccionSupermercado;
        this.localidadSupermercado = localidadSupermercado;
        this.provinciaSupermercado = provinciaSupermercado;
    }

    public Integer getIdsupermercado() {
        return idsupermercado;
    }

    public void setIdsupermercado(Integer idsupermercado) {
        this.idsupermercado = idsupermercado;
    }

    public String getNombreSupermercado() {
        return nombreSupermercado;
    }

    public void setNombreSupermercado(String nombreSupermercado) {
        this.nombreSupermercado = nombreSupermercado;
    }

    public String getDireccionSupermercado() {
        return direccionSupermercado;
    }

    public void setDireccionSupermercado(String direccionSupermercado) {
        this.direccionSupermercado = direccionSupermercado;
    }

    public String getLocalidadSupermercado() {
        return localidadSupermercado;
    }

    public void setLocalidadSupermercado(String localidadSupermercado) {
        this.localidadSupermercado = localidadSupermercado;
    }

    public String getProvinciaSupermercado() {
        return provinciaSupermercado;
    }

    public void setProvinciaSupermercado(String provinciaSupermercado) {
        this.provinciaSupermercado = provinciaSupermercado;
    }

    @XmlTransient
    public Collection<Estanteria> getEstanteriaCollection() {
        return estanteriaCollection;
    }

    public void setEstanteriaCollection(Collection<Estanteria> estanteriaCollection) {
        this.estanteriaCollection = estanteriaCollection;
    }

    @XmlTransient
    public Collection<Empleado> getEmpleadoCollection() {
        return empleadoCollection;
    }

    public void setEmpleadoCollection(Collection<Empleado> empleadoCollection) {
        this.empleadoCollection = empleadoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsupermercado != null ? idsupermercado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Supermercado)) {
            return false;
        }
        Supermercado other = (Supermercado) object;
        if ((this.idsupermercado == null && other.idsupermercado != null) || (this.idsupermercado != null && !this.idsupermercado.equals(other.idsupermercado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.back.hibernate.model.Supermercado[ idsupermercado=" + idsupermercado + " ]";
    }

    @XmlTransient
    @JsonIgnore
    public Collection<MapaSupermercado> getMapaSupermercadoCollection() {
        return mapaSupermercadoCollection;
    }

    public void setMapaSupermercadoCollection(Collection<MapaSupermercado> mapaSupermercadoCollection) {
        this.mapaSupermercadoCollection = mapaSupermercadoCollection;
    }
    
}
