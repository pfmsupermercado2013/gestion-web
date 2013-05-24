/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.back.hibernate.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "localizacion_aparcamiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LocalizacionAparcamiento.findAll", query = "SELECT l FROM LocalizacionAparcamiento l"),
    @NamedQuery(name = "LocalizacionAparcamiento.findByIdLocalizacionAparcamiento", query = "SELECT l FROM LocalizacionAparcamiento l WHERE l.idLocalizacionAparcamiento = :idLocalizacionAparcamiento"),
    @NamedQuery(name = "LocalizacionAparcamiento.findByGPSLongitud", query = "SELECT l FROM LocalizacionAparcamiento l WHERE l.gPSLongitud = :gPSLongitud"),
    @NamedQuery(name = "LocalizacionAparcamiento.findByGPSLatitud", query = "SELECT l FROM LocalizacionAparcamiento l WHERE l.gPSLatitud = :gPSLatitud"),
    @NamedQuery(name = "LocalizacionAparcamiento.findByGPSAltura", query = "SELECT l FROM LocalizacionAparcamiento l WHERE l.gPSAltura = :gPSAltura")})
public class LocalizacionAparcamiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idLocalizacionAparcamiento")
    private Integer idLocalizacionAparcamiento;
    @Column(name = "GPS_Longitud")
    private Short gPSLongitud;
    @Column(name = "GPS_Latitud")
    private Short gPSLatitud;
    @Column(name = "GPS_Altura")
    private Short gPSAltura;
    @JoinColumn(name = "idTicketAparcamiento", referencedColumnName = "idTicketAparcamiento")
    @ManyToOne(optional = false)
    private TicketAparcamiento idTicketAparcamiento;

    public LocalizacionAparcamiento() {
    }

    public LocalizacionAparcamiento(Integer idLocalizacionAparcamiento) {
        this.idLocalizacionAparcamiento = idLocalizacionAparcamiento;
    }

    public Integer getIdLocalizacionAparcamiento() {
        return idLocalizacionAparcamiento;
    }

    public void setIdLocalizacionAparcamiento(Integer idLocalizacionAparcamiento) {
        this.idLocalizacionAparcamiento = idLocalizacionAparcamiento;
    }

    public Short getGPSLongitud() {
        return gPSLongitud;
    }

    public void setGPSLongitud(Short gPSLongitud) {
        this.gPSLongitud = gPSLongitud;
    }

    public Short getGPSLatitud() {
        return gPSLatitud;
    }

    public void setGPSLatitud(Short gPSLatitud) {
        this.gPSLatitud = gPSLatitud;
    }

    public Short getGPSAltura() {
        return gPSAltura;
    }

    public void setGPSAltura(Short gPSAltura) {
        this.gPSAltura = gPSAltura;
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
        hash += (idLocalizacionAparcamiento != null ? idLocalizacionAparcamiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LocalizacionAparcamiento)) {
            return false;
        }
        LocalizacionAparcamiento other = (LocalizacionAparcamiento) object;
        if ((this.idLocalizacionAparcamiento == null && other.idLocalizacionAparcamiento != null) || (this.idLocalizacionAparcamiento != null && !this.idLocalizacionAparcamiento.equals(other.idLocalizacionAparcamiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.back.hibernate.model.LocalizacionAparcamiento[ idLocalizacionAparcamiento=" + idLocalizacionAparcamiento + " ]";
    }
    
}
