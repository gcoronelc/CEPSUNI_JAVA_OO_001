/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.core.dto;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "SYS_CORE_CATALOGO_PARAMETRO")
public class CatalogoParametroDto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "SEQ_CATALOGO_PARAMETRO")
    @TableGenerator(name = "SEQ_CATALOGO_PARAMETRO", table = "SYS_CORE_ENTIDAD", pkColumnName = "NOMBRE", valueColumnName = "CORRELATIVO", pkColumnValue = "CATALOGO_PARAMETRO", allocationSize = 1)
    private Long id;
    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "COMENTARIO")
    private String comentario;
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private Boolean estado;
    @Basic(optional = false)
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "INLINESTYLE")
    private String inlinestyle;
    @Basic(optional = false)
    @Column(name = "TERMINAL")
    private String terminal;
    @Basic(optional = false)
    @JoinColumn(name = "USUARIO", referencedColumnName = "ID")
    @ManyToOne
    private UsuarioDto usuarioDto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "catalogoParametroDto", fetch = FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ParametroDto> listaParametros;
    @Transient
    private String estadoTexto;

    public CatalogoParametroDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getInlinestyle() {
        return inlinestyle;
    }

    public void setInlinestyle(String inlinestyle) {
        this.inlinestyle = inlinestyle;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

 

    public UsuarioDto getUsuarioDto() {
        return usuarioDto;
    }

    public void setUsuarioDto(UsuarioDto usuarioDto) {
        this.usuarioDto = usuarioDto;
    }

     public String getEstadoTexto() {
        return (this.estado == Boolean.TRUE ? "Si" : "No");
    }

    public void setEstadoTexto(String estadoTexto) {
        this.estadoTexto = estadoTexto;
    }


    public List<ParametroDto> getListaParametros() {
        return listaParametros;
    }

    public void setListaParametros(List<ParametroDto> listaParametros) {
        this.listaParametros = listaParametros;
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
        if (!(object instanceof CatalogoParametroDto)) {
            return false;
        }
        CatalogoParametroDto other = (CatalogoParametroDto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sys.core.dto.CatalogoParametroDto[ id=" + id + " ]";
    }
}
