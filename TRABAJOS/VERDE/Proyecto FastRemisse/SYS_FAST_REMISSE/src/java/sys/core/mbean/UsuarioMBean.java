    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.core.mbean;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.event.ActionEvent;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import sys.core.common.UtilCore;
import sys.core.configuracion.ApplicationHelper;
import sys.core.dto.RolDto;
import sys.core.dto.UsuarioDto;
import sys.core.exception.DAOException;
import sys.core.manager.RecursosManager;
import sys.core.manager.UsuarioManager;
import sys.core.util.ConstantesCore;
import sys.core.view.mbean.ApplicationMBean;
import sys.core.view.mbean.GenericMBean;
import static sys.core.view.mbean.GenericMBean.showError;
import static sys.core.view.mbean.GenericMBean.showMessage;
import static sys.core.view.mbean.GenericMBean.showWarning;
import sys.core.view.mbean.SessionMBean;

/**
 *
 * @author Indra
 */
@Controller("usuarioMBean")
@Scope("session")
public class UsuarioMBean extends GenericMBean implements Serializable {

    private static Logger logger = Logger.getLogger(UsuarioMBean.class);
    @Resource
    private UsuarioManager manager;
    @Resource
    private RecursosManager recursosManager;
    private List<UsuarioDto> lista;
    private UsuarioDto dto;
    private UsuarioDto dtoFiltro;
    private String keyTitulo = "usuario.panel";
    @Autowired
    private ApplicationMBean applicationMBean;
    @Autowired
    private SessionMBean sessionMBean;
    private String contrasenaAnterior;
    private String contrasenaNueva;
    private String contrasenaVerificar;

    public UsuarioMBean() {
        this.dtoFiltro = new UsuarioDto();
    }

    public String iniciar() {
        this.lista = null;
        this.dto = null;
        this.dtoFiltro = new UsuarioDto();
        this.dtoFiltro.setRolDto(new RolDto());
        this.dtoFiltro.getRolDto().setDescripcion("");
        return ConstantesCore.UrlNavegacion.URL_LISTA_USUARIOS;
    }

    public String nuevo() {
        sessionMBean.setAccion(ConstantesCore.Formulario.NUEVO);
        sessionMBean.setTitulo(recursosManager.construirTitulo(keyTitulo, sessionMBean.getAccion()));
        this.lista = new ArrayList<UsuarioDto>();
        this.dto = new UsuarioDto();
        this.dto.setEstadoDto(recursosManager.obtenerParametroPorID(ConstantesCore.Parametro.PARAMETRO_USUARIO_ACTIVO));
        return ConstantesCore.UrlNavegacion.URL_USUARIO;
    }

    public String editar() {
        sessionMBean.setAccion(ConstantesCore.Formulario.EDITAR);
        sessionMBean.setTitulo(recursosManager.construirTitulo(keyTitulo, sessionMBean.getAccion()));
        return ConstantesCore.UrlNavegacion.URL_USUARIO;
    }

    public String cambiarContrasena() {
        this.dto = sessionMBean.getSessionUsuarioDto();
        sessionMBean.setAccion(ConstantesCore.Formulario.EDITAR);
        sessionMBean.setTitulo(recursosManager.construirTitulo(keyTitulo, sessionMBean.getAccion()));
        return ConstantesCore.UrlNavegacion.URL_USUARIO_CAMBIAR_CONTRASENA;
    }

    public String formatearContrasena() {
        sessionMBean.setAccion(ConstantesCore.Formulario.EDITAR);
        sessionMBean.setTitulo(recursosManager.construirTitulo(keyTitulo, sessionMBean.getAccion()));
        return ConstantesCore.UrlNavegacion.URL_USUARIO_FORMATEAR_CONTRASENA;
    }

    public String ver() {
        sessionMBean.setAccion(ConstantesCore.Formulario.VER);
        sessionMBean.setTitulo(recursosManager.construirTitulo(keyTitulo, sessionMBean.getAccion()));
        return ConstantesCore.UrlNavegacion.URL_USUARIO;
    }

    public void buscar(ActionEvent ae) {
       
        try{
            this.lista = this.manager.obtenerUsuariosMantenimiento(this.dtoFiltro);
        } catch (DAOException ex) {
            logger.error("ERROR DE SISTEMA", ex);
            showError(ex.getMessage());
        }

    }

    public String guardar() {

        String to = null;
        String mensajeTrx = "";

        {
            this.dto.setUsuarioDto(sessionMBean.getSessionUsuarioDto());
            this.dto.setFecha(UtilCore.Fecha.obtenerFechaActualDate());
            this.dto.setTerminal(sessionMBean.getSessionTerminal());
        }

        if (sessionMBean.getAccion() == ConstantesCore.Formulario.NUEVO) {
            try {
                this.dto.setUsuarioCreacionDto(sessionMBean.getSessionUsuarioDto());
                this.dto.setFechaCreacion(UtilCore.Fecha.obtenerFechaActualDate());
                this.dto.setTerminalCreacion(sessionMBean.getSessionTerminal());
                
                this.dto.setContraseniaActual(UtilCore.Seguridad.encriptarTexto(this.dto.getContraseniaActual().toUpperCase()));
                UtilCore.General.toUpperCaseDto(this.dto);
                this.manager.nuevo(this.dto);
                mensajeTrx = UtilCore.Internacionalizacion.getMensajeInternacional("form.general.mensaje.transaccion.ok");

                showMessage(mensajeTrx);
                to = retroceder();
            } catch (Exception ex) {
                showError(ex.getMessage());
                to = null;
            }
        } else if (sessionMBean.getAccion() == ConstantesCore.Formulario.EDITAR) {
            try {
                UtilCore.General.toUpperCaseDto(this.dto);
                this.manager.editar(this.dto);
                mensajeTrx = UtilCore.Internacionalizacion.getMensajeInternacional("form.general.mensaje.transaccion.ok");
                showMessage(mensajeTrx);

                to = retroceder();
            } catch (Exception ex) {
                showError(ex.getMessage());
                ex.getStackTrace();
                to = null;
            }
        }
        return to;
    }

