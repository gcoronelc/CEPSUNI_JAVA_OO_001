/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.core.manager;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import jxl.CellView;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import sys.core.common.UtilCore;
import sys.core.configuracion.ApplicationHelper;
import sys.core.configuracion.WebServletContextListener;
import sys.core.dto.CatalogoParametroDto;
import sys.core.dto.ParametroDto;
import sys.core.exception.DAOException;
import sys.core.util.ConstantesCore;
import sys.core.view.mbean.ApplicationMBean;

/**
 *
 * @author admin
 */
@Service
public class RecursosManager implements Serializable {

    private static Logger logger = Logger.getLogger(ApplicationHelper.class);

    public String construirTitulo(final String keyEntidad, final int accion, final String... otros) {
        String retorno = null;
        StringBuilder sbTitulo = new StringBuilder();

        if (accion == ConstantesCore.Formulario.LISTA) {
            sbTitulo.append(UtilCore.Internacionalizacion.getMensajeInternacional("general.button.buscar"));
        } else if (accion == ConstantesCore.Formulario.NUEVO) {
            sbTitulo.append(UtilCore.Internacionalizacion.getMensajeInternacional("general.button.nuevo"));
        } else if (accion == ConstantesCore.Formulario.EDITAR) {
            sbTitulo.append(UtilCore.Internacionalizacion.getMensajeInternacional("general.button.editar"));
        } else if (accion == ConstantesCore.Formulario.VER) {
            sbTitulo.append(UtilCore.Internacionalizacion.getMensajeInternacional("general.button.eliminar"));
        }
        sbTitulo.append(" ");
        sbTitulo.append(UtilCore.Internacionalizacion.getMensajeInternacional(keyEntidad));

        retorno = sbTitulo.toString();

        return retorno;
    }

    public List<ParametroDto> obtenerParametrosActivosPorCatalogo(final Long catalogo) {
        ParametroManager manager = (ParametroManager) WebServletContextListener.getApplicationContext().getBean("parametroManager");
        try {
            return manager.obtenerParametrosActivosPorCatalogo(catalogo);
        } catch (DAOException ex) {
            logger.error(ex);
            return null;
        }
    }

    public List<ParametroDto> obtenerParametrosActivosPorCatalogoOrdenManual(final Long catalogo, String tipoDato, String tipoOrden) {
        ParametroManager manager = (ParametroManager) WebServletContextListener.getApplicationContext().getBean("parametroManager");
        try {
            return manager.obtenerParametrosActivosPorCatalogoOrdenManual(catalogo, tipoDato, tipoOrden);
        } catch (DAOException ex) {
            logger.error(ex);
            return null;
        }
    }

    public List<ParametroDto> obtenerParametrosActivosPorCatalogoOrdenManualAdd(final Long catalogo, String tipoDato, String orden, Long... add) {
        ParametroManager manager = (ParametroManager) WebServletContextListener.getApplicationContext().getBean("parametroManager");
        List<ParametroDto> l = new ArrayList<ParametroDto>();
        for (Long x : add) {
            l.add(obtenerParametroPorID(x));
        }
        try {
            l.addAll(manager.obtenerParametrosActivosPorCatalogoOrdenManual(catalogo, tipoDato, orden));
        } catch (DAOException ex) {
            logger.error(ex);
        }

        return l;
    }

    public List<ParametroDto> obtenerParametrosActivosPorCatalogoExcepcionAdd(final Long catalogo, Long excepcion, Long... add) {
        ParametroManager manager = (ParametroManager) WebServletContextListener.getApplicationContext().getBean("parametroManager");
        List<ParametroDto> l = new ArrayList<ParametroDto>();
        for (Long x : add) {
            l.add(obtenerParametroPorID(x));
        }
        try {
            l.addAll(manager.obtenerParametrosActivosPorCatalogoconExcepcion(catalogo, excepcion));
        } catch (DAOException ex) {
            logger.error(ex);
            return null;
        }
        return l;
    }

    public List<ParametroDto> obtenerParametrosActivosPorCatalogoAdd(final Long catalogo, Long... add) {
        ParametroManager manager = (ParametroManager) WebServletContextListener.getApplicationContext().getBean("parametroManager");
        List<ParametroDto> l = new ArrayList<ParametroDto>();
        for (Long x : add) {
            l.add(obtenerParametroPorID(x));
        }
        try {
            l.addAll(manager.obtenerParametrosActivosPorCatalogo(catalogo));
        } catch (DAOException ex) {
            logger.error(ex);
        }

        return l;
    }

