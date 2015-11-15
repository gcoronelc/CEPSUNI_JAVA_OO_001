package sys.core.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import static jxl.biff.BaseCellFeatures.logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sys.core.common.UtilCore;
import sys.core.exception.DAOException;
import sys.movil.dto.UsuarioExternoDto;
import sys.movil.manager.UsuarioExternoManager;

/**
 *
 * @author admin
 */
@Controller
@RequestMapping(value = "usuarioExternoJson")
public class UsuarioExternoJson {

    @Resource
    private UsuarioExternoManager usuarioExternoManager;
    private UsuarioExternoDto usuarioExternoDto;

    @RequestMapping(value = "/login/{strLogin},{strPassword}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List validarUsuario(@PathVariable("strLogin") String strLogin, @PathVariable("strPassword") String strPassword) {
        List retorno = new ArrayList();
        Map<String, Object> mapFiltro = new HashMap<String, Object>();
        mapFiltro.put("login", strLogin.toUpperCase());
        mapFiltro.put("contraseniaActual", UtilCore.Seguridad.encriptarTexto(strPassword.toUpperCase()).toUpperCase());

        List<UsuarioExternoDto> listaUsuariosExt = null;
        try {
            listaUsuariosExt = this.usuarioExternoManager.obtenerConFiltroConOrden(mapFiltro, null);
        } catch (DAOException ex) {
            logger.error(ex);
        }
        if (listaUsuariosExt != null && !listaUsuariosExt.isEmpty()) {
            usuarioExternoDto = listaUsuariosExt.get(0);
            retorno.add(usuarioExternoDto.getId());
            retorno.add(usuarioExternoDto.getNombresCompletos());
            retorno.add(usuarioExternoDto.getLogin());
            retorno.add(usuarioExternoDto.getTipoUsuario());
            retorno.add(String.valueOf(UtilCore.Fecha.obtenerFechaActualDate()));
            retorno.add(usuarioExternoDto.getEmail());
            retorno.add(usuarioExternoDto.getRegistrationId());
            //El usuario es correcto
        } else {
            //Datos invalidos
            retorno = null;
        }
        return retorno;
    }

    @RequestMapping(value = "/updateRegistrationID/{idUsuario},{strRegistration_ID}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String updateRegistrationID(@PathVariable("idUsuario") String idUsuario, @PathVariable("strRegistration_ID") String strRegistration_ID) throws DAOException {
        String retorno;
        try {
            UsuarioExternoDto usuExternoDto = new UsuarioExternoDto();
            usuExternoDto = usuarioExternoManager.obtenerPorId(Long.valueOf(idUsuario));
            System.out.println(usuExternoDto);
            usuExternoDto.setRegistrationId(strRegistration_ID);
            usuarioExternoManager.editar(usuExternoDto);
            retorno = usuExternoDto.getRegistrationId();
        } catch (Exception ex) {
            retorno = ex.getMessage();
        }
        System.out.println("mensaje" + retorno);
        return retorno;
    }

    @RequestMapping(value = "/obtenerUsuarioById/{idUsuario}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String obtenerById(@PathVariable("idUsuario") String idUsuario) throws DAOException {
        String retorno = "";
        UsuarioExternoDto usuExternoDto = new UsuarioExternoDto();
        usuExternoDto = usuarioExternoManager.obtenerPorId(Long.valueOf(idUsuario));
        if (usuExternoDto != null) {
            if (usuExternoDto.getTipoUsuario().equals(1L)) {
                retorno = String.valueOf(usuExternoDto.getNombresCompletos()) + "," + String.valueOf(usuExternoDto.getNroTelefono()) + "," + String.valueOf(usuExternoDto.getTipoUsuario());
            } else if (usuExternoDto.getTipoUsuario().equals(2L)) {
                retorno = String.valueOf(usuExternoDto.getNombresCompletos()) + "," + String.valueOf(usuExternoDto.getNroTelefono()) + "," + String.valueOf(usuExternoDto.getTipoUsuario()) + "," + String.valueOf(usuExternoDto.getNumeroDocumento()) + "," + String.valueOf(usuExternoDto.getTipoAutoDto().getDescripcion()) + "," + String.valueOf(usuExternoDto.getMarcaAutoDto().getDescripcion()) + "," + String.valueOf(usuExternoDto.getColorAuto()) + "," + String.valueOf(usuExternoDto.getNroPlaca()) + "," + String.valueOf(usuExternoDto.getModelo()) + "," + String.valueOf(usuExternoDto.getLicenciaNro());
            }
        }
        return retorno;
    }

