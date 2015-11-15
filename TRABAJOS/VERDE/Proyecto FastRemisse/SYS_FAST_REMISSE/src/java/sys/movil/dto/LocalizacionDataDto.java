/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.movil.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
import sys.core.dto.UsuarioDto;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "SYS_MOVIL_LOCALIZACION_DATA")
@XmlRootElement
@JsonAutoDetect
public class LocalizacionDataDto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "SEQ_LOCALIZACION_DATA")
    @TableGenerator(name = "SEQ_LOCALIZACION_DATA", table = "SYS_CORE_ENTIDAD", pkColumnName = "NOMBRE", valueColumnName = "CORRELATIVO", pkColumnValue = "LOCALIZACION_DATA", allocationSize = 1)
    private Long id;
    @JoinColumn(name = "USUARIO", referencedColumnName = "ID")
    @ManyToOne
    private UsuarioExternoDto usuarioExternoDto;
    @Size(max = 500)
    @Column(name = "TERMINAL")
    private String terminal;
    @Size(max = 500)
    @Column(name = "LATITUDE")
    private String latitude;
    @Size(max = 500)
    @Column(name = "LONGITUDE")
    private String longitude;
    @Size(max = 500)
    @Column(name = "ALTITUDE")
    private String altitude;
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "ESTADO")
    private int estado;

    public LocalizacionDataDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public UsuarioExternoDto getUsuarioExternoDto() {
        return usuarioExternoDto;
    }

    public void setUsuarioExternoDto(UsuarioExternoDto usuarioExternoDto) {
        this.usuarioExternoDto = usuarioExternoDto;
    }
 

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
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
        if (!(object instanceof LocalizacionDataDto)) {
            return false;
        }
        LocalizacionDataDto other = (LocalizacionDataDto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sys.movil.dto.LocalizacionDataDto[ id=" + id + " ]";
    }

}