    public List<ParametroDto> obtenerParametrosActivosPorCatalogo(final Long catalogo, Long... listaParametrosExcepcion) {
        ParametroManager manager = (ParametroManager) WebServletContextListener.getApplicationContext().getBean("parametroManager");
        try {
            return manager.obtenerParametrosActivosPorCatalogo(catalogo, listaParametrosExcepcion);
        } catch (DAOException ex) {
            logger.error(ex);
            return null;
        }
    }

    private boolean existeParametroEnLista(final Long parametro, final List<ParametroDto> lista) {
        boolean retorno = false;
        for (ParametroDto p : lista) {
            if (p.getId().equals(parametro)) {
                retorno = true;
            }
        }
        return retorno;
    }

    public List<ParametroDto> obtenerParametrosActivosPorCatalogo(Long catalogo, Long padre) {
        ParametroManager manager = (ParametroManager) WebServletContextListener.getApplicationContext().getBean("parametroManager");
        try {
            return manager.obtenerParametrosActivosPorCatalogoPorParametroPadre(catalogo, padre);
        } catch (DAOException ex) {
            logger.error(ex);
            return null;
        }
    }

    public List<ParametroDto> obtenerParametrosActivosPorCatalogoExcepcion(Long catalogo, Long parametro) {
        ParametroManager manager = (ParametroManager) WebServletContextListener.getApplicationContext().getBean("parametroManager");
        try {
            return manager.obtenerParametrosActivosPorCatalogoconExcepcion(catalogo, parametro);
        } catch (DAOException ex) {
            logger.error(ex);
            return null;
        }
    }

    public List<ParametroDto> obtenerParametrosActivosPorCatalogo(Long catalogo, ParametroDto padre) {
        ParametroManager manager = (ParametroManager) WebServletContextListener.getApplicationContext().getBean("parametroManager");
        try {
            if (padre != null && padre.getId() != null) {
                return manager.obtenerParametrosActivosPorCatalogoPorParametroPadre(catalogo, padre.getId());
            } else {
                return new ArrayList<ParametroDto>();
            }
        } catch (DAOException ex) {
            logger.error(ex);
            return null;
        }
    }

    public CatalogoParametroDto obtenerCatalogoParametroPorID(Long id) {
        CatalogoParametroManager manager = (CatalogoParametroManager) WebServletContextListener.getApplicationContext().getBean("catalogoParametroManager");
        try {
            return manager.obtenerPorId(id);
        } catch (DAOException ex) {
            logger.error(ex);
            return null;
        }
    }

    public ParametroDto obtenerParametroPorID(Long id) {
        ParametroManager manager = (ParametroManager) WebServletContextListener.getApplicationContext().getBean("parametroManager");
        try {
            return manager.obtenerPorId(id);
        } catch (DAOException ex) {
            logger.error(ex);
            return null;
        }
    }

    public SelectItem[] siTiposParametro() {
        SelectItem[] si = new SelectItem[2];
        si[0] = new SelectItem("EMPRESA", "EMPRESA");
        si[1] = new SelectItem("SISTEMA", "SISTEMA");
        return si;
    }

