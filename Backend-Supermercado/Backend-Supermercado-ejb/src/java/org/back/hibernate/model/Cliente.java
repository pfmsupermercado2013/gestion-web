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
@Table(name = "cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findByIdCliente", query = "SELECT c FROM Cliente c WHERE c.idCliente = :idCliente"),
    @NamedQuery(name = "Cliente.findByNombre", query = "SELECT c FROM Cliente c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Cliente.findByApellido1", query = "SELECT c FROM Cliente c WHERE c.apellido1 = :apellido1"),
    @NamedQuery(name = "Cliente.findByApellido2", query = "SELECT c FROM Cliente c WHERE c.apellido2 = :apellido2"),
    @NamedQuery(name = "Cliente.findByDireccion", query = "SELECT c FROM Cliente c WHERE c.direccion = :direccion"),
    @NamedQuery(name = "Cliente.findByProvincia", query = "SELECT c FROM Cliente c WHERE c.provincia = :provincia"),
    @NamedQuery(name = "Cliente.findByMunicipio", query = "SELECT c FROM Cliente c WHERE c.municipio = :municipio"),
    @NamedQuery(name = "Cliente.findByCodigoPostal", query = "SELECT c FROM Cliente c WHERE c.codigoPostal = :codigoPostal"),
    @NamedQuery(name = "Cliente.findByTelefonoMovil", query = "SELECT c FROM Cliente c WHERE c.telefonoMovil = :telefonoMovil"),
    @NamedQuery(name = "Cliente.findByTelefonoFijo", query = "SELECT c FROM Cliente c WHERE c.telefonoFijo = :telefonoFijo"),
    @NamedQuery(name = "Cliente.findByFechaAlta", query = "SELECT c FROM Cliente c WHERE c.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "Cliente.findByFechaBaja", query = "SELECT c FROM Cliente c WHERE c.fechaBaja = :fechaBaja")})
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idCliente")
    private Integer idCliente;
    @Basic(optional = false)
    @Column(name = "Nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "Apellido1")
    private String apellido1;
    @Column(name = "Apellido2")
    private String apellido2;
    @Column(name = "Direccion")
    private String direccion;
    @Column(name = "Provincia")
    private String provincia;
    @Column(name = "Municipio")
    private String municipio;
    @Column(name = "CodigoPostal")
    private Integer codigoPostal;
    @Basic(optional = false)
    @Column(name = " TelefonoMovil")
    private int telefonoMovil;
    @Basic(optional = false)
    @Column(name = "TelefonoFijo")
    private int telefonoFijo;
    @Basic(optional = false)
    @Column(name = "FechaAlta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;
    @Column(name = "FechaBaja")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaBaja;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCliente")
    private Collection<CarritoCompra> carritoCompraCollection;
    @JoinColumn(name = "idTicketAparcamiento", referencedColumnName = "idTicketAparcamiento")
    @ManyToOne(optional = false)
    private TicketAparcamiento idTicketAparcamiento;

    public Cliente() {
    }

    public Cliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Cliente(Integer idCliente, String nombre, String apellido1, int telefonoMovil, int telefonoFijo, Date fechaAlta) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.telefonoMovil = telefonoMovil;
        this.telefonoFijo = telefonoFijo;
        this.fechaAlta = fechaAlta;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public Integer getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(Integer codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public int getTelefonoMovil() {
        return telefonoMovil;
    }

    public void setTelefonoMovil(int telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

    public int getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(int telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    @XmlTransient
    public Collection<CarritoCompra> getCarritoCompraCollection() {
        return carritoCompraCollection;
    }

    public void setCarritoCompraCollection(Collection<CarritoCompra> carritoCompraCollection) {
        this.carritoCompraCollection = carritoCompraCollection;
    }

    public TicketAparcamiento getIdTicketAparcamiento() {
        return idTicketAparcamiento;
    }

    public void setIdTicketAparcamiento(TicketAparcamiento idTicketAparcamiento) {
        this.idTicketAparcamiento = idTicketAparcamiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCliente != null ? idCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.idCliente == null && other.idCliente != null) || (this.idCliente != null && !this.idCliente.equals(other.idCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.back.hibernate.model.Cliente[ idCliente=" + idCliente + " ]";
    }
    
}
