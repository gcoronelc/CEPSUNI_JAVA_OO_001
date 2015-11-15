/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.core.view.mbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import sys.core.common.UtilCore;
import sys.core.configuracion.ApplicationHelper;
import sys.core.dto.CatalogoParametroDto;
import sys.core.dto.ParametroDto;
import sys.core.exception.DAOException;
import sys.core.manager.CatalogoParametroManager;
import sys.core.manager.ParametroManager;
import sys.core.manager.RecursosManager;
import sys.core.util.ConstantesCore;

/**
 *
 * @author Indra
 */
@Controller("catalogoParametroMBean")
@Scope("session")
public class CatalogoParametroMBean extends GenericMBean implements Serializable {

    private static Logger logger = Logger.getLogger(CatalogoParametroMBean.class);
    @Resource
    private CatalogoParametroManager manager;
    @Resource
    private ParametroManager mngParametro;
    @Resource
    private RecursosManager recursosManager;
    private List<CatalogoParametroDto> lista;
    private CatalogoParametroDto dto;
    private CatalogoParametroDto dtoFiltro;
    private String keyTitulo = "catalogo.parametro.panel";
    private ParametroDto parametroDto;
    private int accionParametro;
    private SelectItem[] siTipos;
    private List<ParametroDto> listaParametrosPadre;

    public CatalogoParametroMBean() {
        this.dtoFiltro = new CatalogoParametroDto();
    }

    public String iniciar() {
        this.lista = null;
        this.dto = null;
        this.dtoFiltro = new CatalogoParametroDto();
        return ConstantesCore.UrlNavegacion.URL_LISTA_CATALOGO_PARAMETRO;
    }

    public String nuevo() {
        sessionMBean().setAccion(ConstantesCore.Formulario.NUEVO);
        sessionMBean().setTitulo(recursosManager.construirTitulo(keyTitulo, sessionMBean().getAccion()));
        this.lista = new ArrayList<CatalogoParametroDto>();
        this.dto = new CatalogoParametroDto();
        this.dto.setEstado(Boolean.TRUE);
        return ConstantesCore.UrlNavegacion.URL_CATALOGO_PARAMETRO;
    }

    public String editar() {
        sessionMBean().setAccion(ConstantesCore.Formulario.EDITAR);
        sessionMBean().setTitulo(recursosManager.construirTitulo(keyTitulo, sessionMBean().getAccion()));
        return ConstantesCore.UrlNavegacion.URL_CATALOGO_PARAMETRO;
    }

    public String ver() {
        sessionMBean().setAccion(ConstantesCore.Formulario.LISTA);
        sessionMBean().setTitulo(recursosManager.construirTitulo(keyTitulo, sessionMBean().getAccion()));
        return ConstantesCore.UrlNavegacion.URL_CATALOGO_PARAMETRO;
    }

    public void buscar(ActionEvent ae) {
        try {
            this.lista = this.manager.obtenerCatalogos(dtoFiltro);
        } catch (DAOException ex) {
            logger.error(ex);
            showError(ex.getMessage());
        }

    }

    public String guardar() {

        String to = null;
        String mensajeTrx = "";
        {
            this.dto.setUsuarioDto(sessionMBean().getSessionUsuarioDto());
            this.dto.setFecha(UtilCore.Fecha.obtenerFechaActualDate());
            this.dto.setTerminal(sessionMBean().getSessionTerminal());
        }
        if (sessionMBean().getAccion() == ConstantesCore.Formulario.NUEVO) {
            try {
                UtilCore.General.toUpperCaseDto(this.dto);
                this.manager.nuevo(this.dto);
                mensajeTrx = UtilCore.Internacionalizacion.getMensajeInternacional("form.general.mensaje.transaccion.ok");
                showMessage(mensajeTrx);
                to = "";//retroceder();
            } catch (Exception ex) {
                logger.error(ex.getMessage());
                showError(UtilCore.Internacionalizacion.getMensajeInternacional("form.general.mensaje.transaccion.error"));
                to = null;
            }
        } else if (sessionMBean().getAccion() == ConstantesCore.Formulario.EDITAR) {
            try {
                UtilCore.General.toUpperCaseDto(this.dto);
                this.manager.editar(this.dto);
                mensajeTrx = UtilCore.Internacionalizacion.getMensajeInternacional("form.general.mensaje.transaccion.ok");
                showMessage(mensajeTrx);

                to = "";//retroceder();
            } catch (Exception ex) {
                logger.error(ex.getMessage());
                showError(UtilCore.Internacionalizacion.getMensajeInternacional("form.general.mensaje.transaccion.error"));
                to = null;
            }
        }
        return to;
    }

    public String retroceder() {
        buscar(null);
        sessionMBean().setAccion(ConstantesCore.Formulario.LISTA);
        sessionMBean().setTitulo(recursosManager.construirTitulo(keyTitulo, sessionMBean().getAccion()));
        return ConstantesCore.UrlNavegacion.URL_LISTA_CATALOGO_PARAMETRO;
    }

