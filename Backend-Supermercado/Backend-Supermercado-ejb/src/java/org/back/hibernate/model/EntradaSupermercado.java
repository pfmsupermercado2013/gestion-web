/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.back.hibernate.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
@Table(name = "entrada_supermercado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EntradaSupermercado.findAll", query = "SELECT e FROM EntradaSupermercado e"),
    @NamedQuery(name = "EntradaSupermercado.findByIdentradaSupermercado", query = "SELECT e FROM EntradaSupermercado e WHERE e.entradaSupermercadoPK.identradaSupermercado = :identradaSupermercado"),
    @NamedQuery(name = "EntradaSupermercado.findByMapaSupermercadoIdmapaSupermercado", query = "SELECT e FROM EntradaSupermercado e WHERE e.entradaSupermercadoPK.mapaSupermercadoIdmapaSupermercado = :mapaSupermercadoIdmapaSupermercado"),
    @NamedQuery(name = "EntradaSupermercado.findByMapaSupermercadoIdsupermercado", query = "SELECT e FROM EntradaSupermercado e WHERE e.entradaSupermercadoPK.mapaSupermercadoIdsupermercado = :mapaSupermercadoIdsupermercado"),
    @NamedQuery(name = "EntradaSupermercado.findByPosicionX", query = "SELECT e FROM EntradaSupermercado e WHERE e.posicionX = :posicionX"),
    @NamedQuery(name = "EntradaSupermercado.findByPosicionY", query = "SELECT e FROM EntradaSupermercado e WHERE e.posicionY = :posicionY"),
    @NamedQuery(name = "EntradaSupermercado.findByRotacion", query = "SELECT e FROM EntradaSupermercado e WHERE e.rotacion = :rotacion"),
    @NamedQuery(name = "EntradaSupermercado.findByLargo", query = "SELECT e FROM EntradaSupermercado e WHERE e.largo = :largo"),
    @NamedQuery(name = "EntradaSupermercado.findByAlto", query = "SELECT e FROM EntradaSupermercado e WHERE e.alto = :alto"),
    @NamedQuery(name = "EntradaSupermercado.findByTipoPuertaEntrada", query = "SELECT e FROM EntradaSupermercado e WHERE e.tipoPuertaEntrada = :tipoPuertaEntrada")})
public class EntradaSupermercado implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EntradaSupermercadoPK entradaSupermercadoPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "posicion_x")
    private Float posicionX;
    @Column(name = "posicion_y")
    private Float posicionY;
    @Column(name = "rotacion")
    private Float rotacion;
    @Column(name = "largo")
    private Float largo;
    @Column(name = "alto")
    private Float alto;
    @Column(name = "tipo_puerta_entrada")
    private Integer tipoPuertaEntrada;
    @JoinColumns({
        @JoinColumn(name = "mapa_supermercado_idmapa_supermercado", referencedColumnName = "idmapa_supermercado", insertable = false, updatable = false),
        @JoinColumn(name = "mapa_supermercado_idsupermercado", referencedColumnName = "supermercado_idsupermercado", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private MapaSupermercado mapaSupermercado;

    public EntradaSupermercado() {
    }

    public EntradaSupermercado(EntradaSupermercadoPK entradaSupermercadoPK) {
        this.entradaSupermercadoPK = entradaSupermercadoPK;
    }

    public EntradaSupermercado(int identradaSupermercado, int mapaSupermercadoIdmapaSupermercado, int mapaSupermercadoIdsupermercado) {
        this.entradaSupermercadoPK = new EntradaSupermercadoPK(identradaSupermercado, mapaSupermercadoIdmapaSupermercado, mapaSupermercadoIdsupermercado);
    }

    public EntradaSupermercadoPK getEntradaSupermercadoPK() {
        return entradaSupermercadoPK;
    }

    public void setEntradaSupermercadoPK(EntradaSupermercadoPK entradaSupermercadoPK) {
        this.entradaSupermercadoPK = entradaSupermercadoPK;
    }

    public Float getPosicionX() {
        return posicionX;
    }

    public void setPosicionX(Float posicionX) {
        this.posicionX = posicionX;
    }

    public Float getPosicionY() {
        return posicionY;
    }

    public void setPosicionY(Float posicionY) {
        this.posicionY = posicionY;
    }

    public Float getRotacion() {
        return rotacion;
    }

    public void setRotacion(Float rotacion) {
        this.rotacion = rotacion;
    }

    public Float getLargo() {
        return largo;
    }

    public void setLargo(Float largo) {
        this.largo = largo;
    }

    public Float getAlto() {
        return alto;
    }

    public void setAlto(Float alto) {
        this.alto = alto;
    }

    public Integer getTipoPuertaEntrada() {
        return tipoPuertaEntrada;
    }

    public void setTipoPuertaEntrada(Integer tipoPuertaEntrada) {
        this.tipoPuertaEntrada = tipoPuertaEntrada;
    }

    public MapaSupermercado getMapaSupermercado() {
        return mapaSupermercado;
    }

    public void setMapaSupermercado(MapaSupermercado mapaSupermercado) {
        this.mapaSupermercado = mapaSupermercado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (entradaSupermercadoPK != null ? entradaSupermercadoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EntradaSupermercado)) {
            return false;
        }
        EntradaSupermercado other = (EntradaSupermercado) object;
        if ((this.entradaSupermercadoPK == null && other.entradaSupermercadoPK != null) || (this.entradaSupermercadoPK != null && !this.entradaSupermercadoPK.equals(other.entradaSupermercadoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.back.hibernate.model.EntradaSupermercado[ entradaSupermercadoPK=" + entradaSupermercadoPK + " ]";
    }
    
}
