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
@Table(name = "ticket_aparcamiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TicketAparcamiento.findAll", query = "SELECT t FROM TicketAparcamiento t"),
    @NamedQuery(name = "TicketAparcamiento.findByIdTicketAparcamiento", query = "SELECT t FROM TicketAparcamiento t WHERE t.idTicketAparcamiento = :idTicketAparcamiento"),
    @NamedQuery(name = "TicketAparcamiento.findByImportePagar", query = "SELECT t FROM TicketAparcamiento t WHERE t.importePagar = :importePagar"),
    @NamedQuery(name = "TicketAparcamiento.findByFechaEntrada", query = "SELECT t FROM TicketAparcamiento t WHERE t.fechaEntrada = :fechaEntrada"),
    @NamedQuery(name = "TicketAparcamiento.findByFechaSalida", query = "SELECT t FROM TicketAparcamiento t WHERE t.fechaSalida = :fechaSalida")})
public class TicketAparcamiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idTicketAparcamiento")
    private Integer idTicketAparcamiento;
    @Column(name = "ImportePagar")
    private Integer importePagar;
    @Basic(optional = false)
    @Column(name = "FechaEntrada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEntrada;
    @Basic(optional = false)
    @Column(name = "FechaSalida")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSalida;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTicketAparcamiento")
    private Collection<LocalizacionAparcamiento> localizacionAparcamientoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTicketAparcamiento")
    private Collection<Cliente> clienteCollection;
    @JoinColumn(name = "idPago", referencedColumnName = "idPago")
    @ManyToOne(optional = false)
    private Pago idPago;

    public TicketAparcamiento() {
    }

    public TicketAparcamiento(Integer idTicketAparcamiento) {
        this.idTicketAparcamiento = idTicketAparcamiento;
    }

    public TicketAparcamiento(Integer idTicketAparcamiento, Date fechaEntrada, Date fechaSalida) {
        this.idTicketAparcamiento = idTicketAparcamiento;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
    }

    public Integer getIdTicketAparcamiento() {
        return idTicketAparcamiento;
    }

    public void setIdTicketAparcamiento(Integer idTicketAparcamiento) {
        this.idTicketAparcamiento = idTicketAparcamiento;
    }

    public Integer getImportePagar() {
        return importePagar;
    }

    public void setImportePagar(Integer importePagar) {
        this.importePagar = importePagar;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    @XmlTransient
    public Collection<LocalizacionAparcamiento> getLocalizacionAparcamientoCollection() {
        return localizacionAparcamientoCollection;
    }

    public void setLocalizacionAparcamientoCollection(Collection<LocalizacionAparcamiento> localizacionAparcamientoCollection) {
        this.localizacionAparcamientoCollection = localizacionAparcamientoCollection;
    }

    @XmlTransient
    public Collection<Cliente> getClienteCollection() {
        return clienteCollection;
    }

    public void setClienteCollection(Collection<Cliente> clienteCollection) {
        this.clienteCollection = clienteCollection;
    }

    public Pago getIdPago() {
        return idPago;
    }

    public void setIdPago(Pago idPago) {
        this.idPago = idPago;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTicketAparcamiento != null ? idTicketAparcamiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TicketAparcamiento)) {
            return false;
        }
        TicketAparcamiento other = (TicketAparcamiento) object;
        if ((this.idTicketAparcamiento == null && other.idTicketAparcamiento != null) || (this.idTicketAparcamiento != null && !this.idTicketAparcamiento.equals(other.idTicketAparcamiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.back.hibernate.model.TicketAparcamiento[ idTicketAparcamiento=" + idTicketAparcamiento + " ]";
    }
    
}
