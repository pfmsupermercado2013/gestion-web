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
@Table(name = "pago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pago.findAll", query = "SELECT p FROM Pago p"),
    @NamedQuery(name = "Pago.findByIdPago", query = "SELECT p FROM Pago p WHERE p.idPago = :idPago"),
    @NamedQuery(name = "Pago.findByImporteTotal", query = "SELECT p FROM Pago p WHERE p.importeTotal = :importeTotal"),
    @NamedQuery(name = "Pago.findByDescuento", query = "SELECT p FROM Pago p WHERE p.descuento = :descuento"),
    @NamedQuery(name = "Pago.findByFechaPago", query = "SELECT p FROM Pago p WHERE p.fechaPago = :fechaPago"),
    @NamedQuery(name = "Pago.findByModoPago", query = "SELECT p FROM Pago p WHERE p.modoPago = :modoPago"),
    @NamedQuery(name = "Pago.findByDescripcion", query = "SELECT p FROM Pago p WHERE p.descripcion = :descripcion")})
public class Pago implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idPago")
    private Integer idPago;
    @Column(name = "ImporteTotal")
    private Short importeTotal;
    @Column(name = "Descuento")
    private Short descuento;
    @Basic(optional = false)
    @Column(name = "FechaPago")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPago;
    @Column(name = "ModoPago")
    private String modoPago;
    @Column(name = "Descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPago")
    private Collection<CarritoCompra> carritoCompraCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPago")
    private Collection<TicketAparcamiento> ticketAparcamientoCollection;

    public Pago() {
    }

    public Pago(Integer idPago) {
        this.idPago = idPago;
    }

    public Pago(Integer idPago, Date fechaPago) {
        this.idPago = idPago;
        this.fechaPago = fechaPago;
    }

    public Integer getIdPago() {
        return idPago;
    }

    public void setIdPago(Integer idPago) {
        this.idPago = idPago;
    }

    public Short getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(Short importeTotal) {
        this.importeTotal = importeTotal;
    }

    public Short getDescuento() {
        return descuento;
    }

    public void setDescuento(Short descuento) {
        this.descuento = descuento;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getModoPago() {
        return modoPago;
    }

    public void setModoPago(String modoPago) {
        this.modoPago = modoPago;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<CarritoCompra> getCarritoCompraCollection() {
        return carritoCompraCollection;
    }

    public void setCarritoCompraCollection(Collection<CarritoCompra> carritoCompraCollection) {
        this.carritoCompraCollection = carritoCompraCollection;
    }

    @XmlTransient
    public Collection<TicketAparcamiento> getTicketAparcamientoCollection() {
        return ticketAparcamientoCollection;
    }

    public void setTicketAparcamientoCollection(Collection<TicketAparcamiento> ticketAparcamientoCollection) {
        this.ticketAparcamientoCollection = ticketAparcamientoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPago != null ? idPago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pago)) {
            return false;
        }
        Pago other = (Pago) object;
        if ((this.idPago == null && other.idPago != null) || (this.idPago != null && !this.idPago.equals(other.idPago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.back.hibernate.model.Pago[ idPago=" + idPago + " ]";
    }
    
}
