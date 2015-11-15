    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.core.mbean;

import common.Logger;
import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import sys.core.common.UtilCore;
import sys.core.dto.EmpresaDto;
import sys.core.dto.ParametroDto;
import sys.core.exception.DAOException;
import sys.core.manager.EmpresaManager;
import sys.core.manager.RecursosManager;
import sys.core.util.ConstantesCore;
import sys.core.view.mbean.GenericMBean;
import sys.core.view.mbean.SessionMBean;

/**
 *
 * @author admin
 */
@Controller("empresaMBean")
@Scope("session")
public class EmpresaMBean extends GenericMBean implements Serializable {

    private static Logger logger = Logger.getLogger(EmpresaMBean.class);
    private EmpresaDto dto;
    private EmpresaDto dtoFiltro;
    @Resource
    private EmpresaManager empresaManager;
    @Resource
    private RecursosManager recursosManager;
    @Autowired
    private SessionMBean sessionMBean;
    private List<EmpresaDto> listaFiltro;
    private String keyTitulo = "empresa.panel";
    private List<ParametroDto> listaEstados;

    public String iniciar() {
        dto = new EmpresaDto();
        dtoFiltro = new EmpresaDto();
        listaFiltro = null;
        return ConstantesCore.UrlNavegacion.URL_LISTA_EMPRESAS;
    }

    public String nuevo() {
        sessionMBean.setAccion(ConstantesCore.Formulario.NUEVO);
        sessionMBean.setTitulo(recursosManager.construirTitulo(keyTitulo, sessionMBean.getAccion()));
        this.dto = new EmpresaDto();
        return ConstantesCore.UrlNavegacion.URL_EMPRESA;
    }

    public String buscarData() {
        try {
            listaFiltro = empresaManager.obtenerEmpresas(dtoFiltro);
        } catch (DAOException ex) {
            logger.error(ex.getMessage());
            showError("ERROR EN LA BUSQUEDA");
        }
        return null;
    }

    public String ver() {
        sessionMBean.setAccion(ConstantesCore.Formulario.VER);
        sessionMBean.setTitulo(recursosManager.construirTitulo(keyTitulo, sessionMBean.getAccion()));
        return ConstantesCore.UrlNavegacion.URL_EMPRESA;
    }

    public String editar() {
        sessionMBean.setAccion(ConstantesCore.Formulario.EDITAR);
        sessionMBean.setTitulo(recursosManager.construirTitulo(keyTitulo, sessionMBean.getAccion()));
        return ConstantesCore.UrlNavegacion.URL_EMPRESA;
    }

    public String guardar() {
        dto.setUsuarioDto(sessionMBean.getSessionUsuarioDto());
        dto.setTerminal(sessionMBean.getSessionTerminal());
        try {
            if (sessionMBean.getAccion() == ConstantesCore.Formulario.EDITAR) {
                empresaManager.editar(dto);
            }
            showMessage(UtilCore.Internacionalizacion.getMensajeInternacional("form.general.mensaje.transaccion.ok"));
        } catch (DAOException ex) {
            logger.error(ex.getMessage());
        }
        return back();
    }

    public String back() {
        return ConstantesCore.UrlNavegacion.URL_LISTA_EMPRESAS;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        EmpresaMBean.logger = logger;
    }

    public EmpresaDto getDto() {
        return dto;
    }

    public void setDto(EmpresaDto dto) {
        this.dto = dto;
    }

    public EmpresaDto getDtoFiltro() {
        return dtoFiltro;
    }

    public void setDtoFiltro(EmpresaDto dtoFiltro) {
        this.dtoFiltro = dtoFiltro;
    }

    public EmpresaManager getEmpresaManager() {
        return empresaManager;
    }

    public void setEmpresaManager(EmpresaManager empresaManager) {
        this.empresaManager = empresaManager;
    }

    public List<EmpresaDto> getListaFiltro() {
        return listaFiltro;
    }

    public void setListaFiltro(List<EmpresaDto> listaFiltro) {
        this.listaFiltro = listaFiltro;
    }

    public RecursosManager getRecursosManager() {
        return recursosManager;
    }

    public void setRecursosManager(RecursosManager recursosManager) {
        this.recursosManager = recursosManager;
    }

    public SessionMBean getSessionMBean() {
        return sessionMBean;
    }

    public void setSessionMBean(SessionMBean sessionMBean) {
        this.sessionMBean = sessionMBean;
    }

    public String getKeyTitulo() {
        return keyTitulo;
    }

    public void setKeyTitulo(String keyTitulo) {
        this.keyTitulo = keyTitulo;
    }

    public List<ParametroDto> getListaEstados() {
        return recursosManager.obtenerParametrosActivosPorCatalogoAdd(ConstantesCore.CatalogoParametro.CATALOGO_PARAMETRO_ESTADOS, ConstantesCore.Parametro.SELECCIONAR);
    }

    public void setListaEstados(List<ParametroDto> listaEstados) {
        this.listaEstados = listaEstados;
    }
}
