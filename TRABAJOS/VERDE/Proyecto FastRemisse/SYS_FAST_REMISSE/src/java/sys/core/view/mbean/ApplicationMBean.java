/*
 * To change ApplicationMBean template, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.core.view.mbean;

import java.io.Serializable;
import java.sql.Connection;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import sys.core.configuracion.WebServletContextListener;
import sys.core.dto.CatalogoParametroDto;
import sys.core.dto.EmpresaDto;
import sys.core.dto.ParametroDto;
import sys.core.dto.RolDto;
import sys.core.dto.UbigeoDto;
import sys.core.dto.UsuarioDto;
import sys.core.exception.DAOException;
import sys.core.manager.CatalogoParametroManager;
import sys.core.manager.EmpresaManager;
import sys.core.manager.ParametroManager;
import sys.core.manager.RolManager;
import sys.core.manager.UbigeoManager;
import sys.core.manager.UsuarioManager;
import sys.core.util.ConstantesCore;
import sys.movil.dto.UsuarioExternoDto;
import sys.movil.manager.UsuarioExternoManager;

/**
 *
 * @author Indra
 */
@Controller("applicationMBean")
@Scope("application")
public class ApplicationMBean implements Serializable {

    private static Logger logger = Logger.getLogger(ApplicationMBean.class);
    private String dataSourceName = "JDNI_FAST_REMISSE";
    private Boolean habilitarIdioma = Boolean.FALSE;
    private Boolean habilitarTema = Boolean.FALSE;
    private String formatoFechaHora = "dd/MM/yyyy HH:mm";
    private String formatoFechaHoraSegundos = "dd/MM/yyyy HH:mm:ss";
    private String formatoFecha = "dd/MM/yyyy";
    private String formatoHora = "HH:mm:ss";
    private String formatoMontos = "#,###.00";
    private String formatoMontosTipoCambio = "#,###.0000";
    private String formatoMontoOferta = "#,###.0000";
    private String nombreSistema = "Fast Remisse V1";
    private String mailServidor = "smtp.gmail.com";
    private String mailPuerto = "465";
    private String irPaginaInicio = ConstantesCore.UrlNavegacion.URL_INICIO;
    private String rutaArchivos = "";
    private String rutaJaspers = "";
    private int precisionDecimales = 2;
    private int precisionDecimalesOferta = 8;
    private TimeZone timeZone;
    private String archivoConfiguracion;
    private List<UbigeoDto> siPaisesActivos;
    private List<ParametroDto> siParametrosActivos;
    private List<CatalogoParametroDto> siCatalogosActivos;
    private List<RolDto> siRolesActivos;
    private List<EmpresaDto> siOrganizacionesActivas;
    private List<UsuarioDto> siUsuariosActivos;
    private String tamanoAplicacion = "80%";
    private String estilo = "FONT-FAMILY: 'Arial';font-size: 10px;text-transform:uppercase;";
    private String estiloLabel = "FONT-FAMILY: 'Arial';color: #000000;font-size: 10px;text-transform:uppercase;";
    private String estiloLink = "FONT-FAMILY: 'Arial';font-weight: bold; font-size: 10px;text-decoration: underline;color: #cc0000;text-transform:uppercase;";
    private String estiloLinkTable = "FONT-FAMILY: 'Arial';font-size: 10px;text-transform:uppercase;"; //text-decoration: underline;
    private String estiloObligatorio = "FONT-FAMILY:'Arial'; color:#000000;font-size:10px;border-color: #787878;text-transform:uppercase;";
    private String estiloMensaje = "FONT-FAMILY: 'Arial';font-weight: bold; color: #004276;font-size: 9px;text-transform:uppercase;";
    private String estiloMenu = "FONT-FAMILY:'Arial'; color:#004492;font-size:9px;font-weight: bold;";
    private String estiloTitulo = "FONT-FAMILY:'Arial'; color:#004492;font-size:10px;";
    private String estiloButton = "height: 30px;width: 70px;font-size:10px;";

    public ApplicationMBean() {
    }

     

    public List<ParametroDto> autoCompletarParametro(String query) {

        ParametroManager manager = (ParametroManager) WebServletContextListener.getApplicationContext().getBean("parametroManager");
        List<ParametroDto> results = null;
        try {
            results = manager.obtenerTodosActivos(query);
        } catch (DAOException ex) {
            logger.error(ex);
            results = new ArrayList<ParametroDto>();
        }
        return results;
    }
    
    public List<UsuarioExternoDto> autoCompletarUsuarioExterno(String query) {
        UsuarioExternoManager manager = (UsuarioExternoManager) WebServletContextListener.getApplicationContext().getBean("usuarioExternoManager");
        List<UsuarioExternoDto> results = null;
        try {
            results = manager.obtener(query);
        } catch (DAOException ex) {
            logger.error(ex);
            results = new ArrayList<UsuarioExternoDto>();
        }
        return results;
    }

