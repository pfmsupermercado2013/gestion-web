/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.back.hibernate.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ÓscarJavier
 */
@Entity
@Table(name = "carrito_compra_tiene_producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CarritoCompraTieneProducto.findAll", query = "SELECT c FROM CarritoCompraTieneProducto c"),
    @NamedQuery(name = "CarritoCompraTieneProducto.findByIdCarritoCompra", query = "SELECT c FROM CarritoCompraTieneProducto c WHERE c.carritoCompraTieneProductoPK.idCarritoCompra = :idCarritoCompra"),
    @NamedQuery(name = "CarritoCompraTieneProducto.findByIdproducto", query = "SELECT c FROM CarritoCompraTieneProducto c WHERE c.carritoCompraTieneProductoPK.idproducto = :idproducto"),
    @NamedQuery(name = "CarritoCompraTieneProducto.findByCantidad", query = "SELECT c FROM CarritoCompraTieneProducto c WHERE c.cantidad = :cantidad")})
public class CarritoCompraTieneProducto implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CarritoCompraTieneProductoPK carritoCompraTieneProductoPK;
    @Column(name = "Cantidad")
    private Integer cantidad;
    @JoinColumn(name = "idproducto", referencedColumnName = "idproducto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Producto producto;
    @JoinColumn(name = "idCarritoCompra", referencedColumnName = "idCarritoCompra", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CarritoCompra carritoCompra;

    public CarritoCompraTieneProducto() {
    }

    public CarritoCompraTieneProducto(CarritoCompraTieneProductoPK carritoCompraTieneProductoPK) {
        this.carritoCompraTieneProductoPK = carritoCompraTieneProductoPK;
    }

    public CarritoCompraTieneProducto(int idCarritoCompra, int idproducto) {
        this.carritoCompraTieneProductoPK = new CarritoCompraTieneProductoPK(idCarritoCompra, idproducto);
    }

    public CarritoCompraTieneProductoPK getCarritoCompraTieneProductoPK() {
        return carritoCompraTieneProductoPK;
    }

    public void setCarritoCompraTieneProductoPK(CarritoCompraTieneProductoPK carritoCompraTieneProductoPK) {
        this.carritoCompraTieneProductoPK = carritoCompraTieneProductoPK;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public CarritoCompra getCarritoCompra() {
        return carritoCompra;
    }

    public void setCarritoCompra(CarritoCompra carritoCompra) {
        this.carritoCompra = carritoCompra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (carritoCompraTieneProductoPK != null ? carritoCompraTieneProductoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CarritoCompraTieneProducto)) {
            return false;
        }
        CarritoCompraTieneProducto other = (CarritoCompraTieneProducto) object;
        if ((this.carritoCompraTieneProductoPK == null && other.carritoCompraTieneProductoPK != null) || (this.carritoCompraTieneProductoPK != null && !this.carritoCompraTieneProductoPK.equals(other.carritoCompraTieneProductoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.back.hibernate.model.CarritoCompraTieneProducto[ carritoCompraTieneProductoPK=" + carritoCompraTieneProductoPK + " ]";
    }
    
}
