/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.core.json;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sys.core.common.UtilCore;
import sys.movil.dto.LocalizacionDataDto;
import sys.movil.manager.LocalizacionDataManager;
import sys.core.exception.DAOException;
import sys.movil.manager.UsuarioExternoManager;

/**
 *
 * @author admin
 */
@Controller
@RequestMapping(value = "localizacionDataJson")
public class LocalizacionDataJson {

    @Resource
    private LocalizacionDataManager localizacionDataManager;
    @Resource
    private UsuarioExternoManager usuarioExternoManager;
    private LocalizacionDataDto localizacionDataDto;

    @RequestMapping(value = "/nuevo/{usuario},{terminal},{latitude},{longitude},{altitude},{estado}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Boolean nuevo(@PathVariable("usuario") Long usuario, @PathVariable("terminal") String terminal, @PathVariable("latitude") String latitude, @PathVariable("longitude") String longitude, @PathVariable("altitude") String altitude, @PathVariable("estado") int estado) throws DAOException {
        boolean methodState = Boolean.TRUE;
        try {
            if (usuarioExternoManager.obtenerPorId(usuario) != null) {
                localizacionDataDto = new LocalizacionDataDto();
                localizacionDataDto.setUsuarioExternoDto(usuarioExternoManager.obtenerPorId(usuario));
                localizacionDataDto.setTerminal(terminal);
                localizacionDataDto.setLatitude(latitude);
                localizacionDataDto.setLongitude(longitude);
                localizacionDataDto.setAltitude(altitude);
                localizacionDataDto.setFecha(UtilCore.Fecha.obtenerFechaActualDate());
                localizacionDataDto.setEstado(estado);
                localizacionDataManager.nuevo(localizacionDataDto);
            }
            // System.out.println("Ingreso de Data Satisfactoria");
        } catch (Exception ex) {
            methodState = false;
            //    System.out.println("Error  : " + ex);
        }
        return methodState;
    }

    @RequestMapping(value = "/obtenerLocalizacionesActivas", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<LocalizacionDataDto> obtenerTodosActivos() throws DAOException {
        return localizacionDataManager.obtenerTodos();
    }

    @RequestMapping(value = "/obtenerChoferesActivos", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List obtenerChoferesActivos() throws DAOException {
        List lista = new ArrayList();
        if (!localizacionDataManager.obtenerChoferesActivos().isEmpty()) {
            for (int i = 0; i < localizacionDataManager.obtenerChoferesActivos().size(); i++) {
                lista.add(localizacionDataManager.obtenerChoferesActivos().get(i).getUsuarioExternoDto().getId() + "," + localizacionDataManager.obtenerChoferesActivos().get(i).getLatitude() + "," + localizacionDataManager.obtenerChoferesActivos().get(i).getLongitude() + "," + localizacionDataManager.obtenerChoferesActivos().get(i).getAltitude() + "," + localizacionDataManager.obtenerChoferesActivos().get(i).getEstado() + "," + localizacionDataManager.obtenerChoferesActivos().get(i).getTerminal() + "," + localizacionDataManager.obtenerChoferesActivos().get(i).getUsuarioExternoDto().getRegistrationId() + "|");
            }
        }
        return lista;
    }

    @RequestMapping(value = "/cambiarEstadoSessionUsuario/{idUsuario},{estado}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    void desactivarSessionUsuario(@PathVariable("idUsuario") Long idUsuario , @PathVariable("estado") int estado) throws DAOException {
        if (localizacionDataManager.obtenerxUsuario(idUsuario) != null) {
            String id = localizacionDataManager.obtenerxUsuario(idUsuario);
            if (!id.equals("")) {
                LocalizacionDataDto localizacionDataDto = new LocalizacionDataDto();
                localizacionDataDto = localizacionDataManager.obtenerPorId(Long.valueOf(id));
                localizacionDataDto.setEstado(estado);
                localizacionDataManager.editar(localizacionDataDto);
            }
        }
    }

    @RequestMapping(value = "/obtenerPosicionChoferCercano", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List obtenerPosicionChoferCercano(@PathVariable("strLatitudActual") String strLatitudActual, @PathVariable("strLongitudActual") String strLongitudActual) throws DAOException {
        List lista = new ArrayList();
        if (!localizacionDataManager.obtenerChoferesActivos().isEmpty()) {
            for (int i = 0; i < localizacionDataManager.obtenerPosicionChoferCercano().size(); i++) {
                lista.add(localizacionDataManager.obtenerChoferesActivos().get(i).getUsuarioExternoDto().getId() + "," + localizacionDataManager.obtenerChoferesActivos().get(i).getLatitude() + "," + localizacionDataManager.obtenerChoferesActivos().get(i).getLongitude() + "," + localizacionDataManager.obtenerChoferesActivos().get(i).getAltitude() + "," + localizacionDataManager.obtenerChoferesActivos().get(i).getEstado() + "," + localizacionDataManager.obtenerChoferesActivos().get(i).getTerminal() + "," + localizacionDataManager.obtenerChoferesActivos().get(i).getUsuarioExternoDto().getRegistrationId() + "|");
            }
        }
        return lista;
    }

    public LocalizacionDataManager getLocalizacionDataManager() {
        return localizacionDataManager;
    }

    public void setLocalizacionDataManager(LocalizacionDataManager localizacionDataManager) {
        this.localizacionDataManager = localizacionDataManager;
    }

    public UsuarioExternoManager getUsuarioExternoManager() {
        return usuarioExternoManager;
    }

    public void setUsuarioExternoManager(UsuarioExternoManager usuarioExternoManager) {
        this.usuarioExternoManager = usuarioExternoManager;
    }

    public LocalizacionDataDto getLocalizacionDataDto() {
        return localizacionDataDto;
    }

    public void setLocalizacionDataDto(LocalizacionDataDto localizacionDataDto) {
        this.localizacionDataDto = localizacionDataDto;
    }
}