    @RequestMapping(value = "/registrarUsuario/{strNombresCompletos},{strLoginUsuario},{strEmail},{strTelefono},{strPasswordUsuario},{strTerminal},{strRegistration_ID}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List registrarUsuario(@PathVariable("strNombresCompletos") String strNombresCompletos, @PathVariable("strLoginUsuario") String strLoginUsuario, @PathVariable("strEmail") String strEmail, @PathVariable("strTelefono") String strTelefono, @PathVariable("strPasswordUsuario") String strPasswordUsuario, @PathVariable("strTerminal") String strTerminal, @PathVariable("strRegistration_ID") String strRegistration_ID) throws DAOException {
        List retorno = new ArrayList<Object>();
        boolean condicion = true;
        if (usuarioExternoManager.validarUsuario(strLoginUsuario).size() > 0) {
            retorno.add("EL USUARIO YA EXISTE !");
            condicion = false;
            return retorno;
        }
        if (usuarioExternoManager.validarEmail(strEmail).size() > 0) {
            retorno.add("EL EMAIL YA EXISTE !");
            condicion = false;
            return retorno;
        }
        if (condicion) {
            usuarioExternoDto = new UsuarioExternoDto();
            usuarioExternoDto.setLogin(strLoginUsuario);
            usuarioExternoDto.setEmail(strEmail);
            usuarioExternoDto.setNombresCompletos(strNombresCompletos);
            usuarioExternoDto.setNroTelefono(strTelefono);
            usuarioExternoDto.setContrasenia(UtilCore.Seguridad.encriptarTexto(strPasswordUsuario.toUpperCase()));
            usuarioExternoDto.setContraseniaActual(UtilCore.Seguridad.encriptarTexto(strPasswordUsuario.toUpperCase()));
            usuarioExternoDto.setTipoUsuario(1L);
            usuarioExternoDto.setEstado(Boolean.TRUE);
            usuarioExternoDto.setTerminalCreacion(strTerminal);
            usuarioExternoDto.setRegistrationId(strRegistration_ID);
            try {
                usuarioExternoManager.nuevo(usuarioExternoDto);
            } catch (DAOException ex) {
                System.out.println(ex.getMessage());
            }
            retorno.add(usuarioExternoDto.getId());
            retorno.add(usuarioExternoDto.getNombresCompletos());
            retorno.add(usuarioExternoDto.getLogin());
            retorno.add(usuarioExternoDto.getTipoUsuario());
            retorno.add(String.valueOf(UtilCore.Fecha.obtenerFechaActualDate()));
            retorno.add(usuarioExternoDto.getEmail());
            retorno.add(usuarioExternoDto.getRegistrationId());
        }
        return retorno;
    }

    public UsuarioExternoManager getUsuarioExternoManager() {
        return usuarioExternoManager;
    }

    public void setUsuarioExternoManager(UsuarioExternoManager usuarioExternoManager) {
        this.usuarioExternoManager = usuarioExternoManager;
    }

    public UsuarioExternoDto getUsuarioExternoDto() {
        return usuarioExternoDto;
    }

    public void setUsuarioExternoDto(UsuarioExternoDto usuarioExternoDto) {
        this.usuarioExternoDto = usuarioExternoDto;
    }
}
