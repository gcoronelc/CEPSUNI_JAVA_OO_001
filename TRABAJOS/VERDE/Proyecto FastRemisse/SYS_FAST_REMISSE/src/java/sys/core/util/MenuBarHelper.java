/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.core.util;

import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.component.menubar.Menubar;
import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.springframework.stereotype.Controller;
import sys.core.common.UtilCore;
import sys.core.configuracion.WebServletContextListener;
import sys.core.dto.OpcionSistemaDto;
import sys.core.dto.PermisoDto;
import sys.core.manager.OpcionSistemaManager;
import sys.core.view.mbean.ApplicationMBean;
import sys.core.view.mbean.GenericMBean;

/**
 *
 * @author admin
 */
@Controller("menuBarHelper")
public class MenuBarHelper implements Serializable {

    private static Logger logger = Logger.getLogger(MenuBarHelper.class);

    @Resource
    private OpcionSistemaManager opcionSistemaManager;

    public MenuBarHelper() {
    }

    private static PermisoDto obtenerPermiso(List<PermisoDto> listaPermisos, OpcionSistemaDto o) {
        PermisoDto p = null;
        for (PermisoDto s : listaPermisos) {
            if (s.getOpcionSistemaDto().equals(o)) {
                p = s;
                break;
            }
        }
        return p;
    }

    public Menubar getMenuBar(List<OpcionSistemaDto> listaOpcionMenu, List<PermisoDto> listaPermisos) throws Exception {
        listaOpcionMenu.remove(new OpcionSistemaDto(0L));
        Menubar menuBar = new Menubar();
        Submenu subMenu = null;
        MenuItem menuItem = null;

        ApplicationMBean applicationMBean = (ApplicationMBean) WebServletContextListener.getApplicationContext().getBean("applicationMBean");

        for (OpcionSistemaDto opcionMenuDto : listaOpcionMenu) {
            opcionMenuDto.setPermiso(obtenerPermiso(listaPermisos, opcionMenuDto).getEstado());
            if (opcionMenuDto.getPadreDto() == null || opcionMenuDto.getPadreDto().getId().equals(0L)) {
                List<OpcionSistemaDto> hijos = opcionSistemaManager.obtenerHijosMenu(opcionMenuDto);
                if (hijos.size() > 0) {
                    if (opcionMenuDto.getPermiso()) {
                        subMenu = new Submenu();
                        subMenu.setId(ConstantesCore.VARIABLE_ID_MENU + String.valueOf(opcionMenuDto.getId().intValue()));
                        subMenu.setLabel(UtilCore.Internacionalizacion.getMensajeInternacional(opcionMenuDto.getValue()).toUpperCase());
                        subMenu.setIcon(opcionMenuDto.getIcon());
                        subMenu.setStyle(applicationMBean.getEstiloMenu());
                        cargarHijos3(opcionMenuDto, subMenu, listaPermisos);
                        menuBar.getChildren().add(subMenu);
                    }

                } else {
                    if (opcionMenuDto.getPermiso()) {
                        menuItem = new MenuItem();
                        menuItem.setId(ConstantesCore.VARIABLE_ID_MENU_ITEM + String.valueOf(opcionMenuDto.getId().intValue()));
                        menuItem.setValue(UtilCore.Internacionalizacion.getMensajeInternacional(opcionMenuDto.getValue()).toUpperCase());

                        menuItem.setIcon(opcionMenuDto.getIcon());
                        menuItem.setOnclick(opcionMenuDto.getOnClick());
                        if (opcionMenuDto.getAction() != null && opcionMenuDto.getAction().length() > 0) {
                            menuItem.setAction(FacesContext.getCurrentInstance().getApplication().createMethodBinding(opcionMenuDto.getAction(), new Class[]{}));
                        } else {
                            menuItem.setUrl(opcionMenuDto.getUrl());
                        }

                        menuItem.setInView(true);
                        menuItem.setStyle(applicationMBean.getEstiloMenu());
                        menuItem.setAjax(false);
                        menuBar.getChildren().add(menuItem);
                    }

                }
            }
        }
        return menuBar;
    }

    private void cargarHijos3(OpcionSistemaDto opcionMenuDto2, Submenu subMenu2, List<PermisoDto> listaPermisos) throws Exception {
        try {
            MenuItem menuItem = null;
            Submenu subMenuHijo = null;
            ApplicationMBean applicationMBean = (ApplicationMBean) WebServletContextListener.getApplicationContext().getBean("applicationMBean");
            List<OpcionSistemaDto> hijos1 = opcionSistemaManager.obtenerHijosMenu(opcionMenuDto2);
            for (OpcionSistemaDto opcionMenuDtoHijo : hijos1) {
                opcionMenuDtoHijo.setPermiso(obtenerPermiso(listaPermisos, opcionMenuDtoHijo).getEstado());
                List<OpcionSistemaDto> hijos = opcionSistemaManager.obtenerHijosMenu(opcionMenuDtoHijo);
                if (hijos.size() >= 1) {
                    if (opcionMenuDtoHijo.getPermiso()) {
                        subMenuHijo = new Submenu();
                        subMenuHijo.setId(ConstantesCore.VARIABLE_ID_MENU + String.valueOf(opcionMenuDtoHijo.getId().intValue()));
                        subMenuHijo.setLabel(UtilCore.Internacionalizacion.getMensajeInternacional(opcionMenuDtoHijo.getValue()).toUpperCase());
                        subMenuHijo.setIcon(opcionMenuDtoHijo.getIcon());
                        subMenuHijo.setStyle(applicationMBean.getEstiloMenu());
                        cargarHijos3(opcionMenuDtoHijo, subMenuHijo, listaPermisos);
                        subMenu2.getChildren().add(subMenuHijo);
                    }
                } else {
                    if (opcionMenuDtoHijo.getPermiso()) {
                        menuItem = new MenuItem();
                        menuItem.setId(ConstantesCore.VARIABLE_ID_MENU_ITEM + String.valueOf(opcionMenuDtoHijo.getId().intValue()));
                        menuItem.setValue(UtilCore.Internacionalizacion.getMensajeInternacional(opcionMenuDtoHijo.getValue()).toUpperCase());
                        menuItem.setUrl(opcionMenuDtoHijo.getUrl());
                        menuItem.setIcon(opcionMenuDtoHijo.getIcon());
                        menuItem.setOnclick(opcionMenuDtoHijo.getOnClick());
                        menuItem.setStyle(applicationMBean.getEstiloMenu());
                        if (opcionMenuDtoHijo.getAction() != null && opcionMenuDtoHijo.getAction().length() > 0) {
                            menuItem.setAction(FacesContext.getCurrentInstance().getApplication().createMethodBinding(opcionMenuDtoHijo.getAction(), new Class[]{}));
                        } else {
                            menuItem.setUrl(opcionMenuDtoHijo.getUrl());
                        }

                        menuItem.setInView(true);
                        menuItem.setAjax(false);
                        subMenu2.getChildren().add(menuItem);
                    }
                }

            }
        } catch (Exception ex) {
            GenericMBean.showError(ex.getMessage());
        }
    }
}
