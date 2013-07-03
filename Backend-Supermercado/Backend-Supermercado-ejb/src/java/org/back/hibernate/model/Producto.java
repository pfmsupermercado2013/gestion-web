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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Fernando Pabón
 */
@Entity
@Table(name = "producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p"),
    @NamedQuery(name = "Producto.findByIdproducto", query = "SELECT p FROM Producto p WHERE p.idproducto = :idproducto"),
    @NamedQuery(name = "Producto.findByNombreProducto", query = "SELECT p FROM Producto p WHERE p.nombreProducto = :nombreProducto"),
    @NamedQuery(name = "Producto.findByPrecio", query = "SELECT p FROM Producto p WHERE p.precio = :precio"),
    @NamedQuery(name = "Producto.findByMarca", query = "SELECT p FROM Producto p WHERE p.marca = :marca"),
    @NamedQuery(name = "Producto.findByCodigoEAN", query = "SELECT p FROM Producto p WHERE p.codigoEAN = :codigoEAN"),
    @NamedQuery(name = "Producto.findByFechaEntrada", query = "SELECT p FROM Producto p WHERE p.fechaEntrada = :fechaEntrada"),
    @NamedQuery(name = "Producto.updateProducto", query = "UPDATE Producto SET Cantidad = :cantidad, FechaEntrada = :fechaEntrada WHERE idproducto = :idproducto"),
    @NamedQuery(name = "Producto.findByDescripcion", query = "SELECT p FROM Producto p WHERE p.descripcion = :descripcion")})
public class Producto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idproducto")
    private Integer idproducto;
    @Column(name = "NombreProducto")
    private String nombreProducto;
    @Column(name = "Precio")
    private Short precio;
    @Basic(optional = false)
    @Column(name = "Cantidad")
    private Integer cantidad;
    @Column(name = "Marca")
    private String marca;
    @Column(name = "CodigoEAN")
    private String codigoEAN;
    @Column(name = "FechaEntrada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEntrada;
    @Column(name = "Descripcion")
    private String descripcion;
    @Basic(optional = true)
    @Lob @Column(name = "imagen", length = 1048576)
    private byte[] imagen;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "producto") 
    private Subasta subasta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto")
    private Collection<UbicacionProducto> ubicacionProductoCollection;
    @JoinColumn(name = "Categoria_Id_Categoria", referencedColumnName = "idcategoria")
    @ManyToOne(optional = false)
    private Categoria categoriaIdCategoria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto")
    private Collection<CarritoCompraTieneProducto> carritoCompraTieneProductoCollection;

    public Producto() {
    }

    public Producto(Integer idproducto) {
        this.idproducto = idproducto;
    }

    public Integer getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Integer idproducto) {
        this.idproducto = idproducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Short getPrecio() {
        return precio;
    }

    public void setPrecio(Short precio) {
        this.precio = precio;
    }
    
    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCodigoEAN() {
        return codigoEAN;
    }

    public void setCodigoEAN(String codigoEAN) {
        this.codigoEAN = codigoEAN;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
    
    public Subasta getSubasta() {
        return subasta;
    }

    public void setSubasta(Subasta subasta) {
        this.subasta = subasta;
    }

    @XmlTransient
    public Collection<UbicacionProducto> getUbicacionProductoCollection() {
        return ubicacionProductoCollection;
    }

    public void setUbicacionProductoCollection(Collection<UbicacionProducto> ubicacionProductoCollection) {
        this.ubicacionProductoCollection = ubicacionProductoCollection;
    }

    public Categoria getCategoriaIdCategoria() {
        return categoriaIdCategoria;
    }

    public void setCategoriaIdCategoria(Categoria categoriaIdCategoria) {
        this.categoriaIdCategoria = categoriaIdCategoria;
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
        hash += (idproducto != null ? idproducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.idproducto == null && other.idproducto != null) || (this.idproducto != null && !this.idproducto.equals(other.idproducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.back.hibernate.model.Producto[ idproducto=" + idproducto + " ]";
    }
    
}
