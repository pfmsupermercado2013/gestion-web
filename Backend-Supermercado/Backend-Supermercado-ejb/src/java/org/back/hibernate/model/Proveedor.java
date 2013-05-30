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

/**
 *
 * @author ÓscarJavier
 */
@Entity
@Table(name = "proveedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proveedor.findAll", query = "SELECT p FROM Proveedor p"),
    @NamedQuery(name = "Proveedor.findByIdProveedor", query = "SELECT p FROM Proveedor p WHERE p.idProveedor = :idProveedor"),
    @NamedQuery(name = "Proveedor.findByCif", query = "SELECT p FROM Proveedor p WHERE p.cif = :cif"),
    @NamedQuery(name = "Proveedor.findByNombreProveedor", query = "SELECT p FROM Proveedor p WHERE p.nombreProveedor = :nombreProveedor"),
    @NamedQuery(name = "Proveedor.findByLocalidadProveedor", query = "SELECT p FROM Proveedor p WHERE p.localidadProveedor = :localidadProveedor"),
    @NamedQuery(name = "Proveedor.findByProvinciaProveedor", query = "SELECT p FROM Proveedor p WHERE p.provinciaProveedor = :provinciaProveedor"),
    @NamedQuery(name = "Proveedor.findByCpProveedor", query = "SELECT p FROM Proveedor p WHERE p.cpProveedor = :cpProveedor"),
    @NamedQuery(name = "Proveedor.findByEmailProveedor", query = "SELECT p FROM Proveedor p WHERE p.emailProveedor = :emailProveedor"),
    @NamedQuery(name = "Proveedor.findByContrase\u00f1a", query = "SELECT p FROM Proveedor p WHERE p.contrase\u00f1a = :contrase\u00f1a"),
    @NamedQuery(name = "Proveedor.findByTelefono", query = "SELECT p FROM Proveedor p WHERE p.telefono = :telefono")})
public class Proveedor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idProveedor")
    private Integer idProveedor;
    @Basic(optional = false)
    @Column(name = "CIF")
    private String cif;
    @Basic(optional = false)
    @Column(name = "nombre_proveedor")
    private String nombreProveedor;
    @Basic(optional = false)
    @Column(name = "localidad_proveedor")
    private String localidadProveedor;
    @Basic(optional = false)
    @Column(name = "provincia_proveedor")
    private String provinciaProveedor;
    @Basic(optional = false)
    @Column(name = "cp_proveedor")
    private String cpProveedor;
    @Basic(optional = false)
    @Column(name = "email_proveedor")
    private String emailProveedor;
    @Basic(optional = false)
    @Column(name = "contrase\u00f1a")
    private String contraseña;
    @Basic(optional = false)
    @Column(name = "telefono")
    private String telefono;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proveedor")
    private Collection<ProveedorSubasta> proveedorSubastaCollection;

    public Proveedor() {
    }

    public Proveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Proveedor(String cif, String nombreProveedor, String localidadProveedor, String provinciaProveedor, String cpProveedor, String emailProveedor, String contraseña, String telefono) {
        this.cif = cif;
        this.nombreProveedor = nombreProveedor;
        this.localidadProveedor = localidadProveedor;
        this.provinciaProveedor = provinciaProveedor;
        this.cpProveedor = cpProveedor;
        this.emailProveedor = emailProveedor;
        this.contraseña = contraseña;
        this.telefono = telefono;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getLocalidadProveedor() {
        return localidadProveedor;
    }

    public void setLocalidadProveedor(String localidadProveedor) {
        this.localidadProveedor = localidadProveedor;
    }

    public String getProvinciaProveedor() {
        return provinciaProveedor;
    }

    public void setProvinciaProveedor(String provinciaProveedor) {
        this.provinciaProveedor = provinciaProveedor;
    }

    public String getCpProveedor() {
        return cpProveedor;
    }

    public void setCpProveedor(String cpProveedor) {
        this.cpProveedor = cpProveedor;
    }

    public String getEmailProveedor() {
        return emailProveedor;
    }

    public void setEmailProveedor(String emailProveedor) {
        this.emailProveedor = emailProveedor;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @XmlTransient
    public Collection<ProveedorSubasta> getProveedorSubastaCollection() {
        return proveedorSubastaCollection;
    }

    public void setProveedorSubastaCollection(Collection<ProveedorSubasta> proveedorSubastaCollection) {
        this.proveedorSubastaCollection = proveedorSubastaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProveedor != null ? idProveedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedor)) {
            return false;
        }
        Proveedor other = (Proveedor) object;
        if ((this.idProveedor == null && other.idProveedor != null) || (this.idProveedor != null && !this.idProveedor.equals(other.idProveedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.back.hibernate.model.Proveedor[ idProveedor=" + idProveedor + " ]";
    }
    
}
