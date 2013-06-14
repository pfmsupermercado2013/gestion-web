/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.back.hibernate.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ÓscarJavier
 */
@Entity
@Table(name = "notificaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notificaciones.findAll", query = "SELECT n FROM Notificaciones n"),
    @NamedQuery(name = "Notificaciones.findByIdnotificaciones", query = "SELECT n FROM Notificaciones n WHERE n.idnotificaciones = :idnotificaciones"),
    @NamedQuery(name = "Notificaciones.findByFechaCreacion", query = "SELECT n FROM Notificaciones n WHERE n.fechaCreacion = :fechaCreacion")})
public class Notificaciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idnotificaciones")
    private Integer idnotificaciones;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @JoinColumn(name = "idempleado", referencedColumnName = "idempleado")
    @ManyToOne(optional = false)
    private Empleado idempleado;

    public Notificaciones() {
    }

    public Notificaciones(Integer idnotificaciones) {
        this.idnotificaciones = idnotificaciones;
    }

    public Notificaciones(Integer idnotificaciones, String descripcion, Date fechaCreacion) {
        this.idnotificaciones = idnotificaciones;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getIdnotificaciones() {
        return idnotificaciones;
    }

    public void setIdnotificaciones(Integer idnotificaciones) {
        this.idnotificaciones = idnotificaciones;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Empleado getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(Empleado idempleado) {
        this.idempleado = idempleado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnotificaciones != null ? idnotificaciones.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notificaciones)) {
            return false;
        }
        Notificaciones other = (Notificaciones) object;
        if ((this.idnotificaciones == null && other.idnotificaciones != null) || (this.idnotificaciones != null && !this.idnotificaciones.equals(other.idnotificaciones))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.back.hibernate.model.Notificaciones[ idnotificaciones=" + idnotificaciones + " ]";
    }
    
}