    public void viewArchivo(String archivo) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        response.setContentType("application/force-download");
        response.addHeader("Content-Disposition", "attachment; filename=\"" + archivo + "\"");
        ApplicationMBean applicationMBean = (ApplicationMBean) WebServletContextListener.getApplicationContext().getBean("applicationMBean");
        byte[] buf = new byte[1024];
        try {
            File file = new File(applicationMBean.getRutaArchivos() + archivo);
            long length = file.length();
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
            ServletOutputStream out = response.getOutputStream();
            response.setContentLength((int) length);
            while ((in != null) && ((length = in.read(buf)) != -1)) {
                out.write(buf, 0, (int) length);
            }
            in.close();
            out.close();
        } catch (Exception exc) {
            logger.error(exc);
        }
    }

    public String viewReportePdf(HashMap hmParametros, String reporte, String nombreArchivo) throws FileNotFoundException, JRException, IOException, SQLException {
        InputStream ie = null;
        Connection c = null;
        try {
            ApplicationMBean applicationMBean = (ApplicationMBean) WebServletContextListener.getApplicationContext().getBean("applicationMBean");
            File file = new File(applicationMBean.getRutaJaspers());
            ie = new FileInputStream(new File(file, reporte + ".jasper"));
            c = applicationMBean.obtenerConexionDataBase();
            if (c != null) {
                logger.info("P_DIRECTORIO_REPORTES: " + file.getCanonicalPath() + File.separator);
                hmParametros.put("P_DIRECTORIO_REPORTES", file.getCanonicalPath() + File.separator);
                hmParametros.put(JRParameter.REPORT_LOCALE, new Locale("es_PE"));
                hmParametros.put(JRParameter.REPORT_TIME_ZONE, applicationMBean.getTimeZone());
                JasperPrint jasperPrint = JasperFillManager.fillReport(ie, hmParametros, c);
                logger.info("el jasperProcesado: " + jasperPrint.toString());
                byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
                FacesContext context = FacesContext.getCurrentInstance();
                HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
                response.addHeader("Content-disposition",
                        "attachment;filename=" + nombreArchivo);
                response.setContentLength(bytes.length);
                response.getOutputStream().write(bytes);
                response.setContentType("application/pdf");
                context.responseComplete();
            } else {
                logger.error("NO SE PUEDO EJECUTAR LA IMPRESION LA CONEXION ES NULA");
            }

            return null;
        } catch (JRException ex) {
            logger.error("Error al rellenar el reporte " + ex.getMessage());
            logger.error(ex);
        } catch (IOException ex) {
            logger.error("Error al leer el jasper " + ex.getMessage());
            logger.error(ex);
        } catch (Throwable ex) {
            logger.error("Otros errores con el jasper " + ex.getMessage());
            logger.error(ex);
        } finally {
            try {
                if (c != null) {
                    c.close();
                }
                if (ie != null) {
                    ie.close();
                }
            } catch (IOException ex) {
                logger.error(ex);
            }
            return null;
        }

    }

    public int[] listaEnteros(int inicio, int fin) {
        int total = fin - inicio + 2;
        int[] arreglo = new int[total];
        arreglo[0] = -1;
        int indice = 1;
        for (int i = inicio; i <= fin; i++) {
            arreglo[indice++] = i;
        }
        return arreglo;
    }

    public SelectItem[] siMesesAnio(boolean addTodos) {
        int total = 12;
        SelectItem[] si = null;
        int indice = 0;
        if (addTodos) {
            si = new SelectItem[total + 1];
            int id = sys.core.common.ConstantesCore.ExpresionQuartzCodigos.TODOS;
            String label = sys.core.common.ConstantesCore.ExpresionQuartzEtiquetas.TODOS;
            si[indice++] = new SelectItem(id, label);
        } else {
            si = new SelectItem[total];
        }
        for (int i = 0; i < 12; i++) {
            si[indice++] = new SelectItem(i, sys.core.common.ConstantesCore.MESES_ANIO[i]);
        }
        return si;
    }

    public SelectItem[] siDiasSemana(boolean addTodos, boolean addIncognita) {
        int total = 7;

        if (addTodos) {
            total += 1;
        }
        if (addIncognita) {
            total += 1;
        }

        int indice = 0;
        SelectItem[] si = new SelectItem[total];
        if (addTodos) {
            int id = sys.core.common.ConstantesCore.ExpresionQuartzCodigos.TODOS;
            String label = sys.core.common.ConstantesCore.ExpresionQuartzEtiquetas.TODOS;
            si[indice++] = new SelectItem(id, label);
        }

        if (addIncognita) {
            int id = sys.core.common.ConstantesCore.ExpresionQuartzCodigos.INCOGNITA;
            String label = sys.core.common.ConstantesCore.ExpresionQuartzEtiquetas.INCOGNITA;
            si[indice++] = new SelectItem(id, label);
        }

        for (int i = 0; i < 7; i++) {
            si[indice++] = new SelectItem(i, sys.core.common.ConstantesCore.DIAS_SEMANA[i]);
        }

        return si;
    }

    public SelectItem[] siEnteros(int inicio, int fin, boolean addTodos, boolean addUltimo, boolean addPrimero, boolean addIncognita) {
        int total = fin - inicio + 1;
        if (addTodos) {
            total += 1;
        }
        if (addUltimo) {
            total += 1;
        }
        if (addPrimero) {
            total += 1;
        }
        if (addIncognita) {
            total += 1;
        }

        SelectItem[] si = new SelectItem[total];
        int indice = 0;
        if (addTodos) {
            int id = sys.core.common.ConstantesCore.ExpresionQuartzCodigos.TODOS;
            String label = sys.core.common.ConstantesCore.ExpresionQuartzEtiquetas.TODOS;
            si[indice++] = new SelectItem(id, label);
        }
        if (addUltimo) {
            int id = sys.core.common.ConstantesCore.ExpresionQuartzCodigos.ULTIMO;
            String label = sys.core.common.ConstantesCore.ExpresionQuartzEtiquetas.ULTIMO;
            si[indice++] = new SelectItem(id, label);
        }
        if (addPrimero) {
            int id = sys.core.common.ConstantesCore.ExpresionQuartzCodigos.PRIMERO;
            String label = sys.core.common.ConstantesCore.ExpresionQuartzEtiquetas.PRIMERO;
            si[indice++] = new SelectItem(id, label);
        }
        if (addIncognita) {
            int id = sys.core.common.ConstantesCore.ExpresionQuartzCodigos.INCOGNITA;
            String label = sys.core.common.ConstantesCore.ExpresionQuartzEtiquetas.INCOGNITA;
            si[indice++] = new SelectItem(id, label);
        }

        for (int i = inicio; i <= fin; i++) {
            si[indice++] = new SelectItem(i, i < 10 ? ("0" + i) : String.valueOf(i));
        }
        return si;
    }

    public void generarExcel(String cabecera, List listaExportar, String nombreArchivo) {
        try {
            ApplicationMBean applicationMBean = (ApplicationMBean) WebServletContextListener.getApplicationContext().getBean("applicationMBean");
            String ruta = applicationMBean.getRutaArchivos();
            File f = new File(ruta, nombreArchivo);
            f.createNewFile();
            WritableWorkbook workbook = Workbook.createWorkbook(f);

            WritableFont fuenteCelda = null;
            WritableCellFormat formatoCelda = null;
            WritableSheet sheet = workbook.createSheet("REPORTE", 0);

            // imprimiendo titulos
            fuenteCelda = new WritableFont(WritableFont.createFont("Calibri"), 8, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.WHITE);
            formatoCelda = new WritableCellFormat(fuenteCelda);
            formatoCelda.setBackground(Colour.DARK_BLUE);
            formatoCelda.setAlignment(Alignment.CENTRE);
            formatoCelda.setVerticalAlignment(VerticalAlignment.TOP);

            formatoCelda.setBorder(Border.ALL, BorderLineStyle.NONE);

            StringTokenizer cabecera_array = new StringTokenizer(cabecera, "|");
            int columnas = 0;
            while (cabecera_array.hasMoreTokens()) {
                Label label;
                label = new Label(columnas, 0, cabecera_array.nextToken(), formatoCelda);
                sheet.addCell(label);
                columnas++;
            }

            CellView cv = new CellView();
            // Anchos de columnas
            for (int j = 0; j < columnas; j++) {
                cv = sheet.getColumnView(j);
                cv.setAutosize(true);
                sheet.setColumnView(j, cv);
            }

            fuenteCelda = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
            formatoCelda = new WritableCellFormat(fuenteCelda);
            formatoCelda.setBackground(Colour.WHITE);
            formatoCelda.setAlignment(Alignment.GENERAL);
            formatoCelda.setBorder(Border.ALL, BorderLineStyle.THIN);

            int fila = 1;
            for (Iterator iter = listaExportar.iterator(); iter.hasNext();) {
                String registro = (String) iter.next();
                StringTokenizer registro_array = new StringTokenizer(registro, "|");
                int columnas_registro = 0;
                while (registro_array.hasMoreTokens()) {
                    Label label;
                    label = new Label(columnas_registro, fila, registro_array.nextToken(), formatoCelda);
                    sheet.addCell(label);
                    columnas_registro++;
                }

                fila++;
            }
            workbook.write();
            workbook.close();
        } catch (Exception e) {
            logger.error(e);
        }
    }
}
