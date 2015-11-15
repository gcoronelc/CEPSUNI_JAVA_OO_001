/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import sys.core.dto.ParametroDto;
import sys.core.dto.UsuarioDto;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "SYS_MOVIL_USUARIO_EXTERNO")
public class UsuarioExternoDto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "SEQ_USUARIO_EXTERNO")
    @TableGenerator(name = "SEQ_USUARIO_EXTERNO", table = "SYS_CORE_ENTIDAD", pkColumnName = "NOMBRE", valueColumnName = "CORRELATIVO", pkColumnValue = "USUARIO_EXTERNO", allocationSize = 1)
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
    @Size(max = 16)
    @Column(name = "CONTRASENIA")
    private String contrasenia;
    @Column(name = "TIPO_USUARIO")
    private Long tipoUsuario;
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
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "ESTADO")
    private Boolean estado;
    @Size(max = 200)
    @Column(name = "LOGIN")
    private String login;
    @Column(name = "CONTRASENIA_ACTUAL")
    private String contraseniaActual;
    @Column(name = "REGISTRATION_ID")
    private String registrationId;
    @Column(name = "NRO_TELEFONO")
    private String nroTelefono;
    @JoinColumn(name = "TIPO_AUTO", referencedColumnName = "ID")
    @ManyToOne
    private ParametroDto tipoAutoDto;
    @JoinColumn(name = "MARCA_AUTO", referencedColumnName = "ID")
    @ManyToOne
    private ParametroDto marcaAutoDto;
    @Column(name = "COLOR_AUTO")
    private String colorAuto;
    @Column(name = "NRO_PLACA")
    private String nroPlaca;
    @Column(name = "LICENCIA")
    private Boolean licencia;
    @Column(name = "LICENCIA_NRO")
    private String licenciaNro;
    @Column(name = "MODELO")
    private String modelo;
    @Column(name = "IMAGEN_PERFIL")
    private byte[] imagenPerfil;

    public UsuarioExternoDto() {
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

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getTerminal() {
        return terminal;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getContraseniaActual() {
        return contraseniaActual;
    }

    public void setContraseniaActual(String contraseniaActual) {
        this.contraseniaActual = contraseniaActual;
    }

    public String getNroTelefono() {
        return nroTelefono;
    }

    public void setNroTelefono(String nroTelefono) {
        this.nroTelefono = nroTelefono;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(Long tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
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

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public ParametroDto getTipoAutoDto() {
        return tipoAutoDto;
    }

    public void setTipoAutoDto(ParametroDto tipoAutoDto) {
        this.tipoAutoDto = tipoAutoDto;
    }

    public ParametroDto getMarcaAutoDto() {
        return marcaAutoDto;
    }

    public void setMarcaAutoDto(ParametroDto marcaAutoDto) {
        this.marcaAutoDto = marcaAutoDto;
    }

    public String getColorAuto() {
        return colorAuto;
    }

    public void setColorAuto(String colorAuto) {
        this.colorAuto = colorAuto;
    }

    public String getNroPlaca() {
        return nroPlaca;
    }

    public void setNroPlaca(String nroPlaca) {
        this.nroPlaca = nroPlaca;
    }

    public Boolean getLicencia() {
        return licencia;
    }

    public void setLicencia(Boolean licencia) {
        this.licencia = licencia;
    }

    public String getLicenciaNro() {
        return licenciaNro;
    }

    public void setLicenciaNro(String licenciaNro) {
        this.licenciaNro = licenciaNro;
    }

    public ParametroDto getTipoDocumentoDto() {
        return tipoDocumentoDto;
    }

    public void setTipoDocumentoDto(ParametroDto tipoDocumentoDto) {
        this.tipoDocumentoDto = tipoDocumentoDto;
    }

    public byte[] getImagenPerfil() {
        return imagenPerfil;
    }

    public void setImagenPerfil(byte[] imagenPerfil) {
        this.imagenPerfil = imagenPerfil;
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
        if (!(object instanceof UsuarioExternoDto)) {
            return false;
        }
        UsuarioExternoDto other = (UsuarioExternoDto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sys.movil.dto.UsuarioExternoDto[ id=" + id + " ]";
    }
}
