/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.core.json;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sys.core.common.UtilCore;
import sys.core.exception.DAOException;
import sys.core.view.mbean.ApplicationMBean;
import sys.movil.dto.LocalizacionDataDto;
import sys.movil.dto.SolicitudServicioDto;
import sys.movil.manager.LocalizacionDataManager;
import sys.movil.manager.MonitoreoServicioManager;
import sys.movil.manager.SolicitudServicioManager;
import sys.movil.manager.UsuarioExternoManager;

/**
 *
 * @author admin
 */
@Controller
@RequestMapping(value = "solicitudServicioJson")
public class SolicitudServicioJson implements Serializable {

    private static String API_KEY = "";
    @Autowired
    private ApplicationMBean applicationMBean;
    @Resource
    private UsuarioExternoManager usuarioExternoManager;
    @Resource
    private LocalizacionDataManager localizacionDataManager;
    @Resource
    private MonitoreoServicioManager monitoreoServicioManager;
    private SolicitudServicioDto solServicioDto;
    private EnvioNotificacionJson envioNotificacionJson;

    @RequestMapping(value = "/solicitarServicio/{strLatitudEmisor},{strLongitudeEmisor}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List solicitarServicio(@PathVariable("strLatitudEmisor") String strLatitudEmisor, @PathVariable("strLongitudeEmisor") String strLongitudeEmisor) throws DAOException, IOException {
        String mensaje = "";
        List listaChoferes = new ArrayList();
        try {
            if (!localizacionDataManager.obtenerChoferesActivos().isEmpty()) {
                for (int i = 0; i < localizacionDataManager.obtenerChoferesActivos().size(); i++) {
                    if (UtilCore.getDistance(Double.parseDouble(strLatitudEmisor), Double.parseDouble(strLongitudeEmisor), Double.parseDouble(localizacionDataManager.obtenerChoferesActivos().get(i).getLatitude()), Double.parseDouble(localizacionDataManager.obtenerChoferesActivos().get(i).getLongitude())) <= 5000) {
                        listaChoferes.add(localizacionDataManager.obtenerChoferesActivos().get(i).getUsuarioExternoDto().getId());
                        //System.out.println("Posicion" + UtilCore.getDistance(Double.parseDouble(strLatitudEmisor), Double.parseDouble(strLongitudeEmisor), Double.parseDouble(localizacionDataManager.obtenerChoferesActivos().get(i).getLatitude()), Double.parseDouble(localizacionDataManager.obtenerChoferesActivos().get(i).getLongitude())));
                    }
                    if (listaChoferes.isEmpty()) {
                        List listaDistancia = new ArrayList();
                        for (int k = 0; k < localizacionDataManager.obtenerChoferesActivos().size(); k++) {
                            listaDistancia.add(UtilCore.getDistance(Double.parseDouble(strLatitudEmisor), Double.parseDouble(strLongitudeEmisor), Double.parseDouble(localizacionDataManager.obtenerChoferesActivos().get(i).getLatitude()), Double.parseDouble(localizacionDataManager.obtenerChoferesActivos().get(i).getLongitude())));
                            System.out.println("distancia" + UtilCore.getDistance(Double.parseDouble(strLatitudEmisor), Double.parseDouble(strLongitudeEmisor), Double.parseDouble(localizacionDataManager.obtenerChoferesActivos().get(i).getLatitude()), Double.parseDouble(localizacionDataManager.obtenerChoferesActivos().get(i).getLongitude())));
                        }
                        int index = UtilCore.calcularMenor(listaDistancia);
                        listaChoferes.add(localizacionDataManager.obtenerChoferesActivos().get(index).getUsuarioExternoDto().getId());
                    }
                }
            } else {
                listaChoferes = null;
            }
        } catch (Exception ex) {
            mensaje = "Error " + ex.getMessage();
        }
        return listaChoferes;
    }

