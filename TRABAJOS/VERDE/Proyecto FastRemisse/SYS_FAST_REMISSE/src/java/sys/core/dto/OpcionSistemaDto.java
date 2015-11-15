/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.core.dto;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Indra
 */
@Entity
@Table(name = "SYS_CORE_OPCION_SISTEMA")
public class OpcionSistemaDto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "SEQ_OPCION_SISTEMA")
    @TableGenerator(name = "SEQ_OPCION_SISTEMA", table = "SYS_CORE_ENTIDAD", pkColumnName = "NOMBRE", valueColumnName = "CORRELATIVO", pkColumnValue = "OPCION_SISTEMA", allocationSize = 1)
    private Long id;
    @Column(name = "ESTADO")
    private Boolean estado;
    @Column(name = "COMENTARIO")
    private String comentario;
    @Column(name = "TIPO_MENU")
    private String tipoMenu;
    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "NIVEL")
    private Long nivel;
//    @Column(name = "PADRE")
//    private Long padre;
    @JoinColumn(name = "PADRE", referencedColumnName = "ID")
    @ManyToOne
    @LazyCollection(LazyCollectionOption.FALSE)
    private OpcionSistemaDto padreDto;
    @Column(name = "ACTION")
    private String action;
    @Column(name = "ICON")
    private String icon;
    @Column(name = "VALUE")
    private String value;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "opcionSistemaDto", fetch = FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<PermisoDto> listaPermisosRoles;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "padreDto", fetch = FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<OpcionSistemaDto> listaHijos;

    @Column(name = "MODULO")
    private String modulo;

    @Column(name = "REFERENCIA")
    private String referencia;

    @Column(name = "ON_CLICK")
    private String onClick;

    @Column(name = "URL")
    private String url;

    @Column(name = "ORDEN")
    private Long orden;

    @Transient
    private Boolean permiso;

    public OpcionSistemaDto() {
    }

    public OpcionSistemaDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
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

    public Long getNivel() {
        return nivel;
    }

    public void setNivel(Long nivel) {
        this.nivel = nivel;
    }

    public List<OpcionSistemaDto> getListaHijos() {
        return listaHijos;
    }

    public void setListaHijos(List<OpcionSistemaDto> listaHijos) {
        this.listaHijos = listaHijos;
    }

    public OpcionSistemaDto getPadreDto() {
        return padreDto;
    }

    public void setPadreDto(OpcionSistemaDto padreDto) {
        this.padreDto = padreDto;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTipoMenu() {
        return tipoMenu;
    }

    public void setTipoMenu(String tipoMenu) {
        this.tipoMenu = tipoMenu;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<PermisoDto> getListaPermisosRoles() {
        return listaPermisosRoles;
    }

    public void setListaPermisosRoles(List<PermisoDto> listaPermisosRoles) {
        this.listaPermisosRoles = listaPermisosRoles;
    }
    public String getOnClick() {
        return onClick;
    }

    public void setOnClick(String onClick) {
        this.onClick = onClick;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getOrden() {
        return orden;
    }

    public void setOrden(Long orden) {
        this.orden = orden;
    }

    public Boolean getPermiso() {
        return permiso;
    }

    public void setPermiso(Boolean permiso) {
        this.permiso = permiso;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
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
        if (!(object instanceof OpcionSistemaDto)) {
            return false;
        }
        OpcionSistemaDto other = (OpcionSistemaDto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sys.core.dto.OpcionSistemaDto[ id=" + id + " ]";
    }
}
