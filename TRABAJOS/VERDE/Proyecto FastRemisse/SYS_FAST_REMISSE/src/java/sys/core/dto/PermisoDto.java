/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.core.dto;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "SYS_CORE_PERMISO")
public class PermisoDto implements Serializable {

   
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "SEQ_PERMISO")
    @TableGenerator(name = "SEQ_PERMISO", table = "SYS_CORE_ENTIDAD", pkColumnName = "NOMBRE", valueColumnName = "CORRELATIVO", pkColumnValue = "PERMISO", allocationSize = 1)
    private Long id;
   
    @Column(name = "ESTADO")
    private Boolean estado;
    
    @JoinColumn(name = "OPCION_SISTEMA", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.FALSE)
    private OpcionSistemaDto opcionSistemaDto;
    
    @JoinColumn(name = "ROL", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.FALSE)
    private RolDto rolDto;

    public PermisoDto() {
    }

    public PermisoDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getEstado() {
        if (estado == null){
            estado =  false;
        }
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public OpcionSistemaDto getOpcionSistemaDto() {
        return opcionSistemaDto;
    }

    public void setOpcionSistemaDto(OpcionSistemaDto opcionSistemaDto) {
        this.opcionSistemaDto = opcionSistemaDto;
    }

    public RolDto getRolDto() {
        return rolDto;
    }

    public void setRolDto(RolDto rolDto) {
        this.rolDto = rolDto;
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
        if (!(object instanceof PermisoDto)) {
            return false;
        }
        PermisoDto other = (PermisoDto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sys.core.dto.PermisoDto[ id=" + id + " ]";
    }

}
