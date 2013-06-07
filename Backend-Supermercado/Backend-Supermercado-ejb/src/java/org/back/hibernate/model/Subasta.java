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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.back.serializer.ProductoSerializer;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author ÓscarJavier
 */
@Entity
@Table(name = "subasta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subasta.findAll", query = "SELECT s FROM Subasta s"),
    @NamedQuery(name = "Subasta.findByIdsubasta", query = "SELECT s FROM Subasta s WHERE s.idsubasta = :idsubasta"),
    @NamedQuery(name = "Subasta.findByFechaFin", query = "SELECT s FROM Subasta s WHERE s.fechaFin = :fechaFin"),
    @NamedQuery(name = "Subasta.findByFechaInicio", query = "SELECT s FROM Subasta s WHERE s.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Subasta.findByPujaInicial", query = "SELECT s FROM Subasta s WHERE s.pujaInicial = :pujaInicial"),
    @NamedQuery(name = "Subasta.findByEstado", query = "SELECT s FROM Subasta s WHERE s.estado = :estado")})
public class Subasta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsubasta")
    private Integer idsubasta;
    @Basic(optional = false)
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Basic(optional = false)
    @Column(name = "puja_inicial")
    private float pujaInicial;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subasta")
    @JsonIgnore
    private Collection<ProveedorSubasta> proveedorSubastaCollection;
    @JoinColumn(name = "producto", referencedColumnName = "idproducto")
    @OneToOne(optional = false)
    @JsonSerialize(using = ProductoSerializer.class)
    private Producto producto;

    public Subasta() {
    }

    public Subasta(Integer idsubasta) {
        this.idsubasta = idsubasta;
    }

    public Subasta(Date fechaFin, float pujaInicial, Producto producto, String descripcion) {
        this.fechaFin = fechaFin;
        this.pujaInicial = pujaInicial;
        this.producto = producto;
        this.estado = 1;
        this.descripcion = descripcion;
        this.fechaInicio = new Date();
    }

    public Integer getIdsubasta() {
        return idsubasta;
    }

    public void setIdsubasta(Integer idsubasta) {
        this.idsubasta = idsubasta;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public float getPujaInicial() {
        return pujaInicial;
    }

    public void setPujaInicial(float pujaInicial) {
        this.pujaInicial = pujaInicial;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @XmlTransient
    public Collection<ProveedorSubasta> getProveedorSubastaCollection() {
        return proveedorSubastaCollection;
    }

    public void setProveedorSubastaCollection(Collection<ProveedorSubasta> proveedorSubastaCollection) {
        this.proveedorSubastaCollection = proveedorSubastaCollection;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsubasta != null ? idsubasta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subasta)) {
            return false;
        }
        Subasta other = (Subasta) object;
        if ((this.idsubasta == null && other.idsubasta != null) || (this.idsubasta != null && !this.idsubasta.equals(other.idsubasta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.back.hibernate.model.Subasta[ idsubasta=" + idsubasta + " ]";
    }
    
}