    public List<EmpresaDto> autoCompletarEmpresa(String query) {
        EmpresaManager manager = (EmpresaManager) WebServletContextListener.getApplicationContext().getBean("organizacionManager");
        List<EmpresaDto> results = null;
        try {
            results = manager.obtenerTodosPorEstado(ConstantesCore.Parametro.EMPRESA_ESTADO_ACTIVO, query);
        } catch (DAOException ex) {
            logger.error(ex);
            results = new ArrayList<EmpresaDto>();
        }
        return results;
    }

    public List<CatalogoParametroDto> getSiCatalogosActivos() {
        CatalogoParametroManager manager = (CatalogoParametroManager) WebServletContextListener.getApplicationContext().getBean("catalogoParametroManager");
        try {
            return manager.obtenerTodosActivos();
        } catch (DAOException ex) {
            logger.error(ex);
            return new ArrayList<CatalogoParametroDto>();
        }
    }

    public void setSiCatalogosActivos(List<CatalogoParametroDto> siCatalogosActivos) {
        this.siCatalogosActivos = siCatalogosActivos;
    }

    public List<EmpresaDto> getSiOrganizacionesActivas() {
        EmpresaManager manager = (EmpresaManager) WebServletContextListener.getApplicationContext().getBean("organizacionManager");
        try {
            return manager.obtenerTodosPorEstado(ConstantesCore.Parametro.EMPRESA_ESTADO_ACTIVO);
        } catch (DAOException ex) {
            logger.error(ex);
            return new ArrayList<EmpresaDto>();
        }
    }

    public void setSiOrganizacionesActivas(List<EmpresaDto> siOrganizacionesActivas) {
        this.siOrganizacionesActivas = siOrganizacionesActivas;
    }

    public List<UbigeoDto> getSiPaisesActivos() {
        UbigeoManager manager = (UbigeoManager) WebServletContextListener.getApplicationContext().getBean("ubigeoManager");
        try {
            return manager.obtenerPaisesActivos();
        } catch (DAOException ex) {
            logger.error(ex);
            return new ArrayList<UbigeoDto>();
        }
    }

    public void setSiPaisesActivos(List<UbigeoDto> siPaisesActivos) {
        this.siPaisesActivos = siPaisesActivos;
    }

    public List<ParametroDto> getSiParametrosActivos() {
        ParametroManager manager = (ParametroManager) WebServletContextListener.getApplicationContext().getBean("parametroManager");
        try {
            return manager.obtenerTodosActivos();
        } catch (DAOException ex) {
            logger.error(ex);
            return new ArrayList<ParametroDto>();
        }
    }

    public void setSiParametrosActivos(List<ParametroDto> siParametrosActivos) {
        this.siParametrosActivos = siParametrosActivos;
    }

    public List<RolDto> getSiRolesActivos() {
        RolManager manager = (RolManager) WebServletContextListener.getApplicationContext().getBean("rolManager");
        try {
            return manager.obtenerTodosActivos();
        } catch (DAOException ex) {
            logger.error(ex);
            return new ArrayList<RolDto>();
        }
    }

    public void setSiRolesActivos(List<RolDto> siRolesActivos) {
        this.siRolesActivos = siRolesActivos;
    }

    public List<UsuarioDto> getSiUsuariosActivos() {
        UsuarioManager usuarioManager = (UsuarioManager) WebServletContextListener.getApplicationContext().getBean("usuarioManager");
        try {
            return usuarioManager.obtenerConsultores();
        } catch (DAOException ex) {
            logger.error(ex);
            return new ArrayList<UsuarioDto>();
        }
    }

    public void setSiUsuariosActivos(List<UsuarioDto> siUsuariosActivos) {
        this.siUsuariosActivos = siUsuariosActivos;
    }

    public String getFormatoMontosTipoCambio() {
        return formatoMontosTipoCambio;
    }

    public void setFormatoMontosTipoCambio(String formatoMontosTipoCambio) {
        this.formatoMontosTipoCambio = formatoMontosTipoCambio;
    }

    public Connection obtenerConexionDataBase() {
        Connection cnx = null;
        try {
            InitialContext initctx = new InitialContext();
            DataSource ds = (DataSource) initctx.lookup(dataSourceName);
            cnx = ds.getConnection();
        } catch (Exception e) {
            cnx = null;
            logger.error(e);
        }

        return cnx;
    }

    public String getFormatoMontoOferta() {
        return formatoMontoOferta;
    }

    public void setFormatoMontoOferta(String formatoMontoOferta) {
        this.formatoMontoOferta = formatoMontoOferta;
    }

    public void postProcessXLS(Object document) {
        final HSSFWorkbook wb = (HSSFWorkbook) document;
        final HSSFSheet sheet = wb.getSheetAt(0);
        final HSSFRow header = sheet.getRow(0);
        final HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFillForegroundColor(HSSFColor.YELLOW.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        for (int i = 0; i < header.getPhysicalNumberOfCells(); i++) {
            ((HSSFCell) header.getCell(i)).setCellStyle(cellStyle);
        }
    }

    public int getPrecisionDecimalesOferta() {
        return precisionDecimalesOferta;
    }

    public void setPrecisionDecimalesOferta(int precisionDecimalesOferta) {
        this.precisionDecimalesOferta = precisionDecimalesOferta;
    }

    public String formatFecha(Date fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat(getFormatoFecha());
        sdf.setTimeZone(getTimeZone());
        return sdf.format(fecha);
    }

    public String getDataSourceName() {
        return dataSourceName;
    }

    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }

    public String getMailPuerto() {
        return mailPuerto;
    }

    public void setMailPuerto(String mailPuerto) {
        this.mailPuerto = mailPuerto;
    }

    public String getMailServidor() {
        return mailServidor;
    }

    public void setMailServidor(String mailServidor) {
        this.mailServidor = mailServidor;
    }

    public String getNombreSistema() {
        return nombreSistema;
    }

    public void setNombreSistema(String nombreSistema) {
        this.nombreSistema = nombreSistema;
    }

    public String getRutaArchivos() {
        return rutaArchivos;
    }

    public void setRutaArchivos(String rutaArchivos) {
        this.rutaArchivos = rutaArchivos;
    }

    public String getRutaJaspers() {
        return rutaJaspers;
    }

    public void setRutaJaspers(String rutaJaspers) {
        this.rutaJaspers = rutaJaspers;
    }

    public String getEstiloMenu() {
        return estiloMenu;
    }

    public void setEstiloMenu(String estiloMenu) {
        this.estiloMenu = estiloMenu;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        ApplicationMBean.logger = logger;
    }

    public Boolean isHabilitarIdioma() {
        return habilitarIdioma;
    }

    public void setHabilitarIdioma(Boolean habilitarIdioma) {
        this.habilitarIdioma = habilitarIdioma;
    }

    public Boolean isHabilitarTema() {
        return habilitarTema;
    }

    public void setHabilitarTema(Boolean habilitarTema) {
        this.habilitarTema = habilitarTema;
    }

    public String getFormatoFechaHora() {
        return formatoFechaHora;
    }

    public void setFormatoFechaHora(String formatoFechaHora) {
        this.formatoFechaHora = formatoFechaHora;
    }

    public String getFormatoFechaHoraSegundos() {
        return formatoFechaHoraSegundos;
    }

    public void setFormatoFechaHoraSegundos(String formatoFechaHoraSegundos) {
        this.formatoFechaHoraSegundos = formatoFechaHoraSegundos;
    }

    public String getFormatoFecha() {
        return formatoFecha;
    }

    public void setFormatoFecha(String formatoFecha) {
        this.formatoFecha = formatoFecha;
    }

    public String getFormatoHora() {
        return formatoHora;
    }

    public void setFormatoHora(String formatoHora) {
        this.formatoHora = formatoHora;
    }

    public String getFormatoMontos() {
        return formatoMontos;
    }

    public void setFormatoMontos(String formatoMontos) {
        this.formatoMontos = formatoMontos;
    }

    public int getPrecisionDecimales() {
        return precisionDecimales;
    }

    public void setPrecisionDecimales(int precisionDecimales) {
        this.precisionDecimales = precisionDecimales;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public String getArchivoConfiguracion() {
        return archivoConfiguracion;
    }

    public void setArchivoConfiguracion(String archivoConfiguracion) {
        this.archivoConfiguracion = archivoConfiguracion;
    }

    public String getTamanoAplicacion() {
        return tamanoAplicacion;
    }

    public void setTamanoAplicacion(String tamanoAplicacion) {
        this.tamanoAplicacion = tamanoAplicacion;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public String getEstiloLabel() {
        return estiloLabel;
    }

    public void setEstiloLabel(String estiloLabel) {
        this.estiloLabel = estiloLabel;
    }

    public String getEstiloLink() {
        return estiloLink;
    }

    public void setEstiloLink(String estiloLink) {
        this.estiloLink = estiloLink;
    }

    public String getEstiloLinkTable() {
        return estiloLinkTable;
    }

    public void setEstiloLinkTable(String estiloLinkTable) {
        this.estiloLinkTable = estiloLinkTable;
    }

    public String getEstiloObligatorio() {
        return estiloObligatorio;
    }

    public void setEstiloObligatorio(String estiloObligatorio) {
        this.estiloObligatorio = estiloObligatorio;
    }

    public String getEstiloMensaje() {
        return estiloMensaje;
    }

    public void setEstiloMensaje(String estiloMensaje) {
        this.estiloMensaje = estiloMensaje;
    }

    public String getEstiloTitulo() {
        return estiloTitulo;
    }

    public void setEstiloTitulo(String estiloTitulo) {
        this.estiloTitulo = estiloTitulo;
    }

    public String getIrPaginaInicio() {
        return irPaginaInicio;
    }

    public void setIrPaginaInicio(String irPaginaInicio) {
        this.irPaginaInicio = irPaginaInicio;
    }

    public String getEstiloButton() {
        return estiloButton;
    }

    public void setEstiloButton(String estiloButton) {
        this.estiloButton = estiloButton;
    }
}