    /*  @RequestMapping(value = "/enviarSolicitudes/{strCadenaChoferes},{strMensaje},{idUsuario},{strDireccion},{strReferencia},{strNumeroReferencia}", method = RequestMethod.GET, produces = "application/json")
     public @ResponseBody
     String enviarSolicitudes(@PathVariable String strCadenaChoferes, @PathVariable String strMensaje, @PathVariable String idUsuario, @PathVariable String strDireccion, @PathVariable String strReferencia, @PathVariable String strNumeroReferencia) throws DAOException, IOException {
     List<String> listaRegistration_ID = new ArrayList<String>();
     String mensaje = "ENVIADO CORRECTAMENTE ";
     List<String> listaChoferes = new ArrayList<String>();
     String valores = strCadenaChoferes.replaceAll("\"", "");
     StringTokenizer tk = new StringTokenizer(valores, ",");
     while (tk.hasMoreTokens()) {
     listaChoferes.add(tk.nextToken());
     }
     if (!listaChoferes.isEmpty()) {
     for (int i = 0; i < listaChoferes.size(); i++) {
     listaRegistration_ID.add(usuarioExternoManager.obtenerPorId(Long.valueOf(listaChoferes.get(i).toString())).getRegistrationId());
     }
     strMensaje = idUsuario + "," + strDireccion + "," + strReferencia + "," + strNumeroReferencia;
     envioNotificacionJson.enviarMensajePrueba(strMensaje, listaRegistration_ID);
     }
     return mensaje;
     }*/
    @RequestMapping(value = "/enviarSolicitudes/{strCadenaChoferes},{strMensaje},{idUsuario},{strOrigen},{strNumero},{strDistrito},{strReferencia},{strTipoTransporte},{strLatitudUsuario},{strLongitudeUsuario}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String enviarSolicitudes(@PathVariable("strCadenaChoferes") String strCadenaChoferes, @PathVariable("strMensaje") String strMensaje, @PathVariable("idUsuario") String idUsuario, @PathVariable("strOrigen") String strOrigen, @PathVariable("strNumero") String strNumero, @PathVariable("strDistrito") String strDistrito, @PathVariable("strReferencia") String strReferencia, @PathVariable("strTipoTransporte") String strTipoTransporte, @PathVariable("strLatitudUsuario") String strLatitudUsuario, @PathVariable("strLongitudeUsuario") String strLongitudeUsuario) throws DAOException, IOException {
        List<String> listaRegistration_ID = new ArrayList<String>();
        String idSolicitud = "Error";
        List<String> listaChoferes = new ArrayList<String>();
        String valores = strCadenaChoferes.replaceAll("\"", "");
        StringTokenizer tk = new StringTokenizer(valores, "|");
        while (tk.hasMoreTokens()) {
            listaChoferes.add(tk.nextToken());
        }
        solServicioDto = new SolicitudServicioDto();
        solServicioDto.setUsuarioExternoDto(usuarioExternoManager.obtenerPorId(Long.valueOf(idUsuario)));
        solServicioDto.setOrigen(String.valueOf(strOrigen));
        solServicioDto.setNumero(String.valueOf(strNumero));
        solServicioDto.setDistrito(String.valueOf(strDistrito));
        solServicioDto.setReferencia(strReferencia);
        solServicioDto.setTipoTransporte(strTipoTransporte);
        solServicioDto.setFecha(UtilCore.Fecha.obtenerFechaActualDate());
        solServicioDto.setLatitudUsuario(String.valueOf(strLatitudUsuario));
        solServicioDto.setLongitudeUsuario(String.valueOf(strLongitudeUsuario));
        //Estados de servicio 0 enviado , 1 tomado  , 2 pasajero a bordo , 3 cancelado , 4 cerrado
        solServicioDto.setEstado(0L);
        monitoreoServicioManager.nuevo(solServicioDto);
        if (!listaChoferes.isEmpty()) {
            for (int i = 0; i < listaChoferes.size(); i++) {
                listaRegistration_ID.add(usuarioExternoManager.obtenerPorId(Long.valueOf(listaChoferes.get(i).toString())).getRegistrationId());
            }
            String mensaje = "0" + "," + solServicioDto.getId() + "," + solServicioDto.getUsuarioExternoDto().getId() + "," + "2";
            idSolicitud = solServicioDto.getId().toString();
            envioNotificacionJson.enviarMensajePrueba(mensaje, listaRegistration_ID);
        }
        return idSolicitud;
    }

