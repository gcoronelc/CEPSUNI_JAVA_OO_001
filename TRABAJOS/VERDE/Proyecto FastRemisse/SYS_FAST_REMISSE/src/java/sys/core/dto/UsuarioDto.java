/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.core.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "SYS_CORE_USUARIO")
@JsonSerialize
@JsonAutoDetect
@XmlRootElement
public class UsuarioDto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "SEQ_USUARIO")
    @TableGenerator(name = "SEQ_USUARIO", table = "SYS_CORE_ENTIDAD", pkColumnName = "NOMBRE", valueColumnName = "CORRELATIVO", pkColumnValue = "USUARIO", allocationSize = 1)
    private Long id;
    @Size(max = 200)
    @Column(name = "NOMBRES")
    private String nombres;
    @Size(max = 200)
    @Column(name = "APELLIDOS")
    private String apellidos;
    @Size(max = 200)
    @Column(name = "NOMBRES_COMPLETOS")
    private String nombresCompletos;
    @JoinColumn(name = "ROL" , referencedColumnName = "ID")
    @ManyToOne
    private RolDto rolDto;
    @Size(max = 16)
    @Column(name = "CONTRASENIA")
    private String contrasenia;
    @JoinColumn(name = "TIPO_USUARIO", referencedColumnName = "ID")
    @ManyToOne
    private ParametroDto tipoUsuarioDto;
    @JoinColumn(name = "TIPO_DOCUMENTO", referencedColumnName = "ID")
    @ManyToOne
    private ParametroDto tipoDocumentoDto;
    @Size(max = 200)
    @Column(name = "NUMERO_DOCUMENTO")
    private String numeroDocumento;
    @JoinColumn(name = "USUARIO", referencedColumnName = "ID")
    @ManyToOne
    private UsuarioDto usuarioDto;
    @JoinColumn(name = "USUARIO_CREACION", referencedColumnName = "ID")
    @ManyToOne
    private UsuarioDto usuarioCreacionDto;
    @Size(max = 200)
    @Column(name = "TERMINAL")
    private String terminal;
    @Size(max = 200)
    @Column(name = "TERMINAL_CREACION")
    private String terminalCreacion;
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Size(max = 200)
    @Column(name = "EMAIL_LABORAL")
    private String emailLaboral;
    @JoinColumn(name = "ESTADO" , referencedColumnName = "ID")
    @ManyToOne
    private ParametroDto estadoDto;
    @Column(name = "LOGIN")
    private String login;
    @Column(name = "CONTRASENIA_ACTUAL")
    private String contraseniaActual;

    public UsuarioDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombresCompletos() {
        return nombresCompletos;
    }

    public void setNombresCompletos(String nombresCompletos) {
        this.nombresCompletos = nombresCompletos;
    }

    public RolDto getRolDto() {
        return rolDto;
    }

    public void setRolDto(RolDto rolDto) {
        this.rolDto = rolDto;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public ParametroDto getTipoUsuarioDto() {
        return tipoUsuarioDto;
    }

    public void setTipoUsuarioDto(ParametroDto tipoUsuarioDto) {
        this.tipoUsuarioDto = tipoUsuarioDto;
    }

    public ParametroDto getTipoDocumentoDto() {
        return tipoDocumentoDto;
    }

    public void setTipoDocumentoDto(ParametroDto tipoDocumentoDto) {
        this.tipoDocumentoDto = tipoDocumentoDto;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public UsuarioDto getUsuarioDto() {
        return usuarioDto;
    }

    public void setUsuarioDto(UsuarioDto usuarioDto) {
        this.usuarioDto = usuarioDto;
    }

    public UsuarioDto getUsuarioCreacionDto() {
        return usuarioCreacionDto;
    }

    public void setUsuarioCreacionDto(UsuarioDto usuarioCreacionDto) {
        this.usuarioCreacionDto = usuarioCreacionDto;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getTerminalCreacion() {
        return terminalCreacion;
    }

    public void setTerminalCreacion(String terminalCreacion) {
        this.terminalCreacion = terminalCreacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getEmailLaboral() {
        return emailLaboral;
    }

    public void setEmailLaboral(String emailLaboral) {
        this.emailLaboral = emailLaboral;
    }

    public ParametroDto getEstadoDto() {
        return estadoDto;
    }

    public void setEstadoDto(ParametroDto estadoDto) {
        this.estadoDto = estadoDto;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getContraseniaActual() {
        return contraseniaActual;
    }

    public void setContraseniaActual(String contraseniaActual) {
        this.contraseniaActual = contraseniaActual;
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
        if (!(object instanceof UsuarioDto)) {
            return false;
        }
        UsuarioDto other = (UsuarioDto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sys.core.dto.UsuarioDto[ id=" + id + " ]";
    }

}
