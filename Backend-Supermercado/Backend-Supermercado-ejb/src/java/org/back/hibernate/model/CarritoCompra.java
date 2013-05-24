/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.back.hibernate.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ÓscarJavier
 */
@Entity
@Table(name = "carrito_compra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CarritoCompra.findAll", query = "SELECT c FROM CarritoCompra c"),
    @NamedQuery(name = "CarritoCompra.findByIdCarritoCompra", query = "SELECT c FROM CarritoCompra c WHERE c.idCarritoCompra = :idCarritoCompra"),
    @NamedQuery(name = "CarritoCompra.findByFechaCreacion", query = "SELECT c FROM CarritoCompra c WHERE c.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "CarritoCompra.findByDescripcion", query = "SELECT c FROM CarritoCompra c WHERE c.descripcion = :descripcion")})
public class CarritoCompra implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idCarritoCompra")
    private Integer idCarritoCompra;
    @Column(name = "FechaCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "Descripcion")
    private String descripcion;
    @JoinColumn(name = "idPago", referencedColumnName = "idPago")
    @ManyToOne(optional = false)
    private Pago idPago;
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
    @ManyToOne(optional = false)
    private Cliente idCliente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carritoCompra")
    private Collection<CarritoCompraTieneProducto> carritoCompraTieneProductoCollection;

    public CarritoCompra() {
    }

    public CarritoCompra(Integer idCarritoCompra) {
        this.idCarritoCompra = idCarritoCompra;
    }

    public Integer getIdCarritoCompra() {
        return idCarritoCompra;
    }

    public void setIdCarritoCompra(Integer idCarritoCompra) {
        this.idCarritoCompra = idCarritoCompra;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Pago getIdPago() {
        return idPago;
    }

    public void setIdPago(Pago idPago) {
        this.idPago = idPago;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    @XmlTransient
    public Collection<CarritoCompraTieneProducto> getCarritoCompraTieneProductoCollection() {
        return carritoCompraTieneProductoCollection;
    }

    public void setCarritoCompraTieneProductoCollection(Collection<CarritoCompraTieneProducto> carritoCompraTieneProductoCollection) {
        this.carritoCompraTieneProductoCollection = carritoCompraTieneProductoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCarritoCompra != null ? idCarritoCompra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CarritoCompra)) {
            return false;
        }
        CarritoCompra other = (CarritoCompra) object;
        if ((this.idCarritoCompra == null && other.idCarritoCompra != null) || (this.idCarritoCompra != null && !this.idCarritoCompra.equals(other.idCarritoCompra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.back.hibernate.model.CarritoCompra[ idCarritoCompra=" + idCarritoCompra + " ]";
    }
    
}