    @RequestMapping(value = "/obtenerPorId/{idServicio}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String obtenerPorId(@PathVariable("idServicio") Long idServicio) throws DAOException, IOException {
        String cadenaClase = "";
        solServicioDto = new SolicitudServicioDto();
        solServicioDto = monitoreoServicioManager.obtenerPorId(idServicio);
        cadenaClase = solServicioDto.getId() + "," + solServicioDto.getOrigen() + "," + solServicioDto.getNumero() + "," + solServicioDto.getDistrito() + "," + solServicioDto.getReferencia() + "," + solServicioDto.getTipoTransporte();
        if (solServicioDto.getUsuarioExternoDto() != null) {
            cadenaClase += "," + solServicioDto.getUsuarioExternoDto().getId().toString();
        } else {
            cadenaClase += "," + "0";
        }
        if (solServicioDto.getChoferExternoDto() != null) {
            cadenaClase += "," + solServicioDto.getChoferExternoDto().getId().toString();
        } else {
            cadenaClase += "," + "0";
        }
        if (solServicioDto.getLatitudUsuario() != null && solServicioDto.getLongitudeUsuario() != null) {
            cadenaClase += "," + solServicioDto.getLongitudeUsuario().toString() + "," + solServicioDto.getLongitudeUsuario();
        } else {
            cadenaClase += "," + "0.0" + "," + "0.0";
        }
        if (solServicioDto.getLatitudChofer() != null && solServicioDto.getLongitudeChofer() != null) {
            cadenaClase += "," + solServicioDto.getLatitudChofer().toString() + "," + solServicioDto.getLongitudeChofer();
        } else {
            cadenaClase += "," + "0.0" + "," + "0.0";
        }
        return cadenaClase;
    }

    @RequestMapping(value = "/obtenerUltimosServiciosRealizados/{idChofer}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List obtenerServiciosRealizados(@PathVariable("idChofer") Long idChofer) throws DAOException, IOException {
        List cadenaClase = new ArrayList();
        List<SolicitudServicioDto> solServicioDtoList = new ArrayList<SolicitudServicioDto>();
        solServicioDtoList = monitoreoServicioManager.obtenerServiciosRealizados(idChofer);
        if (!solServicioDtoList.isEmpty()) {
            for (int i = 0; i < solServicioDtoList.size(); i++) {
                cadenaClase.add(solServicioDtoList.get(i).getOrigen() + "," + solServicioDtoList.get(i).getNumero() + "," + solServicioDtoList.get(i).getDistrito().toString() + "," + solServicioDtoList.get(i).getReferencia() + "," + solServicioDtoList.get(i).getTipoTransporte() + "," + solServicioDtoList.get(i).getUsuarioExternoDto().getNombresCompletos() + "," + UtilCore.Fecha.formatearFecha(solServicioDtoList.get(i).getFecha(), applicationMBean.getFormatoFechaHora()) + "|");
            }
        }
        return cadenaClase;
    }

