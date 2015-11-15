/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.core.view.mbean;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;
import org.primefaces.model.MenuModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import sys.core.common.UtilCore;
import sys.core.dto.EmpresaDto;
import sys.core.dto.UsuarioDto;
import static sys.core.view.mbean.GenericMBean.showMessage;

/**
 *
 * @author admin
 */
@Controller("sessionMBean")
@Scope("session")
public class SessionMBean  extends  GenericMBean implements Serializable{

    

    private static Logger logger = Logger.getLogger(SessionMBean.class);
    private String titulo;
    private int accion;
     
    private String idioma = "es";
    private int cantidadRegistros = 10;
    private Boolean exportarExcel = Boolean.TRUE;
    private Boolean exportarCvs = Boolean.TRUE;
    private Boolean exportarPdf = Boolean.TRUE;
    private String sessionTerminal;
 
    private MenuModel menuModel;
    private UsuarioDto sessionUsuarioDto;
    //private Long sessionOrganizacion;
    private String mensajeExpiraSession = "";
    private EmpresaDto sessionEmpresaDto;
    private List<EmpresaDto> empresasUsuario;
    
    private Map<String, Boolean> privilegiosOpciones = new HashMap<String, Boolean>();
    

    /*
     public String salir() {        
     String ir = "/index";
     HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);        
     try {
     this.usuarioConectado = null;            
     session.invalidate();
     } catch (Exception e) {
     UtilCore.Log.error(e);
     }
     return ir;
     }*/
 
    public void cambiarEmpresa(javax.faces.event.AjaxBehaviorEvent a) {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        String url = extContext.encodeActionURL(fc.getApplication().getViewHandler().getActionURL(fc, "/inicio.xhtml"));

        //  System.out.println(this.sessionEmpresaDto.getDescripcion());

        try {
            extContext.redirect(url);
        } catch (IOException e1) {
            logger.error(e1);
        }
    }

    public String cambiarOrganizacionUsuario() {
        String to = "/inicio.xhtml";
        String mensajeTrx = UtilCore.Internacionalizacion.getMensajeInternacional("general.cambio.organizacion");
        showMessage(mensajeTrx + " " + this.sessionEmpresaDto.getDescripcion() + "-" + this.sessionEmpresaDto.getPaisDto().getDescripcion());
        return to;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        SessionMBean.logger = logger;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAccion() {
        return accion;
    }

    public void setAccion(int accion) {
        this.accion = accion;
    }


    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public int getCantidadRegistros() {
        return cantidadRegistros;
    }

    public void setCantidadRegistros(int cantidadRegistros) {
        this.cantidadRegistros = cantidadRegistros;
    }

    public Boolean isExportarExcel() {
        return exportarExcel;
    }

    public void setExportarExcel(Boolean exportarExcel) {
        this.exportarExcel = exportarExcel;
    }

    public Boolean isExportarCvs() {
        return exportarCvs;
    }

    public void setExportarCvs(Boolean exportarCvs) {
        this.exportarCvs = exportarCvs;
    }

    public Boolean isExportarPdf() {
        return exportarPdf;
    }

    public void setExportarPdf(Boolean exportarPdf) {
        this.exportarPdf = exportarPdf;
    }

    public String getSessionTerminal() {
        return sessionTerminal;
    }

    public void setSessionTerminal(String sessionTerminal) {
        this.sessionTerminal = sessionTerminal;
    }


    public MenuModel getMenuModel() {
        return menuModel;
    }

    public void setMenuModel(MenuModel menuModel) {
        this.menuModel = menuModel;
    }

    public UsuarioDto getSessionUsuarioDto() {
        return sessionUsuarioDto;
    }

    public void setSessionUsuarioDto(UsuarioDto sessionUsuarioDto) {
        this.sessionUsuarioDto = sessionUsuarioDto;
    }

    public String getMensajeExpiraSession() {
        return mensajeExpiraSession;
    }

    public void setMensajeExpiraSession(String mensajeExpiraSession) {
        this.mensajeExpiraSession = mensajeExpiraSession;
    }

    public EmpresaDto getSessionEmpresaDto() {
        return sessionEmpresaDto;
    }

    public void setSessionEmpresaDto(EmpresaDto sessionEmpresaDto) {
        this.sessionEmpresaDto = sessionEmpresaDto;
    }

    public List<EmpresaDto> getEmpresasUsuario() {
        return empresasUsuario;
    }

    public void setEmpresasUsuario(List<EmpresaDto> empresasUsuario) {
        this.empresasUsuario = empresasUsuario;
    }

    public Map<String, Boolean> getPrivilegiosOpciones() {
        return privilegiosOpciones;
    }

    public void setPrivilegiosOpciones(Map<String, Boolean> privilegiosOpciones) {
        this.privilegiosOpciones = privilegiosOpciones;
    }
 

 
}
