/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.core.common;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.apache.log4j.Logger;
import sys.core.dto.ProcesoDto;

/**
 *
 * @author Indra
 */
public class ProgramacionTareasCore {

    private static Logger logger = Logger.getLogger(ProgramacionTareasCore.class);

    public void configuracion() {
        try {
            /*RECORRE MINUTO A MINUTO CONFIGURACION EN APPICATIONCONTEXT*/
            Calendar calendario = Calendar.getInstance();
            String hora = calendario.get(Calendar.HOUR_OF_DAY)<10?("0"+calendario.get(Calendar.HOUR_OF_DAY)):String.valueOf(calendario.get(Calendar.HOUR_OF_DAY));
            String min = calendario.get(Calendar.MINUTE)<10?("0"+calendario.get(Calendar.MINUTE)):String.valueOf(calendario.get(Calendar.MINUTE));
            String seg = calendario.get(Calendar.SECOND)<10?("0"+calendario.get(Calendar.SECOND)):String.valueOf(calendario.get(Calendar.SECOND));
            
            String dia = calendario.get(Calendar.DAY_OF_MONTH)<10?("0"+calendario.get(Calendar.DAY_OF_MONTH)):String.valueOf(calendario.get(Calendar.DAY_OF_MONTH));
            String mes = (calendario.get(Calendar.MONTH))+1<10?("0"+(calendario.get(Calendar.MONTH)+1)):String.valueOf(calendario.get(Calendar.MONTH)+1);
            String anio = String.valueOf(calendario.get(Calendar.YEAR));
            
            String yyyymmdd = anio+"/"+mes+"/"+dia;
            String hhmi = hora+":"+min;            
            
            
            //ProcesoManager procesoManager = (ProcesoManager) WebServletContextListener.getApplicationContext().getBean("procesoManager");
            List<ProcesoDto> listaProcesos = new ArrayList<ProcesoDto>();//procesoManager.obtenerTodosActivos();
            
            
            //System.out.println(hhmi);
            for (ProcesoDto p : listaProcesos) {
                if (p.getHoraEjecucion().equalsIgnoreCase(hhmi)) {
                    p.setFechaEjecutada(yyyymmdd);
                    p.setHoraEjecutada(hhmi);
                    HiloTarea h1 = new HiloTarea(p);
                    h1.start();
                }                
            }
        } catch (Exception ex) {
            logger.error("ERROR AL TRAER LA LISTA DE PROCESOS", ex);
        }
        
        
    }
}
