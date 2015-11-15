/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.core.dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 *
 * @author Indra
 */
@Entity
@Table(name = "SYS_CORE_EJECUCION_PROCESO")
public class EjecucionProcesoDto implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EjecucionProcesoDtoPK ejecucionProcesoDtoPK;
    @Column(name = "FECHA_HORA_INICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraInicio;
    @Column(name = "FECHA_HORA_FIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraFin;
    @Size(max = 1)
    @Column(name = "ESTADO")
    private String estado;
    @JoinColumn(name = "PROCESO", referencedColumnName = "NOMBRE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ProcesoDto procesoDto;
    
    @Column(name = "COMENTARIO")
    private String comentario;

    public EjecucionProcesoDto() {
    }

    public EjecucionProcesoDto(EjecucionProcesoDtoPK ejecucionProcesoDtoPK) {
        this.ejecucionProcesoDtoPK = ejecucionProcesoDtoPK;
    }

    public EjecucionProcesoDto(String proceso, String fecha, String hora) {
        this.ejecucionProcesoDtoPK = new EjecucionProcesoDtoPK(proceso, fecha, hora);
    }

    public EjecucionProcesoDtoPK getEjecucionProcesoDtoPK() {
        return ejecucionProcesoDtoPK;
    }

    public void setEjecucionProcesoDtoPK(EjecucionProcesoDtoPK ejecucionProcesoDtoPK) {
        this.ejecucionProcesoDtoPK = ejecucionProcesoDtoPK;
    }

    public Date getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(Date fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Date getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(Date fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ProcesoDto getProcesoDto() {
        return procesoDto;
    }

    public void setProcesoDto(ProcesoDto procesoDto) {
        this.procesoDto = procesoDto;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ejecucionProcesoDtoPK != null ? ejecucionProcesoDtoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EjecucionProcesoDto)) {
            return false;
        }
        EjecucionProcesoDto other = (EjecucionProcesoDto) object;
        if ((this.ejecucionProcesoDtoPK == null && other.ejecucionProcesoDtoPK != null) || (this.ejecucionProcesoDtoPK != null && !this.ejecucionProcesoDtoPK.equals(other.ejecucionProcesoDtoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.brain.core.dto.EjecucionProcesoDto[ ejecucionProcesoDtoPK=" + ejecucionProcesoDtoPK + " ]";
    }
    
}
