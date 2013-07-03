/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.back.hibernate.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Fer
 */
public class ProductoAndroid implements Serializable {
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
    @Column(name = "Marca")
    private String marca;
    @Column(name = "CodigoEAN")
    private String codigoEAN;
    @Column(name = "FechaEntrada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEntrada;
    @Column(name = "Descripcion")
    private String descripcion;
    @Column(name = "Cantidad")
    private int cantidad;
    @Column(name = "Categoria")
    private String categoria;
    
    @Column(name = "idseccion")
    private int idseccion;
    
    
    @Column(name = "idestante")
    private int idestante;
    @Column(name = "numero_secciones")
    private int numero_secciones_estante;
    
    @Column(name = "idestanteria")
    private int idestanteria;
    @Column(name = "longitud")
    private long longitud;
    @Column(name = "numero_estantes")
    private int numero_estantes;
    @Column(name = "posicion")
    private int posicion;
    @Column(name = "supermercado")
    private long supermercado;
    
    @Basic(optional = true)
    @Lob @Column(name = "imagen", length = 1048576)
    private byte[] imagen;

    public ProductoAndroid() {
    }

    public ProductoAndroid(Integer idproducto) {
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
    
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getIdseccion() {
        return idseccion;
    }

    public void setIdseccion(int idseccion) {
        this.idseccion = idseccion;
    }

    public int getIdestante() {
        return idestante;
    }

    public void setIdestante(int idestante) {
        this.idestante = idestante;
    }

    public int getIdestanteria() {
        return idestanteria;
    }

    public int getNumero_secciones_estante() {
        return numero_secciones_estante;
    }

    public void setNumero_secciones_estante(int numero_secciones_estante) {
        this.numero_secciones_estante = numero_secciones_estante;
    }
    

    public void setIdestanteria(int idestanteria) {
        this.idestanteria = idestanteria;
    }

    public long getLongitud() {
        return longitud;
    }

    public void setLongitud(long longitud) {
        this.longitud = longitud;
    }

    public int getNumero_estantes() {
        return numero_estantes;
    }

    public void setNumero_estantes(int numero_estantes) {
        this.numero_estantes = numero_estantes;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public long getSupermercado() {
        return supermercado;
    }

    public void setSupermercado(long supermercado) {
        this.supermercado = supermercado;
    }
    
    
   
}
