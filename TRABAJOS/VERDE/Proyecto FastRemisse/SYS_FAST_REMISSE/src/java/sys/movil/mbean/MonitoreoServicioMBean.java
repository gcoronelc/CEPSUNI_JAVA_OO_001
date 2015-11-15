    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.movil.mbean;

import sys.core.mbean.*;
import java.io.Serializable;
import java.util.ArrayList;
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
import sys.core.common.UtilCore;
import sys.core.exception.DAOException;
import sys.core.json.EnvioNotificacionJson;
import sys.core.manager.RecursosManager;
import sys.core.util.ConstantesCore;
import sys.core.view.mbean.ApplicationMBean;
import sys.core.view.mbean.GenericMBean;
import sys.core.view.mbean.SessionMBean;
import sys.movil.dto.SolicitudServicioDto;
import sys.movil.manager.LocalizacionDataManager;
import sys.movil.manager.SolicitudServicioManager;

/**
 *
 * @author Indra
 */
@Controller("monitoreoServicioMBean")
@Scope("session")
public class MonitoreoServicioMBean extends GenericMBean implements Serializable {

    private static Logger logger = Logger.getLogger(UsuarioMBean.class);
    @Resource
    private SolicitudServicioManager manager;
    @Resource
    private LocalizacionDataManager managerLocalizacionData;
    @Resource
    private RecursosManager recursosManager;
    private List<SolicitudServicioDto> lista;
    private SolicitudServicioDto dto;
    private String keyTitulo = "usuario.panel";
    @Autowired
    private ApplicationMBean applicationMBean;
    @Autowired
    private SessionMBean sessionMBean;
    private MapModel mapModel;

    public MonitoreoServicioMBean() {
        this.dto = new SolicitudServicioDto();
        this.mapModel = new DefaultMapModel();

    }

    public String iniciar() throws DAOException {
        this.lista = null;
        this.dto = new SolicitudServicioDto();
        obtenerChoferes();
        return ConstantesCore.UrlNavegacion.URL_LISTA_MONITOREO_SERVICIOS;
    }

    public void obtenerChoferes() throws DAOException {
        if (!managerLocalizacionData.obtenerChoferesActivos().isEmpty()) {
            for (int i = 0; i < managerLocalizacionData.obtenerChoferesActivos().size(); i++) {
                LatLng coordenada = new LatLng(Double.parseDouble(managerLocalizacionData.obtenerChoferesActivos().get(i).getLatitude().toString()), Double.parseDouble(managerLocalizacionData.obtenerChoferesActivos().get(i).getLongitude().toString()));
                Marker d = new Marker(coordenada, "Chofer : " + managerLocalizacionData.obtenerChoferesActivos().get(i).getUsuarioExternoDto().getNombresCompletos() + " Nro Telefonico : " + managerLocalizacionData.obtenerChoferesActivos().get(i).getUsuarioExternoDto().getNroTelefono());
                d.setIcon(ConstantesCore.Imagenes.URL_LOGO_MARKERS);
                d.setClickable(Boolean.TRUE);
                mapModel.addOverlay(d);
            }
        }
    }

    public void guardar() {
        boolean methodState = true;
        try {
            ArrayList<String> l = new ArrayList<String>();
            dto.setFechaConfirmacion(UtilCore.Fecha.obtenerFechaActualDate());
            dto.setFecha(UtilCore.Fecha.obtenerFechaActualDate());
            dto.setTipoTransporte("Taxi");
            dto.setLongitudeChofer("0");
            dto.setLatitudChofer("0");
            dto.setComentario("Usuario enviado desde la web , usuario :" + sessionMBean.getSessionUsuarioDto().getNombresCompletos());
            dto.setEstado(7L);
            manager.nuevo(dto);
            l.add(dto.getChoferExternoDto().getRegistrationId());
            showMessage(UtilCore.Internacionalizacion.getMensajeInternacional("form.general.mensaje.transaccion.ok"));
            EnvioNotificacionJson.enviarMensajePrueba("7", l);
        } catch (Exception ex) {
            methodState = false;
            logger.error(ex.getMessage());
        }
        if (methodState){
            dto = new SolicitudServicioDto();
   
        }
    }

    public String ver() {
        sessionMBean.setAccion(ConstantesCore.Formulario.VER);
        sessionMBean.setTitulo(recursosManager.construirTitulo(keyTitulo, sessionMBean.getAccion()));
        return ConstantesCore.UrlNavegacion.URL_LISTA_MONITOREO_SERVICIOS;
    }

    /* public void buscar(ActionEvent ae) {
       
     try{
     this.lista = this.manager.obtenerConsulaMantenimiento(this.dtoFiltro);
     } catch (DAOException ex) {
     logger.error("ERROR DE SISTEMA", ex);
     showError(ex.getMessage());
     }

     }*/
    public String retroceder() {

        sessionMBean.setAccion(ConstantesCore.Formulario.LISTA);
        return ConstantesCore.UrlNavegacion.URL_LISTA_MONITOREO_SERVICIOS;
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

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        MonitoreoServicioMBean.logger = logger;
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

    public MapModel getMapModel() {
        return mapModel;
    }

    public void setMapModel(MapModel mapModel) {
        this.mapModel = mapModel;
    }
}
