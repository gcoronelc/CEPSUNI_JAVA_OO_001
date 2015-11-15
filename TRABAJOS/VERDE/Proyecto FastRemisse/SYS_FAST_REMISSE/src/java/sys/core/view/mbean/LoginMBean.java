/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.core.view.mbean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import sys.core.common.UtilCore;
import sys.core.configuracion.ApplicationHelper;
import sys.core.configuracion.WebServletContextListener;
import sys.core.dto.UsuarioDto;
import sys.core.exception.DAOException;
import sys.core.manager.CatalogoParametroManager;
import sys.core.manager.EmpresaManager;
import sys.core.manager.ParametroManager;
import sys.core.manager.RecursosManager;
import sys.core.manager.UbigeoManager;
import sys.core.manager.UsuarioManager;
import sys.core.util.ConstantesCore;

/**
 *
 * @author Indra
 */
@Controller("loginMBean")
@Scope("request")
public class LoginMBean extends GenericMBean implements Serializable {

    private static Logger logger = Logger.getLogger(LoginMBean.class);
    private String usuario;
    private String contrasena;
    @Resource
    private UsuarioManager usuarioManager;
    @Resource
    private ParametroManager parametroManager;
    @Resource
    private CatalogoParametroManager catalogoParametroManager;
    @Resource
    private UbigeoManager ubigeoManager;
    @Autowired
    private ApplicationMBean applicationMBean;
    @Autowired
    private SessionMBean sessionMBean;
    @Resource
    private RecursosManager recursosManager;
    @Resource
    private EmpresaManager empresaManager;

    public String ingresar() {

        if (validarUsuario()) {

            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            this.sessionMBean.setSessionTerminal(request.getRemoteAddr());
            sessionMBean.setMensajeExpiraSession("");
            request.getSession().setAttribute("terminal", request.getRemoteAddr());
            System.out.println("session.getAttribute('terminal')" + request.getSession().getAttribute("terminal"));
            return "/inicio.xhtml";
        } else {
            return null;
        }

    }

    private boolean validarUsuario() {

        Map<String, Object> mapFiltro = new HashMap<String, Object>();
        mapFiltro.put("login", this.usuario);
        mapFiltro.put("contraseniaActual", UtilCore.Seguridad.encriptarTexto(this.contrasena.toUpperCase()).toUpperCase());

        List<UsuarioDto> listaUsuarios = null;
        try {
            listaUsuarios = this.usuarioManager.obtenerConFiltroConOrden(mapFiltro, null);
        } catch (DAOException ex) {
            logger.error(ex);
        }

        this.sessionMBean.setSessionUsuarioDto(null);

        UsuarioDto usuarioDto = null;
        if (listaUsuarios != null && !listaUsuarios.isEmpty()) {
            usuarioDto = listaUsuarios.get(0);

            if (usuarioDto.getEstadoDto().getId().equals(ConstantesCore.Parametro.PARAMETRO_USUARIO_ACTIVO)) {

                if (usuarioDto.getEstadoDto().getId().equals(ConstantesCore.Parametro.PARAMETRO_USUARIO_DESACTIVO)) {
                    String msj = UtilCore.Internacionalizacion.getMensajeInternacional("login.validacion.desactivo", usuarioDto.getNombresCompletos().toUpperCase());
                    showWarning(msj);
                    return false;
                } else {

                    this.sessionMBean.setSessionUsuarioDto(usuarioDto);
                    this.sessionMBean.setEmpresasUsuario(empresaManager.obtenerEmpresaPorId(ConstantesCore.Parametro.EMPRESA_FAST_REMISSE_ID));
                    this.sessionMBean.setPrivilegiosOpciones(ApplicationHelper.obtenerPrivilegiosUsuario());

                    if (this.sessionMBean.getEmpresasUsuario().size() > 0) {
                        this.sessionMBean.setSessionEmpresaDto(this.sessionMBean.getEmpresasUsuario().get(0));
                        String msj = UtilCore.Internacionalizacion.getMensajeInternacional("login.bienvenida.mensaje", usuarioDto.getNombresCompletos().toUpperCase());
                        //Cargamos el Menu del Sistema
                        MenuMBean menuMBean = (MenuMBean) WebServletContextListener.getApplicationContext().getBean("menuMBean");
                        menuMBean.cargarMenuBar();
                        showMessage(msj);
                        return true;
                    } else {
                        String msj = UtilCore.Internacionalizacion.getMensajeInternacional("login.validacion.organizaciones", usuarioDto.getNombresCompletos().toUpperCase());
                        showWarning(msj);
                        return false;
                    }

                }
            } else {
                String msj = UtilCore.Internacionalizacion.getMensajeInternacional("login.validacion.inactivo", usuarioDto.getNombresCompletos().toUpperCase());
                showWarning(msj);
                return false;
            }

        } else {
            String msj = UtilCore.Internacionalizacion.getMensajeInternacional("login.validacion.error");
            showError(msj);
            return false;
        }
    }

    public String cerrarSession() {
        this.sessionMBean.setSessionUsuarioDto(null);
        this.sessionMBean.setSessionEmpresaDto(null);
        this.sessionMBean.setSessionTerminal(null);
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        request.getSession().removeAttribute("terminal");

        request.getSession().invalidate();
        sessionMBean.setMensajeExpiraSession("");
        return "/login";
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
