    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.movil.mbean;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import sys.core.common.UtilCore;
import sys.core.dto.ParametroDto;
import sys.core.exception.DAOException;
import sys.core.manager.RecursosManager;
import sys.core.util.ConstantesCore;
import sys.core.view.mbean.ApplicationMBean;
import sys.core.view.mbean.GenericMBean;
import static sys.core.view.mbean.GenericMBean.showError;
import static sys.core.view.mbean.GenericMBean.showMessage;
import static sys.core.view.mbean.GenericMBean.showWarning;
import sys.core.view.mbean.SessionMBean;
import sys.movil.dto.UsuarioExternoDto;
import sys.movil.manager.UsuarioExternoManager;

/**
 *
 * @author Indra
 */
@Controller("usuarioExternoMBean")
@Scope("session")
public class UsuarioExternoMBean extends GenericMBean implements Serializable {

    private static Logger logger = Logger.getLogger(UsuarioExternoMBean.class);
    @Resource
    private UsuarioExternoManager manager;
    @Resource
    private RecursosManager recursosManager;
    private List<UsuarioExternoDto> lista;
    private UsuarioExternoDto dto;
    private UsuarioExternoDto dtoFiltro;
    private String keyTitulo = "usuario.panel";
    @Autowired
    private ApplicationMBean applicationMBean;
    @Autowired
    private SessionMBean sessionMBean;
    private String contrasenaAnterior;
    private String contrasenaNueva;
    private String contrasenaVerificar;
    private List<ParametroDto> listaTipoDocumentos;
    private List<ParametroDto> listaTipoAutos;
    private List<ParametroDto> listaMarcaAutos;
    public String imagenPerfil;
    public Boolean esChofer;

    public UsuarioExternoMBean() {
        this.dtoFiltro = new UsuarioExternoDto();
    }

    public String iniciar() {
        this.lista = null;
        this.dto = new UsuarioExternoDto();
        this.dtoFiltro = new UsuarioExternoDto();
        return ConstantesCore.UrlNavegacion.URL_LISTA_USUARIOS_EXTERNOS;
    }

    public String nuevo() {
        sessionMBean.setAccion(ConstantesCore.Formulario.NUEVO);
        sessionMBean.setTitulo(recursosManager.construirTitulo(keyTitulo, sessionMBean.getAccion()));
        this.lista = new ArrayList<UsuarioExternoDto>();
        this.dto = new UsuarioExternoDto();
        this.dto.setEstado(Boolean.TRUE);
        this.dto.setMarcaAutoDto(recursosManager.obtenerParametroPorID(5000L));
        this.dto.setTipoDocumentoDto(recursosManager.obtenerParametroPorID(5002L));
        this.dto.setTipoAutoDto(recursosManager.obtenerParametroPorID(5006L));
        this.dto.setLicencia(Boolean.TRUE);
        esChofer = Boolean.TRUE;
        return ConstantesCore.UrlNavegacion.URL_USUARIO_EXTERNO;
    }

    public String editar() {
        sessionMBean.setAccion(ConstantesCore.Formulario.EDITAR);
        sessionMBean.setTitulo(recursosManager.construirTitulo(keyTitulo, sessionMBean.getAccion()));
    /*    InputStream is = new ByteArrayInputStream((byte[]) dto.getImagenPerfil());
        myImage = new DefaultStreamedContent(is, "image/png");*/
        return ConstantesCore.UrlNavegacion.URL_USUARIO_EXTERNO;
    }

    public String ver() {
        sessionMBean.setAccion(ConstantesCore.Formulario.VER);
        sessionMBean.setTitulo(recursosManager.construirTitulo(keyTitulo, sessionMBean.getAccion()));
        return ConstantesCore.UrlNavegacion.URL_USUARIO_EXTERNO;
    }

    public void buscar(ActionEvent ae) {

        try {
            this.lista = this.manager.obtenerUsuariosExternos(this.dtoFiltro);
        } catch (DAOException ex) {
            logger.error("ERROR DE SISTEMA", ex);
            showError(ex.getMessage());
        }

    }
    
