package org.back.hibernate.model;

import java.io.Serializable;
import javax.persistence.Basic;
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
 * @author Alejandro Garcia
 */
@Entity
@Table(name = "proveedor_subasta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProveedorSubasta.findAll", query = "SELECT p FROM ProveedorSubasta p"),
    @NamedQuery(name = "ProveedorSubasta.findByIdproveedor", query = "SELECT p FROM ProveedorSubasta p WHERE p.proveedorSubastaPK.idproveedor = :idproveedor"),
    @NamedQuery(name = "ProveedorSubasta.findByIdsubasta", query = "SELECT p FROM ProveedorSubasta p WHERE p.proveedorSubastaPK.idsubasta = :idsubasta"),
    @NamedQuery(name = "ProveedorSubasta.findByPuja", query = "SELECT p FROM ProveedorSubasta p WHERE p.puja = :puja")})
public class ProveedorSubasta implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProveedorSubastaPK proveedorSubastaPK;
    @Basic(optional = false)
    @Column(name = "puja")
    private float puja;
    @JoinColumn(name = "idsubasta", referencedColumnName = "idsubasta", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Subasta subasta;
    @JoinColumn(name = "idproveedor", referencedColumnName = "idProveedor", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Proveedor proveedor;

    public ProveedorSubasta() {
    }

    public ProveedorSubasta(ProveedorSubastaPK proveedorSubastaPK) {
        this.proveedorSubastaPK = proveedorSubastaPK;
    }

    public ProveedorSubasta(ProveedorSubastaPK proveedorSubastaPK, float puja) {
        this.proveedorSubastaPK = proveedorSubastaPK;
        this.puja = puja;
    }

    public ProveedorSubasta(int idproveedor, int idsubasta) {
        this.proveedorSubastaPK = new ProveedorSubastaPK(idproveedor, idsubasta);
    }

    public ProveedorSubastaPK getProveedorSubastaPK() {
        return proveedorSubastaPK;
    }

    public void setProveedorSubastaPK(ProveedorSubastaPK proveedorSubastaPK) {
        this.proveedorSubastaPK = proveedorSubastaPK;
    }

    public float getPuja() {
        return puja;
    }

    public void setPuja(float puja) {
        this.puja = puja;
    }

    public Subasta getSubasta() {
        return subasta;
    }

    public void setSubasta(Subasta subasta) {
        this.subasta = subasta;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proveedorSubastaPK != null ? proveedorSubastaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProveedorSubasta)) {
            return false;
        }
        ProveedorSubasta other = (ProveedorSubasta) object;
        if ((this.proveedorSubastaPK == null && other.proveedorSubastaPK != null) || (this.proveedorSubastaPK != null && !this.proveedorSubastaPK.equals(other.proveedorSubastaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.back.hibernate.model.ProveedorSubasta[ proveedorSubastaPK=" + proveedorSubastaPK + " ]";
    }
    
}