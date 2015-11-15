/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.core.view.mbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.primefaces.component.menubar.Menubar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import sys.core.common.UtilCore;
import sys.core.configuracion.WebServletContextListener;
import sys.core.dto.OpcionSistemaDto;
import sys.core.dto.PermisoDto;
import sys.core.manager.PermisoManager;
import sys.core.util.MenuBarHelper;

/**
 *
 * @author admin
 */
@Controller("menuMBean")
@Scope("session")
public class MenuMBean extends GenericMBean implements Serializable {

    private static Logger logger = Logger.getLogger(MenuMBean.class);
    private Menubar principalMenuBar;

    @Resource
    private PermisoManager permisoRolManager;

    @Autowired
    private SessionMBean sessionMBean;

    public MenuMBean() {

    }

    public void cargarMenuBar() {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("rolDto.id", sessionMBean.getSessionUsuarioDto().getRolDto().getId());
            map.put("opcionSistemaDto.estado", Boolean.TRUE);
            map.put("opcionSistemaDto.tipoMenu", "M");
            Map<String, String> order = new HashMap<String, String>();
            order.put("opcionSistemaDto.orden", "asc");
            order.put("opcionSistemaDto.descripcion", "asc");
            List<PermisoDto> listaPermisos = permisoRolManager.obtenerConFiltroConOrden(map, order);

            List<OpcionSistemaDto> listaOpciones = new ArrayList<OpcionSistemaDto>();
            for (PermisoDto p : listaPermisos) {
                OpcionSistemaDto o = p.getOpcionSistemaDto();
                o.setPermiso(p.getEstado());
                if (o.getPermiso() && o.getTipoMenu().equals("M")) {
                    listaOpciones.add(o);
                }

            }

            MenuBarHelper menuBarHelper = (MenuBarHelper) WebServletContextListener.getApplicationContext().getBean("menuBarHelper");
            this.principalMenuBar = menuBarHelper.getMenuBar(listaOpciones, listaPermisos);

        } catch (Exception e) {
            String msg = UtilCore.Internacionalizacion.getMensajeInternacional("general.mensaje.error.cargar.menu");
            showError(msg, e.getMessage());
            logger.error(msg, e);
        }
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        MenuMBean.logger = logger;
    }

    public Menubar getPrincipalMenuBar() {
        // cargarMenuBar();
        return principalMenuBar;
    }

    public void setPrincipalMenuBar(Menubar principalMenuBar) {
        this.principalMenuBar = principalMenuBar;
    }

    public SessionMBean getSessionMBean() {
        return sessionMBean;
    }

    public void setSessionMBean(SessionMBean sessionMBean) {
        this.sessionMBean = sessionMBean;
    }

}