    public String guardarContrasenaFormateada() {

        String to = null;
        String mensajeTrx = "";

        {
            this.dto.setUsuarioDto(sessionMBean.getSessionUsuarioDto());
            this.dto.setFecha(UtilCore.Fecha.obtenerFechaActualDate());
            this.dto.setTerminal(sessionMBean.getSessionTerminal());
        }

        if (this.dto.getContraseniaActual().toUpperCase().equals(this.dto.getContrasenia().toUpperCase())) {
            try {
                this.dto.setContraseniaActual(UtilCore.Seguridad.encriptarTexto(this.dto.getContraseniaActual().toUpperCase()));
                UtilCore.General.toUpperCaseDto(this.dto);
                this.manager.editar(this.dto);
                mensajeTrx = UtilCore.Internacionalizacion.getMensajeInternacional("form.general.mensaje.transaccion.ok");
                showMessage(mensajeTrx);
                to = retroceder();
            } catch (Exception ex) {
                showError(ex.getMessage());
                to = null;
            }
        } else {
            mensajeTrx = UtilCore.Internacionalizacion.getMensajeInternacional("usuario.msj.verificar.contrasena");
            showWarning(mensajeTrx);
            to = null;
        }

        return to;
    }

    public String guardarContrasena() {

        String to = null;
        String mensajeTrx = "";

        {
            this.dto.setUsuarioDto(sessionMBean.getSessionUsuarioDto());
            this.dto.setFecha(UtilCore.Fecha.obtenerFechaActualDate());
            this.dto.setTerminal(sessionMBean.getSessionTerminal());
        }
        String cActual = sessionMBean.getSessionUsuarioDto().getContraseniaActual();
        String cActualIngresada = UtilCore.Seguridad.encriptarTexto(this.getContrasenaAnterior().toUpperCase()).toUpperCase();


        if (cActual.equals(cActualIngresada)) {
            if (this.contrasenaNueva.toUpperCase().equals(this.contrasenaVerificar.toUpperCase())) {
                try {
                    this.dto.setContraseniaActual(UtilCore.Seguridad.encriptarTexto(this.contrasenaNueva.toUpperCase()));
                    UtilCore.General.toUpperCaseDto(this.dto);
                    this.manager.editar(this.dto);
                    mensajeTrx = UtilCore.Internacionalizacion.getMensajeInternacional("form.general.mensaje.transaccion.ok");
                    showMessage(mensajeTrx);
                    to = ConstantesCore.UrlNavegacion.URL_INICIO;
                } catch (Exception ex) {
                    showError(ex.getMessage());
                    to = null;
                }
            } else {
                mensajeTrx = UtilCore.Internacionalizacion.getMensajeInternacional("usuario.msj.verificar.contrasena");
                showWarning(mensajeTrx);
                to = null;
            }
        } else {
            mensajeTrx = UtilCore.Internacionalizacion.getMensajeInternacional("usuario.label.contrasena.anterior.error");
            showWarning(mensajeTrx);
            to = null;
        }



        return to;
    }

    public String getContrasenaVerificar() {
        return contrasenaVerificar;
    }

    public void setContrasenaVerificar(String contrasenaVerificar) {
        this.contrasenaVerificar = contrasenaVerificar;
    }

    public String getContrasenaNueva() {
        return contrasenaNueva;
    }

    public void setContrasenaNueva(String contrasenaNueva) {
        this.contrasenaNueva = contrasenaNueva;
    }

    public String getContrasenaAnterior() {
        return contrasenaAnterior;
    }

    public void setContrasenaAnterior(String contrasenaAnterior) {
        this.contrasenaAnterior = contrasenaAnterior;
    }

    public String retroceder() {
        this.dtoFiltro = new UsuarioDto();
        this.dtoFiltro.setRolDto(new RolDto());
        this.dtoFiltro.getRolDto().setDescripcion("");
        this.dtoFiltro.setId(this.dto.getId());
        buscar(null);
        sessionMBean.setAccion(ConstantesCore.Formulario.LISTA);
        return ConstantesCore.UrlNavegacion.URL_LISTA_USUARIOS;
    }

    public UsuarioDto getDto() {
        return dto;
    }

    public void setDto(UsuarioDto dto) {
        this.dto = dto;
    }

    public String getKeyTitulo() {
        return keyTitulo;
    }

    public void setKeyTitulo(String keyTitulo) {
        this.keyTitulo = keyTitulo;
    }

    public List<UsuarioDto> getLista() {
        return lista;
    }

    public void setLista(List<UsuarioDto> lista) {
        this.lista = lista;
    }

    public UsuarioManager getManager() {
        return manager;
    }

    public void setManager(UsuarioManager manager) {
        this.manager = manager;
    }

    public UsuarioDto getDtoFiltro() {
        return dtoFiltro;
    }

    public void setDtoFiltro(UsuarioDto dtoFiltro) {
        this.dtoFiltro = dtoFiltro;
    }
}
