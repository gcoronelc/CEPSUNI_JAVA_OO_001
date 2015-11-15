/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.core.dto;


import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import sys.core.common.ConstantesCore;
import sys.core.common.UtilCore;

/**
 *
 * @author Indra
 */
@Entity
@Table(name = "SYS_CORE_PROCESO")
public class ProcesoDto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull    
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 500)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    
    
    @JoinColumn(name = "PROCEDIMIENTO", referencedColumnName = "PROCEDURE_NAME")
    @ManyToOne
    private ProcedureDto procedureDto;
    
    @Size(max = 10)
    @Column(name = "HORA_EJECUCION")
    private String horaEjecucion;
    @Column(name = "ESTADO")
    private Boolean estado;
    @Size(max = 50)
    @Column(name = "EXPRESION_QUARTZ")
    private String expresionQuartz;
    
    @Transient
    private String fechaEjecutada;
    @Transient
    private String horaEjecutada;
    
    @Column(name = "EXP_SEGUNDO")
    private int expSegundo;
    
    @Column(name = "EXP_MINUTO")
    private int expMinuto;
    
    @Column(name = "EXP_HORA")
    private int expHora;
    
    @Column(name = "EXP_DIA_MES")
    private int expDiaMes;
    
    @Column(name = "EXP_MES")
    private int expMes;
    
    @Column(name = "EXP_DIA_SEMANA")
    private int expDiaSemana;
    
    @Column(name = "EXP_ANIO")
    private int expAnio;
    
    @Transient
    private String expresionQuartzTexto;
    
    
    

    public ProcesoDto() {
    }

    public ProcesoDto(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ProcedureDto getProcedureDto() {
        return procedureDto;
    }

    public void setProcedureDto(ProcedureDto procedureDto) {
        this.procedureDto = procedureDto;
    }

    

    public String getHoraEjecucion() {
        return horaEjecucion;
    }

    public void setHoraEjecucion(String horaEjecucion) {
        this.horaEjecucion = horaEjecucion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    
    private String obtenerExpresion(int codigo, int lPad, char pad) {
        String exp="";
        if (codigo==ConstantesCore.ExpresionQuartzCodigos.TODOS){
            exp = ConstantesCore.ExpresionQuartzEtiquetas.TODOS;                    
        }else if (codigo==ConstantesCore.ExpresionQuartzCodigos.INCOGNITA){
            exp = ConstantesCore.ExpresionQuartzEtiquetas.INCOGNITA;                    
        }else if (codigo==ConstantesCore.ExpresionQuartzCodigos.PRIMERO){
            exp = ConstantesCore.ExpresionQuartzEtiquetas.PRIMERO;                    
        }else if (codigo==ConstantesCore.ExpresionQuartzCodigos.ULTIMO){
            exp = ConstantesCore.ExpresionQuartzEtiquetas.ULTIMO;                    
        }else {
            exp = UtilCore.Cadena.lPad(String.valueOf(codigo), lPad,pad) +" ";    
        }
        return exp;
    }

    public String getExpresionQuartz() {
        expresionQuartz = " ";        
        //expresionQuartz += obtenerExpresion(expSegundo,2,'0');
        expresionQuartz += obtenerExpresion(expMinuto,2,'0');
        expresionQuartz += obtenerExpresion(expHora,2,'0');
        expresionQuartz += obtenerExpresion(expDiaMes,2,'0');
        expresionQuartz += obtenerExpresion(expMes,2,'0');
        expresionQuartz += obtenerExpresion(expDiaSemana,2,'0');
        expresionQuartz += obtenerExpresion(expAnio,4,'0');       
        return expresionQuartz;
    }

    public void setExpresionQuartz(String expresionQuartz) {        
        this.expresionQuartz = expresionQuartz;
    }

    public String getFechaEjecutada() {
        return fechaEjecutada;
    }

    public void setFechaEjecutada(String fechaEjecutada) {
        this.fechaEjecutada = fechaEjecutada;
    }

    public String getHoraEjecutada() {
        return horaEjecutada;
    }

    public void setHoraEjecutada(String horaEjecutada) {
        this.horaEjecutada = horaEjecutada;
    }

    public int getExpAnio() {
        return expAnio;
    }

    public void setExpAnio(int expAnio) {
        this.expAnio = expAnio;
    }

    public int getExpDiaMes() {
        return expDiaMes;
    }

    public void setExpDiaMes(int expDiaMes) {
        this.expDiaMes = expDiaMes;
    }

    public int getExpDiaSemana() {
        return expDiaSemana;
    }

    public void setExpDiaSemana(int expDiaSemana) {
        this.expDiaSemana = expDiaSemana;
    }

    public int getExpHora() {
        return expHora;
    }

    public void setExpHora(int expHora) {
        this.expHora = expHora;
    }

    public int getExpMes() {
        return expMes;
    }

    public void setExpMes(int expMes) {
        this.expMes = expMes;
    }

    public int getExpMinuto() {
        return expMinuto;
    }

    public void setExpMinuto(int expMinuto) {
        this.expMinuto = expMinuto;
    }

    public int getExpSegundo() {
        return expSegundo;
    }

    public void setExpSegundo(int expSegundo) {
        this.expSegundo = expSegundo;
    }
    
    private String obtenerTextoAnio (int codigo) {
        String exp="";
        if (codigo==ConstantesCore.ExpresionQuartzCodigos.TODOS){
            exp = "DE TODOS LOS AÑOS";                    
        }else if (codigo==ConstantesCore.ExpresionQuartzCodigos.INCOGNITA){
            exp = ConstantesCore.ExpresionQuartzEtiquetas.INCOGNITA;                    
        }else if (codigo==ConstantesCore.ExpresionQuartzCodigos.PRIMERO){
            exp = ConstantesCore.ExpresionQuartzEtiquetas.PRIMERO;                    
        }else if (codigo==ConstantesCore.ExpresionQuartzCodigos.ULTIMO){
            exp = ConstantesCore.ExpresionQuartzEtiquetas.ULTIMO;                    
        }else {
            exp = "DEL AÑO " + codigo + " - ";
        }
        return exp;
    }
    
    private String obtenerTextoMes (int codigo) {
        String exp="";
        if (codigo==ConstantesCore.ExpresionQuartzCodigos.TODOS){
            exp = "DE TODOS LOS MESES";                    
        }else if (codigo==ConstantesCore.ExpresionQuartzCodigos.INCOGNITA){
            exp = ConstantesCore.ExpresionQuartzEtiquetas.INCOGNITA;                    
        }else if (codigo==ConstantesCore.ExpresionQuartzCodigos.PRIMERO){
            exp = ConstantesCore.ExpresionQuartzEtiquetas.PRIMERO;                    
        }else if (codigo==ConstantesCore.ExpresionQuartzCodigos.ULTIMO){
            exp = ConstantesCore.ExpresionQuartzEtiquetas.ULTIMO;                    
        }else {
            exp = "DE " + ConstantesCore.MESES_ANIO[codigo];
        }
        return exp;
    }
    
    private String obtenerTextoDiaMes (int codigo) {
        String exp="";
        if (codigo==ConstantesCore.ExpresionQuartzCodigos.TODOS){
            exp = "TODOS LOS DÍAS DEL MES";                    
        }else if (codigo==ConstantesCore.ExpresionQuartzCodigos.INCOGNITA){
            exp = " ";                    
        }else if (codigo==ConstantesCore.ExpresionQuartzCodigos.PRIMERO){
            exp = " PRIMER DÍA ";  
        }else if (codigo==ConstantesCore.ExpresionQuartzCodigos.ULTIMO){
            exp = " ÚLTIMO DÍA ";  
        }else {
            exp = "EL DIA" + UtilCore.Cadena.lPad(String.valueOf(codigo), 2,'0') +" ";  
        }
        return exp;
    }
    
    private String obtenerTextoDiaSemana (int codigo) {
        String exp="";
        if (codigo==ConstantesCore.ExpresionQuartzCodigos.TODOS){
            exp = " ";                    
        }else if (codigo==ConstantesCore.ExpresionQuartzCodigos.INCOGNITA){
            exp = " ";                    
        }else if (codigo==ConstantesCore.ExpresionQuartzCodigos.PRIMERO){
            exp = " ";                    
        }else if (codigo==ConstantesCore.ExpresionQuartzCodigos.ULTIMO){
            exp = " ";                    
        }else {
            exp = "EL " + ConstantesCore.DIAS_SEMANA[codigo] + ", ";
        }
        return exp;
    }
    
    private String obtenerTextoHora (int codigo) {
        String exp="";
        if (codigo==ConstantesCore.ExpresionQuartzCodigos.TODOS){
            exp = "EN TODOS LAS HORAS";                    
        }else if (codigo==ConstantesCore.ExpresionQuartzCodigos.INCOGNITA){
            exp = " ";                    
        }else if (codigo==ConstantesCore.ExpresionQuartzCodigos.PRIMERO){
            exp = " ";                    
        }else if (codigo==ConstantesCore.ExpresionQuartzCodigos.ULTIMO){
            exp = " ";                    
        }else {
            exp = "A LAS " + UtilCore.Cadena.lPad(String.valueOf(codigo), 2,'0') +" HORAS ";  
        }
        return exp;
    }
    
    private String obtenerTextoMin (int codigo) {
        String exp="";
        if (codigo==ConstantesCore.ExpresionQuartzCodigos.TODOS){
            exp = "PARA TODOS LOS MINUTOS";                    
        }else if (codigo==ConstantesCore.ExpresionQuartzCodigos.INCOGNITA){
            exp = " ";                    
        }else if (codigo==ConstantesCore.ExpresionQuartzCodigos.PRIMERO){
            exp = " ";                    
        }else if (codigo==ConstantesCore.ExpresionQuartzCodigos.ULTIMO){
            exp = " ";                    
        }else {
            exp = "EN EL MINUTO " + UtilCore.Cadena.lPad(String.valueOf(codigo), 2,'0') +" ";  
        }
        return exp;
    }

    public String getExpresionQuartzTexto() {
        expresionQuartzTexto ="";
        expresionQuartzTexto += obtenerTextoDiaSemana(this.expDiaSemana)+" ";
        expresionQuartzTexto += obtenerTextoDiaMes(this.expDiaMes)+" ";
        expresionQuartzTexto += obtenerTextoMes(this.expMes)+" ";
        expresionQuartzTexto += obtenerTextoAnio(this.expAnio)+" ";
        expresionQuartzTexto += obtenerTextoHora(this.expHora)+" ";
        expresionQuartzTexto += obtenerTextoMin(this.expMinuto)+" ";
        return expresionQuartzTexto;
    }

    public void setExpresionQuartzTexto(String expresionQuartzTexto) {
        this.expresionQuartzTexto = expresionQuartzTexto;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nombre != null ? nombre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProcesoDto)) {
            return false;
        }
        ProcesoDto other = (ProcesoDto) object;
        if ((this.nombre == null && other.nombre != null) || (this.nombre != null && !this.nombre.equals(other.nombre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.brain.core.dto.ProcesoDto[ nombre=" + nombre + " ]";
    }
    
}
