/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.back.hibernate.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "estanteria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estanteria.findAll", query = "SELECT e FROM Estanteria e"),
    @NamedQuery(name = "Estanteria.findByIdestanteria", query = "SELECT e FROM Estanteria e WHERE e.idestanteria = :idestanteria"),
    @NamedQuery(name = "Estanteria.findByNumeroEstantes", query = "SELECT e FROM Estanteria e WHERE e.numeroEstantes = :numeroEstantes"),
    @NamedQuery(name = "Estanteria.findByLongitud", query = "SELECT e FROM Estanteria e WHERE e.longitud = :longitud")})
public class Estanteria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idestanteria")
    private Integer idestanteria;
    @Basic(optional = false)
    @Column(name = "numero_estantes")
    private int numeroEstantes;
    @Basic(optional = false)
    @Column(name = "longitud")
    private BigDecimal longitud;
    @Column(name = "posicion_x")
    private double posicion_x;
    @Column(name = "posicion_y")
    private double posicion_y;
    @Column(name = "rotacion_xy")
    private double rotacion_xy;
    @Column(name = "tipoEstanteria")
    private Integer tipoEstanteria;
    @JoinColumn(name = "supermercado", referencedColumnName = "idsupermercado")
    @ManyToOne(optional = false)
    private Supermercado supermercado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estanteria")
    private Collection<UbicacionProducto> ubicacionProductoCollection;

    public Estanteria() {
    }

    public Estanteria(Integer idestanteria) {
        this.idestanteria = idestanteria;
    }

    public Estanteria(Integer idestanteria, int numeroEstantes, BigDecimal longitud, double posicion_x, double posicion_y, double rotacion_xy) {
        this.idestanteria = idestanteria;
        this.numeroEstantes = numeroEstantes;
        this.longitud = longitud;
        this.posicion_x = posicion_x;
        this.posicion_y = posicion_y;
        this.rotacion_xy = rotacion_xy;
    }

    public Integer getIdestanteria() {
        return idestanteria;
    }

    public void setIdestanteria(Integer idestanteria) {
        this.idestanteria = idestanteria;
    }

    public int getNumeroEstantes() {
        return numeroEstantes;
    }

    public void setNumeroEstantes(int numeroEstantes) {
        this.numeroEstantes = numeroEstantes;
    }

    public BigDecimal getLongitud() {
        return longitud;
    }

    public void setLongitud(BigDecimal longitud) {
        this.longitud = longitud;
    }

    
    public void setPosicion_x(double posicion_x) {
        this.posicion_x = posicion_x;
    }

    public void setPosicion_y(double posicion_y) {
        this.posicion_y = posicion_y;
    }

    public void setRotacion_xy(double rotacion_xy) {
        this.rotacion_xy = rotacion_xy;
    }

    public void setTipoEstanteria(Integer tipoEstanteria) {
        this.tipoEstanteria = tipoEstanteria;
    }
    
    public double getPosicion_x() {
        return posicion_x;
    }

    public double getPosicion_y() {
        return posicion_y;
    }

    public double getRotacion_xy() {
        return rotacion_xy;
    }

    public Integer getTipoEstanteria() {
        return tipoEstanteria;
    }

    public Supermercado getSupermercado() {
        return supermercado;
    }

    public void setSupermercado(Supermercado supermercado) {
        this.supermercado = supermercado;
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
        hash += (idestanteria != null ? idestanteria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estanteria)) {
            return false;
        }
        Estanteria other = (Estanteria) object;
        if ((this.idestanteria == null && other.idestanteria != null) || (this.idestanteria != null && !this.idestanteria.equals(other.idestanteria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.back.hibernate.model.Estanteria[ idestanteria=" + idestanteria + " ]";
    }
    
}
