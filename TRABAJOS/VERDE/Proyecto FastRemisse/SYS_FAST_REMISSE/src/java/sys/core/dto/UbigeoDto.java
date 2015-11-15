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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "SYS_CORE_UBIGEO")
public class UbigeoDto implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "SEQ_UBIGEO")
    @TableGenerator(name = "SEQ_UBIGEO", table = "SYS_CORE_ENTIDAD", pkColumnName = "NOMBRE", valueColumnName = "CORRELATIVO", pkColumnValue = "UBIGEO", allocationSize = 1)
    private Long id;
    @Column(name = "ESTADO")
    private Boolean estado;
    @JoinColumn(name = "USUARIO", referencedColumnName = "ID")
    @ManyToOne
    private UsuarioDto usuarioDto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Size(max = 1000)
    @Column(name = "COMENTARIO")
    private String comentario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Size(max = 50)
    @Column(name = "DESCRIPCION_CORTA")
    private String descripcionCorta;
    @Size(max = 300)
    @Column(name = "DESCRIPCION_COMPLETA")
    private String descripcionCompleta;
    @Size(max = 50)
    @JoinColumn(name = "TIPO", referencedColumnName = "ID")
    @ManyToOne
    private ParametroDto tipoDto;
    @Column(name = "UBIGEO_PADRE")
    private BigInteger ubigeoPadre;
    @Size(max = 50)
    @Column(name = "CODIGO_POSTAL")
    private String codigoPostal;
    @Size(max = 50)
    @Column(name = "CODIGO_INEI")
    private String codigoInei;
    @Column(name = "PRIORIDAD")
    private Long prioridad;
    @JoinColumn(name = "USUARIO_CREACION", referencedColumnName = "ID")
    @ManyToOne
    private UsuarioDto usuarioCreacionDto;
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Size(max = 100)
    @Column(name = "TERMINAL_CREACION")
    private String terminalCreacion;

    public UbigeoDto() {
    }

    public UbigeoDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

 

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcionCorta() {
        return descripcionCorta;
    }

    public void setDescripcionCorta(String descripcionCorta) {
        this.descripcionCorta = descripcionCorta;
    }

    public String getDescripcionCompleta() {
        return descripcionCompleta;
    }

    public void setDescripcionCompleta(String descripcionCompleta) {
        this.descripcionCompleta = descripcionCompleta;
    }

 

    public BigInteger getUbigeoPadre() {
        return ubigeoPadre;
    }

    public void setUbigeoPadre(BigInteger ubigeoPadre) {
        this.ubigeoPadre = ubigeoPadre;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCodigoInei() {
        return codigoInei;
    }

    public void setCodigoInei(String codigoInei) {
        this.codigoInei = codigoInei;
    }

   
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getTerminalCreacion() {
        return terminalCreacion;
    }

    public void setTerminalCreacion(String terminalCreacion) {
        this.terminalCreacion = terminalCreacion;
    }

    public Boolean isEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public UsuarioDto getUsuarioDto() {
        return usuarioDto;
    }

    public void setUsuarioDto(UsuarioDto usuarioDto) {
        this.usuarioDto = usuarioDto;
    }

    public ParametroDto getTipoDto() {
        return tipoDto;
    }

    public void setTipoDto(ParametroDto tipoDto) {
        this.tipoDto = tipoDto;
    }

    public Long getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Long prioridad) {
        this.prioridad = prioridad;
    }

    public UsuarioDto getUsuarioCreacionDto() {
        return usuarioCreacionDto;
    }

    public void setUsuarioCreacionDto(UsuarioDto usuarioCreacionDto) {
        this.usuarioCreacionDto = usuarioCreacionDto;
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
        if (!(object instanceof UbigeoDto)) {
            return false;
        }
        UbigeoDto other = (UbigeoDto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sys.core.dto.UbigeoDto[ id=" + id + " ]";
    }

}
