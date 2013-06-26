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
@Table(name = "ubicacion_producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UbicacionProducto.findAll", query = "SELECT u FROM UbicacionProducto u"),
    @NamedQuery(name = "UbicacionProducto.findByIdproducto", query = "SELECT u FROM UbicacionProducto u WHERE u.ubicacionProductoPK.idproducto = :idproducto"),
    @NamedQuery(name = "UbicacionProducto.findByIdseccion", query = "SELECT u FROM UbicacionProducto u WHERE u.ubicacionProductoPK.idseccion = :idseccion"),
    @NamedQuery(name = "UbicacionProducto.findByIdestante", query = "SELECT u FROM UbicacionProducto u WHERE u.ubicacionProductoPK.idestante = :idestante"),
    @NamedQuery(name = "UbicacionProducto.findByIdestanteria", query = "SELECT u FROM UbicacionProducto u WHERE u.ubicacionProductoPK.idestanteria = :idestanteria")})
public class UbicacionProducto implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UbicacionProductoPK ubicacionProductoPK;
    @JoinColumn(name = "idseccion", referencedColumnName = "idseccion", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Seccion seccion;
    @JoinColumn(name = "idproducto", referencedColumnName = "idproducto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Producto producto;
    @JoinColumn(name = "idestante", referencedColumnName = "idestante", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Estante estante;
    @JoinColumn(name = "idestanteria", referencedColumnName = "idestanteria", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Estanteria estanteria;
    @Column(name = "alto")
    private double alto;
    @Column(name = "ancho")
    private double ancho;
    @Column(name = "largo")
    private double largo;

    public UbicacionProducto() {
    }

    public UbicacionProducto(UbicacionProductoPK ubicacionProductoPK) {
        this.ubicacionProductoPK = ubicacionProductoPK;
    }

    public UbicacionProducto(int idproducto, int idseccion, int idestante, int idestanteria) {
        this.ubicacionProductoPK = new UbicacionProductoPK(idproducto, idseccion, idestante, idestanteria);
    }

    public UbicacionProductoPK getUbicacionProductoPK() {
        return ubicacionProductoPK;
    }

    public void setUbicacionProductoPK(UbicacionProductoPK ubicacionProductoPK) {
        this.ubicacionProductoPK = ubicacionProductoPK;
    }

    public Seccion getSeccion() {
        return seccion;
    }

    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Estante getEstante() {
        return estante;
    }

    public void setEstante(Estante estante) {
        this.estante = estante;
    }

    public Estanteria getEstanteria() {
        return estanteria;
    }

    public void setEstanteria(Estanteria estanteria) {
        this.estanteria = estanteria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ubicacionProductoPK != null ? ubicacionProductoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UbicacionProducto)) {
            return false;
        }
        UbicacionProducto other = (UbicacionProducto) object;
        if ((this.ubicacionProductoPK == null && other.ubicacionProductoPK != null) || (this.ubicacionProductoPK != null && !this.ubicacionProductoPK.equals(other.ubicacionProductoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.back.hibernate.model.UbicacionProducto[ ubicacionProductoPK=" + ubicacionProductoPK + " ]";
    }

    public double getAlto() {
        return alto;
    }

    public void setAlto(double alto) {
        this.alto = alto;
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    public double getLargo() {
        return largo;
    }

    public void setLargo(double largo) {
        this.largo = largo;
    }
    
}
