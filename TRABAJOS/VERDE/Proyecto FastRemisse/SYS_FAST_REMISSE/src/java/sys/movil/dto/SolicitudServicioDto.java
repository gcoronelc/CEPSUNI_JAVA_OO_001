/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.movil.dto;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import sys.core.dto.ComunDto;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "SYS_MOVIL_SOLICITUD_SERVICIO")
public class SolicitudServicioDto extends ComunDto implements Serializable {

    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "SEQ_SOLICITUD_SERVICIO")
    @TableGenerator(name = "SEQ_SOLICITUD_SERVICIO", table = "SYS_CORE_ENTIDAD", pkColumnName = "NOMBRE", valueColumnName = "CORRELATIVO", pkColumnValue = "SOLICITUD_SERVICIO", allocationSize = 1)
    @Id
    private Long id;
    @JoinColumn(name = "USUARIO", referencedColumnName = "ID")
    @ManyToOne
    private UsuarioExternoDto usuarioExternoDto;
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "ESTADO")
    private Long estado;
    @Size(max = 500)
    @Column(name = "LONGITUDE_USUARIO")
    private String longitudeUsuario;
    @Size(max = 500)
    @Column(name = "LATITUD_USUARIO")
    private String latitudUsuario;
    @Size(max = 500)
    @Column(name = "LONGITUDE_CHOFER")
    private String longitudeChofer;
    @Size(max = 500)
    @Column(name = "LATITUD_CHOFER")
    private String latitudChofer;
    @Size(max = 1000)
    @Column(name = "COMENTARIO")
    private String comentario;
    @JoinColumn(name = "CHOFER", referencedColumnName = "ID")
    @ManyToOne
    private UsuarioExternoDto choferExternoDto;
    @Size(max = 4000)
    @Column(name = "NUMERO")
    private String numero;
    @Size(max = 30)
    @Column(name = "ORIGEN")
    private String origen;
    @Size(max = 4000)
    @Column(name = "DISTRITO")
    private String distrito;
    @Column(name = "TIPO_TRANSPORTE")
    private String tipoTransporte;
    @Column(name = "REFERENCIA")
    private String referencia;
    @Column(name = "FECHA_CONFIRMACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaConfirmacion;
    @Transient
    private String estadoTexto;

    public SolicitudServicioDto() {
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

    public Long getEstado() {
        return estado;
    }

    public void setEstado(Long estado) {
        this.estado = estado;
    }

    public String getLatitudUsuario() {
        return latitudUsuario;
    }

    public void setLatitudUsuario(String latitudUsuario) {
        this.latitudUsuario = latitudUsuario;
    }

    public String getLongitudeUsuario() {
        return longitudeUsuario;
    }

    public void setLongitudeUsuario(String longitudeUsuario) {
        this.longitudeUsuario = longitudeUsuario;
    }

    public String getLongitudeChofer() {
        return longitudeChofer;
    }

    public void setLongitudeChofer(String longitudeChofer) {
        this.longitudeChofer = longitudeChofer;
    }

    public String getLatitudChofer() {
        return latitudChofer;
    }

    public void setLatitudChofer(String latitudChofer) {
        this.latitudChofer = latitudChofer;
    }

    public Date getFechaConfirmacion() {
        return fechaConfirmacion;
    }

    public void setFechaConfirmacion(Date fechaConfirmacion) {
        this.fechaConfirmacion = fechaConfirmacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public UsuarioExternoDto getUsuarioExternoDto() {
        return usuarioExternoDto;
    }

    public void setUsuarioExternoDto(UsuarioExternoDto usuarioExternoDto) {
        this.usuarioExternoDto = usuarioExternoDto;
    }

    public UsuarioExternoDto getChoferExternoDto() {
        return choferExternoDto;
    }

    public void setChoferExternoDto(UsuarioExternoDto choferExternoDto) {
        this.choferExternoDto = choferExternoDto;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getTipoTransporte() {
        return tipoTransporte;
    }

    public void setTipoTransporte(String tipoTransporte) {
        this.tipoTransporte = tipoTransporte;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
////Estados de servicio 0 enviado , 1 tomado  , 2 pasajero a bordo , 3 cancelado , 4 cerrado

    public String getEstadoTexto() {
        if (estado.equals(0L)) {
            estadoTexto = "ENVIADO POR EL USUARIO";
        }
        if (estado.equals(1L)) {
            estadoTexto = "ATENDIDO";
        }
        if (estado.equals(3L)) {
            estadoTexto = "CANCELADO";
        }
        if (estado.equals(4L)) {
            estadoTexto = "REALIZADO";
        }
        if (estado.equals(6L)) {
            estadoTexto = "EN CURSO - PASAJERO A BORDO";
        }
        if (estado.equals(7L)) {
            estadoTexto = "ENVIADO POR EL ADMINISTRADOR";
        }
        return estadoTexto;
    }

    public void setEstadoTexto(String estadoTexto) {
        this.estadoTexto = estadoTexto;
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
        if (!(object instanceof SolicitudServicioDto)) {
            return false;
        }
        SolicitudServicioDto other = (SolicitudServicioDto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sys.movil.dto.SolicitudServicioDto[ id=" + id + " ]";
    }
}