    public static String guardaBlobEnFicheroTemporal(byte[] bytes , String nombreArchivo){
        String ubicacionImagen = null;
        ServletContext servletContext = (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
        String path = servletContext.getRealPath("") + File.separatorChar + "resources" + File.separatorChar + "images" + File.separatorChar + "temp" + File.separatorChar + nombreArchivo;
        File f = null;
        InputStream in = null;
        try{
            f = new File(path);
            in = new ByteArrayInputStream(bytes);
            FileOutputStream out = new FileOutputStream(f.getAbsolutePath());
            int c = 0 ;
            while ((c = in.read()) >= 0){
                out.write(c);
            }
            out.flush();
            out.close();
            ubicacionImagen = "../../resources/images/tmp/" + nombreArchivo;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return ubicacionImagen;
    }
    
    public void subirArchivo(FileUploadEvent e){
        try {
            dto.setImagenPerfil(e.getFile().getContents());
            imagenPerfil = guardaBlobEnFicheroTemporal(dto.getImagenPerfil(), e.getFile().getFileName());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String guardar() {

        String to = null;
        String mensajeTrx = "";
        if (esChofer.equals(Boolean.TRUE)) {
            dto.setTipoUsuario(2L);
        } else {
            dto.setTipoUsuario(1L);
        }
        System.out.println("TipoUsuario" + dto.getTipoUsuario() + "contraseniaActual" + dto.getContraseniaActual());
        this.dto.setUsuarioDto(sessionMBean.getSessionUsuarioDto());
        this.dto.setFecha(UtilCore.Fecha.obtenerFechaActualDate());
        this.dto.setTerminal(sessionMBean.getSessionTerminal());

        if (sessionMBean.getAccion() == ConstantesCore.Formulario.NUEVO) {
            try {
                System.out.println("Entro al nuevo");
                this.dto.setUsuarioCreacionDto(sessionMBean.getSessionUsuarioDto());
                this.dto.setFechaCreacion(UtilCore.Fecha.obtenerFechaActualDate());
                this.dto.setTerminalCreacion(sessionMBean.getSessionTerminal());
                this.dto.setContraseniaActual(UtilCore.Seguridad.encriptarTexto(this.dto.getContraseniaActual().toUpperCase()));
                //UtilCore.General.toUpperCaseDto(this.dto);
                this.manager.nuevo(this.dto);
                mensajeTrx = UtilCore.Internacionalizacion.getMensajeInternacional("form.general.mensaje.transaccion.ok");
                showMessage(mensajeTrx);
                to = retroceder();
            } catch (Exception ex) {
                System.out.println("Error en el nuevo" + ex.getMessage());
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
                System.out.println("Error en el editado" + ex.getMessage());
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
                    to = null;
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
        this.buscar(null);
        sessionMBean.setAccion(ConstantesCore.Formulario.LISTA);
        return ConstantesCore.UrlNavegacion.URL_LISTA_USUARIOS_EXTERNOS;
    }

    public UsuarioExternoDto getDto() {
        return dto;
    }

    public void setDto(UsuarioExternoDto dto) {
        this.dto = dto;
    }

    public String getKeyTitulo() {
        return keyTitulo;
    }

    public void setKeyTitulo(String keyTitulo) {
        this.keyTitulo = keyTitulo;
    }

    public List<UsuarioExternoDto> getLista() {
        return lista;
    }

    public void setLista(List<UsuarioExternoDto> lista) {
        this.lista = lista;
    }

    public UsuarioExternoDto getDtoFiltro() {
        return dtoFiltro;
    }

    public void setDtoFiltro(UsuarioExternoDto dtoFiltro) {
        this.dtoFiltro = dtoFiltro;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        UsuarioExternoMBean.logger = logger;
    }

    public UsuarioExternoManager getManager() {
        return manager;
    }

    public void setManager(UsuarioExternoManager manager) {
        this.manager = manager;
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

    public List<ParametroDto> getListaTipoDocumentos() {
        this.listaTipoDocumentos = recursosManager.obtenerParametrosActivosPorCatalogo(ConstantesCore.CatalogoParametro.TIPO_DOCUMENTOS);
        return listaTipoDocumentos;
    }

    public void setListaTipoDocumentos(List<ParametroDto> listaTipoDocumentos) {
        this.listaTipoDocumentos = listaTipoDocumentos;
    }

    public List<ParametroDto> getListaTipoAutos() {
        this.listaTipoAutos = recursosManager.obtenerParametrosActivosPorCatalogo(ConstantesCore.CatalogoParametro.TIPO_AUTOS);
        return listaTipoAutos;
    }

    public void setListaTipoAutos(List<ParametroDto> listaTipoAutos) {
        this.listaTipoAutos = listaTipoAutos;
    }

    public List<ParametroDto> getListaMarcaAutos() {
        this.listaMarcaAutos = recursosManager.obtenerParametrosActivosPorCatalogo(ConstantesCore.CatalogoParametro.MARCA_AUTOS);
        return listaMarcaAutos;
    }

    public void setListaMarcaAutos(List<ParametroDto> listaMarcaAutos) {
        this.listaMarcaAutos = listaMarcaAutos;
    }

    public Boolean getEsChofer() {
        return esChofer;
    }

    public void setEsChofer(Boolean esChofer) {
        this.esChofer = esChofer;
    }

    public String getImagenPerfil() {
        return imagenPerfil;
    }

    public void setImagenPerfil(String imagenPerfil) {
        this.imagenPerfil = imagenPerfil;
    }
}