    @RequestMapping(value = "/obtenerServiciosEnCola", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List obtenerServiciosEnCola() throws DAOException, IOException {
        List cadenaClase = new ArrayList();
        List<SolicitudServicioDto> solServicioDtoList = new ArrayList<SolicitudServicioDto>();
        solServicioDtoList = monitoreoServicioManager.obtenerServiciosEnCola();
        if (!solServicioDtoList.isEmpty()) {
            for (int i = 0; i < solServicioDtoList.size(); i++) {
                cadenaClase.add(solServicioDtoList.get(i).getId().toString() + "," + solServicioDtoList.get(i).getOrigen() + "," + solServicioDtoList.get(i).getNumero() + "," + solServicioDtoList.get(i).getDistrito().toString() + "," + solServicioDtoList.get(i).getReferencia() + "," + solServicioDtoList.get(i).getTipoTransporte() + "," + solServicioDtoList.get(i).getUsuarioExternoDto().getNombresCompletos() + "," + UtilCore.Fecha.formatearFecha(solServicioDtoList.get(i).getFecha(), applicationMBean.getFormatoFechaHora()) + "|");
            }
        }
        return cadenaClase;
    }

    @RequestMapping(value = "/establecerServicio/{idChofer},{strMensaje},{idSolicitud},{strLatitudChofer},{strLongitudeChofer}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String establecerServicio(@PathVariable("idChofer") Long idChofer, @PathVariable("strMensaje") String strMensaje, @PathVariable("idSolicitud") String idSolicitud, @PathVariable("strLatitudChofer") String strLatitudChofer, @PathVariable("strLongitudeChofer") String strLongitudeChofer) throws DAOException, IOException {
        String mensajeChofer = "", mensajeUsuario = "";

        try {
            solServicioDto = new SolicitudServicioDto();
            solServicioDto = monitoreoServicioManager.obtenerPorId(Long.valueOf(idSolicitud));
            if (solServicioDto.getEstado().equals(0L)) {
                solServicioDto.setChoferExternoDto(usuarioExternoManager.obtenerPorId(idChofer));
                solServicioDto.setEstado(1L);
                //Estados de servicio 0 enviado , 1 tomado  , 2 pasajero a bordo , 3 cancelado , 4 cerrado
                solServicioDto.setLatitudChofer(String.valueOf(strLatitudChofer));
                solServicioDto.setLongitudeChofer(String.valueOf(strLongitudeChofer));
                solServicioDto.setFechaConfirmacion(UtilCore.Fecha.obtenerFechaActualDate());
                monitoreoServicioManager.editar(solServicioDto);
                List<String> registration_ID = new ArrayList<String>();
                registration_ID.add(solServicioDto.getUsuarioExternoDto().getRegistrationId());
                mensajeChofer = "1" + "," + solServicioDto.getId() + "," + solServicioDto.getUsuarioExternoDto().getId() + "," + "1";
                mensajeUsuario = "1" + "," + solServicioDto.getId() + "," + solServicioDto.getChoferExternoDto().getId() + "," + "1";
                envioNotificacionJson.enviarMensajePrueba(mensajeUsuario, registration_ID);
            } else {
                mensajeChofer = "SERVICIO NO DISPONIBLE";
            }
        } catch (Exception ex) {
            mensajeChofer = "Error " + ex.getMessage();
        }
        return mensajeChofer;
    }

    @RequestMapping(value = "/alterarServicio/{idUsuario},{idServicio},{strAccion}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String alterarServicio(@PathVariable("idUsuario") String idUsuario, @PathVariable("idServicio") String idServicio, @PathVariable("strAccion") String strAccion) throws DAOException, IOException {
        String mensaje = "";
        Boolean estadoEdit = true;
        SolicitudServicioDto sServicioDto = new SolicitudServicioDto();
        sServicioDto = monitoreoServicioManager.obtenerPorId(Long.valueOf(idServicio));
        if (sServicioDto.getId() != null) {
            if (strAccion.equals("3") && sServicioDto.getChoferExternoDto().getId().equals(Long.valueOf(idUsuario))) {
                sServicioDto.setEstado(3L);
                sServicioDto.setComentario("SERVICIO CANCELADO POR EL CHOFER " + sServicioDto.getChoferExternoDto().getId().toString());
                if (sServicioDto.getUsuarioExternoDto() != null) {
                    List i = new ArrayList();
                    i.add(sServicioDto.getUsuarioExternoDto().getRegistrationId());
                    mensaje = "3" + "," + sServicioDto.getId() + "," + sServicioDto.getUsuarioExternoDto().getId() + "," + "1";
                    envioNotificacionJson.enviarMensajePrueba(mensaje, i);
                }
            } else if (strAccion.equals("3") && sServicioDto.getUsuarioExternoDto().getId().equals(Long.valueOf(idUsuario))) {
                sServicioDto.setEstado(3L);
                sServicioDto.setComentario("SERVICIO CANCELADO POR EL USUARIO" + sServicioDto.getUsuarioExternoDto().getId().toString());
                if (sServicioDto.getChoferExternoDto() != null) {
                    List i = new ArrayList();
                    i.add(sServicioDto.getChoferExternoDto().getRegistrationId());
                    mensaje = "3" + "," + sServicioDto.getId() + "," + sServicioDto.getUsuarioExternoDto().getId() + "," + "2";
                    envioNotificacionJson.enviarMensajePrueba(mensaje, i);
                }
                //Estados de servicio 0 enviado , 1 tomado  , 2 pasajero a bordo , 3 cancelado , 4 cerrado
            } else if (strAccion.equals("2") && sServicioDto.getChoferExternoDto().getId().equals(Long.valueOf(idUsuario))) {
                if (sServicioDto.getUsuarioExternoDto() != null) {
                    List i = new ArrayList();
                    i.add(sServicioDto.getUsuarioExternoDto().getRegistrationId());
                    mensaje = "2" + "," + sServicioDto.getId() + "," + sServicioDto.getChoferExternoDto().getId() + "1";
                    envioNotificacionJson.enviarMensajePrueba(mensaje, i);
                }
            } else if (strAccion.equals("4")) {
                sServicioDto.setEstado(4L);
                mensaje = "4" + "," + sServicioDto.getId() + "," + sServicioDto.getChoferExternoDto().getId() + "," + "1";
                List i = new ArrayList();
                i.add(sServicioDto.getUsuarioExternoDto().getRegistrationId());
                if (sServicioDto.getChoferExternoDto() != null) {
                    Long idChofer = sServicioDto.getChoferExternoDto().getId();
                    if (localizacionDataManager.obtenerxUsuario(idChofer) != null) {
                        String id = localizacionDataManager.obtenerxUsuario(idChofer);
                        if (!id.equals("")) {
                            LocalizacionDataDto localizacionDataDto = new LocalizacionDataDto();
                            localizacionDataDto = localizacionDataManager.obtenerPorId(Long.valueOf(id));
                            localizacionDataDto.setEstado(2);
                            localizacionDataManager.editar(localizacionDataDto);
                        }
                    }
                }
                envioNotificacionJson.enviarMensajePrueba(mensaje, i);
            } else if (strAccion.equals("5") && sServicioDto.getUsuarioExternoDto().getId().equals(Long.valueOf(idUsuario))) {
                estadoEdit = false;
                mensaje = "5" + "," + sServicioDto.getId() + "," + sServicioDto.getUsuarioExternoDto().getId() + "," + "2";
                List i = new ArrayList();
                i.add(sServicioDto.getChoferExternoDto().getRegistrationId());
                envioNotificacionJson.enviarMensajePrueba(mensaje, i);
            } else if (strAccion.equals("5") && sServicioDto.getChoferExternoDto().getId().equals(Long.valueOf(idUsuario))) {
                estadoEdit = false;
                mensaje = "5" + "," + sServicioDto.getId() + "," + sServicioDto.getChoferExternoDto().getId() + "," + "1";
                List i = new ArrayList();
                i.add(sServicioDto.getUsuarioExternoDto().getRegistrationId());
                envioNotificacionJson.enviarMensajePrueba(mensaje, i);
            } else if (strAccion.equals("6") && sServicioDto.getChoferExternoDto().getId().equals(Long.valueOf(idUsuario))) {
                sServicioDto.setEstado(6L);
                mensaje = "6" + "," + sServicioDto.getId() + "," + sServicioDto.getChoferExternoDto().getId() + "," + "1";
                List i = new ArrayList();
                i.add(sServicioDto.getUsuarioExternoDto().getRegistrationId());
                envioNotificacionJson.enviarMensajePrueba(mensaje, i);
            } else if (strAccion.equals("6") && sServicioDto.getUsuarioExternoDto().getId().equals(Long.valueOf(idUsuario))) {
                sServicioDto.setEstado(6L);
                mensaje = "6" + "," + sServicioDto.getId() + "," + sServicioDto.getUsuarioExternoDto().getId() + "," + "2";
                List i = new ArrayList();
                i.add(sServicioDto.getChoferExternoDto().getRegistrationId());
                envioNotificacionJson.enviarMensajePrueba(mensaje, i);
            }else if (strAccion.equals("8") && sServicioDto.getChoferExternoDto().getId().equals(Long.valueOf(idUsuario))) {
                //Si es chofer y está enviado la alerta de llegada 
                sServicioDto.setEstado(8L);
                mensaje = "8" + "," + sServicioDto.getId() + "," + sServicioDto.getChoferExternoDto().getId() + "," + "1";
                List i = new ArrayList();
                i.add(sServicioDto.getUsuarioExternoDto().getRegistrationId());
                envioNotificacionJson.enviarMensajePrueba(mensaje, i);
            }
            if (estadoEdit) {
                monitoreoServicioManager.editar(sServicioDto);

            }

        }
        return mensaje;
    }

    public static String getAPI_KEY() {
        return API_KEY;
    }

    public static void setAPI_KEY(String API_KEY) {
        SolicitudServicioJson.API_KEY = API_KEY;
    }

    public UsuarioExternoManager getUsuarioExternoManager() {
        return usuarioExternoManager;
    }

    public void setUsuarioExternoManager(UsuarioExternoManager usuarioExternoManager) {
        this.usuarioExternoManager = usuarioExternoManager;
    }

    public LocalizacionDataManager getLocalizacionDataManager() {
        return localizacionDataManager;
    }

    public void setLocalizacionDataManager(LocalizacionDataManager localizacionDataManager) {
        this.localizacionDataManager = localizacionDataManager;
    }

    public MonitoreoServicioManager getMonitoreoServicioManager() {
        return monitoreoServicioManager;
    }

    public void setMonitoreoServicioManager(MonitoreoServicioManager monitoreoServicioManager) {
        this.monitoreoServicioManager = monitoreoServicioManager;
    }

    public SolicitudServicioDto getSolServicioDto() {
        return solServicioDto;
    }

    public void setSolServicioDto(SolicitudServicioDto solServicioDto) {
        this.solServicioDto = solServicioDto;
    }

    public ApplicationMBean getApplicationMBean() {
        return applicationMBean;
    }

    public void setApplicationMBean(ApplicationMBean applicationMBean) {
        this.applicationMBean = applicationMBean;
    }

    public EnvioNotificacionJson getEnvioNotificacionJson() {
        return envioNotificacionJson;
    }

    public void setEnvioNotificacionJson(EnvioNotificacionJson envioNotificacionJson) {
        this.envioNotificacionJson = envioNotificacionJson;
    }
}
