/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.core.configuracion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;
import static jxl.biff.BaseCellFeatures.logger;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import sys.core.dto.EmpresaDto;
import sys.core.dto.ParametroDto;
import sys.core.dto.PermisoDto;
import sys.core.exception.DAOException;
import sys.core.manager.PermisoManager;
import sys.core.manager.RecursosManager;
import sys.core.util.ConstantesCore;
import sys.core.view.mbean.ApplicationMBean;
import sys.core.view.mbean.SessionMBean;

/**
 *
 * @author admin
 */
public class ApplicationHelper {

    public static void cargarLogger() {
        Logger logger = null;
        try {
            RecursosManager recursosManager = (RecursosManager) WebServletContextListener.getApplicationContext().getBean("recursosManager");
            String rutaLog = "";
            {
                ParametroDto p = recursosManager.obtenerParametroPorID(ConstantesCore.Parametro.PARAMETRO_GENERAL_LOG);
                if (p.getValorCadena() != null) {
                    rutaLog = p.getValorCadena();
                } else {
                    rutaLog = "D:\\log\\FastRemisse";
                }
            }
            System.out.print("= = = = = = = = = =  INICIANDO CONTEXTO FAST REMISSE = = = = = = = = = =  ");
            Properties prop = new Properties();
            prop.setProperty("log4j.rootCategory", "INFO, LOGFILE, CONSOLE");
            prop.setProperty("log4j.appender.CONSOLE", "org.apache.log4j.ConsoleAppender");
            prop.setProperty("log4j.appender.CONSOLE.layout", "org.apache.log4j.PatternLayout");
            prop.setProperty("log4j.appender.CONSOLE.layout.ConversionPattern", "[%d{yyyy-MM-dd HH:mm:ss}] - [%5p] (%C{1}.%M:%L) - %m%n");
            prop.setProperty("log4j.appender.LOGFILE", "org.apache.log4j.DailyRollingFileAppender");
            prop.setProperty("log4j.appender.LOGFILE.file", rutaLog);
            prop.setProperty("log4j.appender.LOGFILE.DatePattern", "'.'yyyy-MM-dd'.log'");
            prop.setProperty("log4j.appender.LOGFILE.MaxFileSize", "2048KB");
            prop.setProperty("log4j.appender.archivo.maxFileSize", "20MB");
            prop.setProperty("log4j.appender.archivo.maxBackupIndex", "5");
            prop.setProperty("log4j.appender.LOGFILE.append", "true");
            prop.setProperty("log4j.appender.LOGFILE.layout", "org.apache.log4j.PatternLayout");
            prop.setProperty("log4j.appender.LOGFILE.layout.ConversionPattern", "[%d{yyyy-MM-dd HH:mm:ss}] - [%5p] (%C{1}.%M:%L) - %m%n");
            //prop.setProperty("log4j.logger.org.springframework", "INFO, LOGFILE, CONSOLE");
            //prop.setProperty("log4j.logger.org.hibernate", "INFO, LOGFILE, CONSOLE");
            PropertyConfigurator.configure(prop);

            logger.info("= = = = = = = = = =  CONTEXTO FAST REMISSE CARGADO SATISFACTORIAMENTE = = = = = = = = = =  ");
            System.out.print("= = = = = = = = = =  CONTEXTO FAST REMISSE CARGADO SATISFACTORIAMENTE = = = = = = = = = =  ");

        } catch (Exception e) {
            BasicConfigurator.configure();
        }
    }
    
 

 public static Map<String, Boolean> obtenerPrivilegiosUsuario() {
        Map<String, Boolean> privilegiosOpciones = new HashMap<String, Boolean>();

        SessionMBean sessionMBean = (SessionMBean) WebServletContextListener.getApplicationContext().getBean("sessionMBean");
        PermisoManager permisoRolManager = (PermisoManager) WebServletContextListener.getApplicationContext().getBean("permisoManager");

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rolDto.id", sessionMBean.getSessionUsuarioDto().getRolDto().getId());
        map.put("opcionSistemaDto.tipoMenu", "O");
        Map<String, String> order = new HashMap<String, String>();
        order.put("opcionSistemaDto.orden", "asc");
        order.put("opcionSistemaDto.descripcion", "asc");
        List<PermisoDto> listaPermisos = null;
        try {
            listaPermisos = permisoRolManager.obtenerConFiltroConOrden(map, order);
        } catch (DAOException ex) {
            logger.error(ex);
        }

        for (PermisoDto p : listaPermisos) {
            privilegiosOpciones.put(p.getOpcionSistemaDto().getId().toString(), p.getEstado());
        }


        return privilegiosOpciones;
    }
    

