/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sys.core.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
 * @author admin
 */
@Entity
@Table(name = "SYS_CORE_BITACORA_ENTIDAD")
public class BitacoraEntidadDto implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "ENTIDAD")
    private BigInteger entidad;
    @Column(name = "REGISTRO")
    private BigInteger registro;
    @Column(name = "TIPO_MOVIMIENTO")
    private BigInteger tipoMovimiento;
    @Size(max = 1000)
    @Column(name = "COMENTARIO")
    private String comentario;
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "USUARIO")
    private BigInteger usuario;
    @Column(name = "ESTADO")
    private BigInteger estado;
    @Column(name = "ETAPA")
    private BigInteger etapa;
    @Column(name = "DIFERENCIA_TIEMPO")
    private Short diferenciaTiempo;
    @Size(max = 100)
    @Column(name = "TERMINAL")
    private String terminal;

    public BitacoraEntidadDto() {
    }

    public BitacoraEntidadDto(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigInteger getEntidad() {
        return entidad;
    }

    public void setEntidad(BigInteger entidad) {
        this.entidad = entidad;
    }

    public BigInteger getRegistro() {
        return registro;
    }

    public void setRegistro(BigInteger registro) {
        this.registro = registro;
    }

    public BigInteger getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(BigInteger tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigInteger getUsuario() {
        return usuario;
    }

    public void setUsuario(BigInteger usuario) {
        this.usuario = usuario;
    }

    public BigInteger getEstado() {
        return estado;
    }

    public void setEstado(BigInteger estado) {
        this.estado = estado;
    }

    public BigInteger getEtapa() {
        return etapa;
    }

    public void setEtapa(BigInteger etapa) {
        this.etapa = etapa;
    }

    public Short getDiferenciaTiempo() {
        return diferenciaTiempo;
    }

    public void setDiferenciaTiempo(Short diferenciaTiempo) {
        this.diferenciaTiempo = diferenciaTiempo;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BitacoraEntidadDto)) {
            return false;
        }
        BitacoraEntidadDto other = (BitacoraEntidadDto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sys.core.dto.BitacoraEntidadDto[ id=" + id + " ]";
    }
    
}
