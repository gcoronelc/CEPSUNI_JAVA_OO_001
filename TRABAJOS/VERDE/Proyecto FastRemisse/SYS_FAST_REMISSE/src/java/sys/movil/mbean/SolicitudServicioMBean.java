    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.movil.mbean;

import sys.core.mbean.*;
import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import sys.core.exception.DAOException;
import sys.core.manager.RecursosManager;
import sys.core.util.ConstantesCore;
import sys.core.view.mbean.ApplicationMBean;
import sys.core.view.mbean.GenericMBean;
import sys.core.view.mbean.SessionMBean;
import sys.movil.dto.SolicitudServicioDto;
import sys.movil.dto.UsuarioExternoDto;
import sys.movil.manager.LocalizacionDataManager;
import sys.movil.manager.SolicitudServicioManager;

/**
 *
 * @author Indra
 */
@Controller("solicitudServicioMBean")
@Scope("session")
public class SolicitudServicioMBean extends GenericMBean implements Serializable {

    private static Logger logger = Logger.getLogger(UsuarioMBean.class);
    @Resource
    private SolicitudServicioManager manager;
    @Resource
    private LocalizacionDataManager managerLocalizacionData;
    @Resource
    private RecursosManager recursosManager;
    private List<SolicitudServicioDto> lista;
    private SolicitudServicioDto dto;
    private SolicitudServicioDto dtoFiltro;
    private String keyTitulo = "usuario.panel";
    @Autowired
    private ApplicationMBean applicationMBean;
    @Autowired
    private SessionMBean sessionMBean;

    public SolicitudServicioMBean() {
        this.dtoFiltro = new SolicitudServicioDto();

    }

    public String iniciar() throws DAOException {
        this.lista = null;
        this.dto = null;
        this.dtoFiltro = new SolicitudServicioDto();
        this.dtoFiltro.setUsuarioExternoDto(new UsuarioExternoDto());
        this.dtoFiltro.setChoferExternoDto(new UsuarioExternoDto());
        return ConstantesCore.UrlNavegacion.URL_LISTA_SOLICITUDES_SERVICIOS;
    }

    public void ver() {
        sessionMBean.setAccion(ConstantesCore.Formulario.VER);
        sessionMBean.setTitulo(recursosManager.construirTitulo(keyTitulo, sessionMBean.getAccion()));
        //return ConstantesCore.UrlNavegacion.URL_SOLICITUD_SERVICIO;
    }

    public void buscar() {
        try {
            this.lista = this.manager.obtener(this.dtoFiltro);
        } catch (DAOException ex) {
            logger.error("ERROR DE SISTEMA", ex);
            showError(ex.getMessage());
        }

    }

    public String retroceder() {
        this.dtoFiltro = new SolicitudServicioDto();
        this.dtoFiltro.setId(this.dto.getId());
        sessionMBean.setAccion(ConstantesCore.Formulario.LISTA);
        return ConstantesCore.UrlNavegacion.URL_LISTA_SOLICITUDES_SERVICIOS;
    }

    public SolicitudServicioDto getDto() {
        return dto;
    }

    public void setDto(SolicitudServicioDto dto) {
        this.dto = dto;
    }

    public String getKeyTitulo() {
        return keyTitulo;
    }

    public void setKeyTitulo(String keyTitulo) {
        this.keyTitulo = keyTitulo;
    }

    public List<SolicitudServicioDto> getLista() {
        return lista;
    }

    public void setLista(List<SolicitudServicioDto> lista) {
        this.lista = lista;
    }

    public SolicitudServicioDto getDtoFiltro() {
        return dtoFiltro;
    }

    public void setDtoFiltro(SolicitudServicioDto dtoFiltro) {
        this.dtoFiltro = dtoFiltro;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        SolicitudServicioMBean.logger = logger;
    }

    public SolicitudServicioManager getManager() {
        return manager;
    }

    public void setManager(SolicitudServicioManager manager) {
        this.manager = manager;
    }

    public LocalizacionDataManager getManagerLocalizacionData() {
        return managerLocalizacionData;
    }

    public void setManagerLocalizacionData(LocalizacionDataManager managerLocalizacionData) {
        this.managerLocalizacionData = managerLocalizacionData;
    }

    public RecursosManager getRecursosManager() {
        return recursosManager;
    }

    public void setRecursosManager(RecursosManager recursosManager) {
        this.recursosManager = recursosManager;
    }

    public ApplicationMBean getApplicationMBean() {
        return applicationMBean;
    }

    public void setApplicationMBean(ApplicationMBean applicationMBean) {
        this.applicationMBean = applicationMBean;
    }

    public SessionMBean getSessionMBean() {
        return sessionMBean;
    }

    public void setSessionMBean(SessionMBean sessionMBean) {
        this.sessionMBean = sessionMBean;
    }
}