    public static void cargarParametrosGenerales() {
        ApplicationMBean applicationMBean = (ApplicationMBean) WebServletContextListener.getApplicationContext().getBean("applicationMBean");
        RecursosManager recursosManager = (RecursosManager) WebServletContextListener.getApplicationContext().getBean("recursosManager");
        {
            ParametroDto p = recursosManager.obtenerParametroPorID(ConstantesCore.Parametro.PARAMETRO_GENERAL_RUTA_ARCHIVOS);
            if (p != null && p.getValorCadena() != null) {
                applicationMBean.setRutaArchivos(p.getValorCadena());
            } else {
                applicationMBean.setRutaArchivos("D:\\files\\archivos\\");
            }
        }

        {
            ParametroDto p = recursosManager.obtenerParametroPorID(ConstantesCore.Parametro.PARAMETRO_GENERAL_RUTA_JASPERS);
            if (p != null && p.getValorCadena() != null) {
                applicationMBean.setRutaJaspers(p.getValorCadena());
            } else {
                applicationMBean.setRutaJaspers("D:\\files\\jaspers\\");
            }
        }

        {
            ParametroDto p = recursosManager.obtenerParametroPorID(ConstantesCore.Parametro.PARAMETRO_GENERAL_FORMATO_MONTOS_OFERTA);
            if (p != null && p.getValorCadena() != null) {
                applicationMBean.setFormatoMontoOferta(p.getValorCadena());
            } else {
                applicationMBean.setFormatoMontoOferta("#,###.0000");
            }
        }

        {
            ParametroDto p = recursosManager.obtenerParametroPorID(ConstantesCore.Parametro.PARAMETRO_GENERAL_PRECISION_DECIMAL);
            if (p != null && p.getValorEntero() != null) {
                applicationMBean.setPrecisionDecimales(p.getValorEntero().intValue());
            } else {
                applicationMBean.setPrecisionDecimales(2);
            }
        }

        {
            ParametroDto p = recursosManager.obtenerParametroPorID(ConstantesCore.Parametro.PARAMETRO_GENERAL_PRECISION_DECIMAL_OFERTA);
            if (p != null && p.getValorEntero() != null) {
                applicationMBean.setPrecisionDecimalesOferta(p.getValorEntero().intValue());
            } else {
                applicationMBean.setPrecisionDecimalesOferta(8);
            }
        }

        {
            ParametroDto p = recursosManager.obtenerParametroPorID(ConstantesCore.Parametro.PARAMETRO_GENERAL_FORMATO_MONTOS_TIPO_CAMBIO);
            if (p != null && p.getValorCadena() != null) {
                applicationMBean.setFormatoMontosTipoCambio(p.getValorCadena());
            } else {
                applicationMBean.setFormatoMontosTipoCambio("#,###.0000");
            }
        }

        {
            ParametroDto p = recursosManager.obtenerParametroPorID(ConstantesCore.Parametro.PARAMETRO_GENERAL_FORMATO_MONTOS);
            if (p != null && p.getValorCadena() != null) {
                applicationMBean.setFormatoMontos(p.getValorCadena());
            } else {
                applicationMBean.setFormatoMontos("#,###.00");
            }
        }

        {
            ParametroDto p = recursosManager.obtenerParametroPorID(ConstantesCore.Parametro.PARAMETRO_GENERAL_FORMATO_HORA);
            if (p != null && p.getValorCadena() != null) {
                applicationMBean.setFormatoHora(p.getValorCadena());
            } else {
                applicationMBean.setFormatoHora("HH:mm:ss");
            }
        }

        {
            ParametroDto p = recursosManager.obtenerParametroPorID(ConstantesCore.Parametro.PARAMETRO_GENERAL_FORMATO_FECHA);
            if (p != null && p.getValorCadena() != null) {
                applicationMBean.setFormatoFecha(p.getValorCadena());
            } else {
                applicationMBean.setFormatoFecha("dd/MM/yyyy");
            }
        }

        {
            ParametroDto p = recursosManager.obtenerParametroPorID(ConstantesCore.Parametro.PARAMETRO_GENERAL_FORMATO_FECHA_HORA_SEGUNDO);
            if (p != null && p.getValorCadena() != null) {
                applicationMBean.setFormatoFechaHoraSegundos(p.getValorCadena());
            } else {
                applicationMBean.setFormatoFechaHoraSegundos("dd/MM/yyyy HH:mm:ss");
            }
        }

        {
            ParametroDto p = recursosManager.obtenerParametroPorID(ConstantesCore.Parametro.PARAMETRO_GENERAL_FORMATO_FECHA_HORA_MINUTO);
            if (p != null && p.getValorCadena() != null) {
                applicationMBean.setFormatoFechaHora(p.getValorCadena());
            } else {
                applicationMBean.setFormatoFechaHora("dd/MM/yyyy HH:mm");
            }
        }

    }

}