    public String nuevoParametro() {
        this.accionParametro = ConstantesCore.Formulario.NUEVO;
        this.parametroDto = new ParametroDto();
        this.parametroDto.setEstado(Boolean.TRUE);
        this.parametroDto.setTipo("SISTEMA");
        this.parametroDto.setCatalogoParametroPadreDto(recursosManager.obtenerCatalogoParametroPorID(1000L));
        this.parametroDto.setParametroPadreDto(recursosManager.obtenerParametroPorID(15L));
        return ConstantesCore.UrlNavegacion.URL_PARAMETRO;
    }

    public String editarParametro() {
        this.accionParametro = ConstantesCore.Formulario.EDITAR;
        this.getListaParametrosPadre();

        return ConstantesCore.UrlNavegacion.URL_PARAMETRO;
    }

    public String verParametro() {
        this.accionParametro = ConstantesCore.Formulario.VER;

        return ConstantesCore.UrlNavegacion.URL_PARAMETRO;
    }

    public String agregarParametro() {
        String to = null;
        String mensajeTrx = "";
        this.parametroDto.setUsuario(sessionMBean().getSessionUsuarioDto().getId());
        this.parametroDto.setFecha(UtilCore.Fecha.obtenerFechaActualDate());
        this.parametroDto.setTerminal(sessionMBean().getSessionTerminal());
        if (this.accionParametro == ConstantesCore.Formulario.NUEVO) {
            try {
                this.parametroDto.setCatalogoParametroDto(this.dto);
                //UtilCore.General.toUpperCaseDto(this.parametroDto);
                this.dto.getListaParametros().add(parametroDto);
                this.mngParametro.nuevo(this.parametroDto);
                mensajeTrx = UtilCore.Internacionalizacion.getMensajeInternacional("form.general.mensaje.transaccion.ok");
                showMessage(mensajeTrx);
                to = ConstantesCore.UrlNavegacion.URL_CATALOGO_PARAMETRO;
            } catch (Exception ex) {
                System.out.println("Error Mensaje" + ex.getMessage());
                to = null;
            }
        } else if (this.accionParametro == ConstantesCore.Formulario.EDITAR) {
            try {
                UtilCore.General.toUpperCaseDto(this.parametroDto);
                this.mngParametro.editar(this.parametroDto);

                /*    if (this.dto.getId().equals(ConstantesCore.CatalogoParametro.PARAMETROS_GENERALES)) {
                 ApplicationHelper.cargarLogger();
                 ApplicationHelper.cargarParametrosGenerales();
                 }*/
                mensajeTrx = UtilCore.Internacionalizacion.getMensajeInternacional("form.general.mensaje.transaccion.ok");
                showMessage(mensajeTrx);
                to = ConstantesCore.UrlNavegacion.URL_CATALOGO_PARAMETRO;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                to = null;
            }
        }
        return to;
    }

    public String retrocederParametro() {
        return ConstantesCore.UrlNavegacion.URL_CATALOGO_PARAMETRO;
    }

    public void cambioCatalogoParametro() {
        this.listaParametrosPadre = getListaParametrosPadre();
    }

    public int getAccionParametro() {
        return accionParametro;
    }

    public void setAccionParametro(int accionParametro) {
        this.accionParametro = accionParametro;
    }

    public ParametroDto getParametroDto() {
        return parametroDto;
    }

    public void setParametroDto(ParametroDto parametroDto) {
        this.parametroDto = parametroDto;
    }

    public SelectItem[] getSiTipos() {
        return this.recursosManager.siTiposParametro();
    }

    public void setSiTipos(SelectItem[] siTipos) {
        this.siTipos = siTipos;
    }

    public CatalogoParametroDto getDto() {
        return dto;
    }

    public void setDto(CatalogoParametroDto dto) {
        this.dto = dto;
    }

    public String getKeyTitulo() {
        return keyTitulo;
    }

    public void setKeyTitulo(String keyTitulo) {
        this.keyTitulo = keyTitulo;
    }

    public List<CatalogoParametroDto> getLista() {
        return lista;
    }

    public void setLista(List<CatalogoParametroDto> lista) {
        this.lista = lista;
    }

    public CatalogoParametroManager getManager() {
        return manager;
    }

    public void setManager(CatalogoParametroManager manager) {
        this.manager = manager;
    }

    public CatalogoParametroDto getDtoFiltro() {
        return dtoFiltro;
    }

    public void setDtoFiltro(CatalogoParametroDto dtoFiltro) {
        this.dtoFiltro = dtoFiltro;
    }

    public List<ParametroDto> getListaParametrosPadre() {
        return this.recursosManager.obtenerParametrosActivosPorCatalogo(parametroDto.getCatalogoParametroPadreDto().getId());
    }

    public void setListaParametrosPadre(List<ParametroDto> listaParametrosPadre) {
        this.listaParametrosPadre = listaParametrosPadre;
    }

    public RecursosManager getRecursosManager() {
        return recursosManager;
    }

    public void setRecursosManager(RecursosManager recursosManager) {
        this.recursosManager = recursosManager;
    }
}
