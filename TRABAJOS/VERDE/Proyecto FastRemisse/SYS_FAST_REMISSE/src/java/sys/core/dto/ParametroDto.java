/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.core.dto;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import sys.core.dto.CatalogoParametroDto;

/**
 *
 * @author Indra
 */
@Entity
@Table(name = "SYS_CORE_PARAMETRO")
@XmlRootElement
public class ParametroDto implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "SEQ_PARAMETRO")
    @TableGenerator(name = "SEQ_PARAMETRO", table = "SYS_CORE_ENTIDAD", pkColumnName = "NOMBRE", valueColumnName = "CORRELATIVO", pkColumnValue = "PARAMETRO", allocationSize = 1)
    private Long id;
    @Column(name = "COMENTARIO")
    private String comentario;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "DESCRIPCION_CORTA")
    private String descripcionCorta;
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private Boolean estado;
    @Column(name = "INLINESTYLE")
    private String inlinestyle;
    @Column(name = "TIPO")
    private String tipo;
    @Column(name = "VALOR_BOOLEAN")
    private Boolean valorBoolean;
    @Column(name = "VALOR_CADENA")
    private String valorCadena;
    @Column(name = "VALOR_ENTERO")
    private Long valorEntero;
    @Column(name = "VALOR_FECHA")
    @Temporal(TemporalType.DATE)
    private Date valorFecha;
    @Column(name = "VALOR_REAL")
    private BigDecimal valorReal;
    @JoinColumn(name = "CATALOGO_PARAMETRO", referencedColumnName = "ID")
    @ManyToOne
    private CatalogoParametroDto catalogoParametroDto;
    @JoinColumn(name = "CATALOGO_PARAMETRO_PADRE", referencedColumnName = "ID")
    @ManyToOne
    private CatalogoParametroDto catalogoParametroPadreDto;
    @JoinColumn(name = "PARAMETRO_PADRE", referencedColumnName = "ID")
    @ManyToOne
    private ParametroDto parametroPadreDto;
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "TERMINAL")
    private String terminal;
    @Column(name = "USUARIO")
    private Long usuario;
    @Transient
    private String estadoTexto;
    @Transient
    private String valorBooleanTexto;

    public ParametroDto() {
    }

    public ParametroDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getInlinestyle() {
        return inlinestyle;
    }

    public void setInlinestyle(String inlinestyle) {
        this.inlinestyle = inlinestyle;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Boolean getValorBoolean() {
        return valorBoolean;
    }

    public void setValorBoolean(Boolean valorBoolean) {
        this.valorBoolean = valorBoolean;
    }

    public String getValorCadena() {
        return valorCadena;
    }

    public void setValorCadena(String valorCadena) {
        this.valorCadena = valorCadena;
    }

    public Long getValorEntero() {
        return valorEntero;
    }

    public void setValorEntero(Long valorEntero) {
        this.valorEntero = valorEntero;
    }

    public Date getValorFecha() {
        return valorFecha;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public void setValorFecha(Date valorFecha) {
        this.valorFecha = valorFecha;
    }

    public BigDecimal getValorReal() {
        return valorReal;
    }

    public void setValorReal(BigDecimal valorReal) {
        this.valorReal = valorReal;
    }

    public CatalogoParametroDto getCatalogoParametroPadreDto() {
        return catalogoParametroPadreDto;
    }

    public void setCatalogoParametroPadreDto(CatalogoParametroDto catalogoParametroPadreDto) {
        this.catalogoParametroPadreDto = catalogoParametroPadreDto;
    }

    public ParametroDto getParametroPadreDto() {
        return parametroPadreDto;
    }

    public void setParametroPadreDto(ParametroDto parametroPadreDto) {
        this.parametroPadreDto = parametroPadreDto;
    }

    public CatalogoParametroDto getCatalogoParametroDto() {
        return catalogoParametroDto;
    }

    public void setCatalogoParametroDto(CatalogoParametroDto catalogoParametroDto) {
        this.catalogoParametroDto = catalogoParametroDto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public Long getUsuario() {
        return usuario;
    }

    public void setUsuario(Long usuario) {
        this.usuario = usuario;
    }

    public String getEstadoTexto() {
        return (this.estado == Boolean.TRUE ? "SI" : "NO");
    }

    public void setEstadoTexto(String estadoTexto) {
        this.estadoTexto = estadoTexto;
    }

    public String getValorBooleanTexto() {
        return (this.valorBoolean == Boolean.TRUE ? "SI" : "NO");
    }

    public void setValorBooleanTexto(String valorBooleanTexto) {
        this.valorBooleanTexto = valorBooleanTexto;
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
        if (!(object instanceof ParametroDto)) {
            return false;
        }
        ParametroDto other = (ParametroDto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sys.core.dto.ParametroDto[ id=" + id + " ]";
    }
}
